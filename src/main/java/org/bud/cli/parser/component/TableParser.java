package org.bud.cli.parser.component;

import org.bud.cli.component.Component;
import org.bud.cli.component.Table;
import org.bud.cli.exception.BudException;
import org.bud.cli.parser.IComponentParser;
import org.bud.cli.util.BudUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description: url-单选/多选-不分页
 * @author: jianfeng.zheng
 * @since: 2019/3/6 上午1:02
 * @history: 1.2019/3/6 created by jianfeng.zheng
 */
public class TableParser implements IComponentParser {

    private static Map<String, AttrToken> keys;

    static {
        keys = new HashMap<>();
        keys.put("单选", new AttrToken("selection", "single"));
        keys.put("多选", new AttrToken("selection", "mul"));
        keys.put("分页", new AttrToken("page", "page"));
        keys.put("不分页", new AttrToken("page", "no page"));

        keys.put("single", new AttrToken("selection", "single"));
        keys.put("mul", new AttrToken("selection", "mul"));
        keys.put("page", new AttrToken("page", "page"));
        keys.put("no page", new AttrToken("page", "no page"));

    }


    @Override
    public Component parse(List<String> lines) {
        Table table = new Table();
        if (lines != null && lines.size() > 0) {
            String line = lines.get(0);
            String[] ts = line.split("-");
            for (String s : ts) {
                AttrToken token = keys.get(s);
                if (token != null) {
                    BudUtil.assignValue(table, token.getType(), token.getValue());
                } else {
                    table.setActionURL(s);
                }
            }
        }
        return table;
    }
}
