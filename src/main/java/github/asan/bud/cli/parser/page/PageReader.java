package github.asan.bud.cli.parser.page;

import github.asan.bud.cli.component.Component;
import github.asan.bud.cli.component.ComponentType;
import org.bud.cli.util.BudUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/29 8:49 PM
 * @history: 1.2019/6/29 created by jianfeng.zheng
 */
public class PageReader {

    /**
     * 读取指定路径下所有的bud页面
     *
     * @param path
     * @return
     */
    public List<Component> readPages(String path) {
        List<Component> pages = new ArrayList<>();
        File f = new File(path);
        File[] files = f.listFiles();
        for (File ff : files) {
            String name = ff.getName();
            if (!name.endsWith(".md") && !name.endsWith(".bud")) {
                continue;
            }
            pages.addAll(this.readPage(ff));
        }
        return pages;
    }

    /**
     * 读取单个文件页面
     *
     * @param f
     * @return
     */
    private List<Component> readPage(File f) {
        List<Component> components = new ArrayList<>();
        List<PageLine> lines = BudUtil.readTextLines(f);
        List<PageLine> contents = new ArrayList<>();
        if (!lines.get(0).isPage()) {
            contents.add(new PageLine(">" + BudUtil.getRawFileName(f.getName())));
        } else {
            contents.add(lines.get(0));
        }
        for (int i = 1; i < lines.size(); ++i) {
            PageLine line = lines.get(i);
            if (line.isPage()) {
                components.add(new Component(contents));
                contents.clear();
            }
            contents.add(line);
        }
        if (!contents.isEmpty()) {
            components.add(new Component(contents));
        }
        return components;
    }

}
