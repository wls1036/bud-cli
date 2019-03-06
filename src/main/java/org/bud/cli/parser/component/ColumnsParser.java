package org.bud.cli.parser.component;

import org.bud.cli.component.Column;
import org.bud.cli.component.Columns;
import org.bud.cli.component.Component;
import org.bud.cli.exception.BudException;
import org.bud.cli.parser.IComponentParser;
import org.bud.cli.util.BudUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/6 上午6:58
 * @history: 1.2019/3/6 created by jianfeng.zheng
 */
public class ColumnsParser implements IComponentParser {
    private static Map<String, AttrToken> keys;

    static {
        keys = new HashMap<>();
        keys.put("主键", new AttrToken("key", "true"));
        keys.put("key", new AttrToken("key", "true"));

    }


    @Override
    public Component parse(List<String> lines) {
        Columns cols = new Columns();
        for (String line : lines) {
            this.validate(line);
            Column col = new Column();
            String[] ts = line.split("-");
            col.setName(ts[0]);
            col.setCode(ts[1]);
            int begin = 2;
            if (ts.length > 2) {
                col.setType(ts[2]);
                begin = 3;
            }
            for (int i = begin; i < ts.length; ++i) {
                String s = ts[i];
                AttrToken token = keys.get(s);
                if (token != null) {
                    BudUtil.assignValue(col, token.getType(), token.getValue());
                }
            }
            cols.addColumn(col);
        }
        return cols;
    }

    public void validate(String line) {
        if (line == null || line.trim().length() == 0) {
            throw new BudException("解析错误");
        }
        String[] strs = line.split("-");
        if (strs.length < 2) {
            throw new BudException(line + " 参数不够 列名-编码-类型-可选(主键)");
        }
    }
}
