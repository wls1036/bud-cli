package org.bud.cli.parser.component;

import org.bud.cli.component.Component;
import org.bud.cli.component.QueryPage;
import org.bud.cli.parser.IComponentParser;
import org.bud.cli.parser.ParserRegistry;
import org.bud.cli.util.BudUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/6 上午12:41
 * @history: 1.2019/3/6 created by jianfeng.zheng
 */
public class QueryPageParser implements IComponentParser {


    @Override
    public Component parse(List<String> lines) {
        QueryPage page = new QueryPage();
        List<String> tmp = new ArrayList<>();
        String type = null;
        boolean match = false;
        for (int i = 0; i < lines.size(); ++i) {
            String line = lines.get(i);
            if (BudUtil.isSubComponent(line)) {
                if (match) {
                    page.addComponent(type, this.parseComponent(type, tmp));
                    tmp.clear();
                }
                match=true;
                type = line.substring(2);
                continue;
            }
            tmp.add(line);
        }
        if (tmp.size()>0) {
            page.addComponent(type, this.parseComponent(type, tmp));
        }
        return page;
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
