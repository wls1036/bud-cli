package org.bud.cli.parser;

import org.bud.cli.component.Component;
import org.bud.cli.component.Page;
import org.bud.cli.util.BudUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:09
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Parser {

    private List<PageFile> pageFiles = new ArrayList<>();


    public Parser() {

    }

    public Parser(List<PageFile> pageFiles) {
        this.pageFiles = pageFiles;
    }

    public Parser(PageFile pageFile) {
        pageFiles.add(pageFile);
    }

    /**
     * 解析单个文件
     *
     * @return
     */
    public Page parseSinglePage() {
        return this.parse().get(0);
    }

    /**
     * 解析文件
     *
     * @return
     */
    public List<Page> parse() {
        List<Page> pages = new ArrayList<>();
        for (PageFile f : this.pageFiles) {
            pages.add(this.parsePage(f));
        }
        return pages;
    }

    /**
     * 解析文件核心逻辑
     *
     * @param pf
     * @return
     */
    private Page parsePage(PageFile pf) {
        Page p = new Page();
        p.setPageName(pf.getName());
        List<String> lines = pf.getLines();
        List<String> tmp = new ArrayList<>();
        String type = null;
        boolean match = false;
        for (int i = 0; i < lines.size(); ++i) {
            String line = lines.get(i);
            if (BudUtil.isComponent(line)) {
                if (match) {
                    p.addComponent(this.parseComponent(type, tmp));
                    tmp.clear();
                }
                type = line.substring(1);
                match = true;
                continue;
            }
            tmp.add(line);
        }
        if (tmp.size() > 0) {
            p.addComponent(this.parseComponent(type, tmp));
        }
        return p;
    }

    /**
     * 解析组件
     *
     * @param type
     * @param content
     * @return
     */
    private Component parseComponent(String type, List<String> content) {
        return ParserRegistry.getInstance().findParser(type).parse(content);
    }
}
