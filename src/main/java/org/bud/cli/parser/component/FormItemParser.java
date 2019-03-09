package org.bud.cli.parser.component;

import org.bud.cli.component.Component;
import org.bud.cli.component.Input;
import org.bud.cli.exception.BudException;
import org.bud.cli.parser.IComponentParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/9 下午5:57
 * @history: 1.2019/3/9 created by jianfeng.zheng
 */
public class FormItemParser implements IComponentParser {
    private static Map<String, String> keys;

    static {
        keys = new HashMap<>();
        keys.put("文本", "org.bud.cli.component.Input");
        keys.put("string", "org.bud.cli.component.Input");
        keys.put("长文本", "org.bud.cli.component.TextArea");
        keys.put("text", "org.bud.cli.component.TextArea");

        keys.put("日期", "org.bud.cli.component.Date");
        keys.put("date", "org.bud.cli.component.Date");
        keys.put("时间", "org.bud.cli.component.Date");
        keys.put("datetime", "org.bud.cli.component.Date");

        keys.put("列表", "org.bud.cli.component.Selection");
        keys.put("list", "org.bud.cli.component.Selection");
        keys.put("单选", "org.bud.cli.component.Radio");
        keys.put("radio", "org.bud.cli.component.Radio");

        keys.put("多选", "org.bud.cli.component.Check");
        keys.put("checkbox", "org.bud.cli.component.Check");
        keys.put("值列表", "org.bud.cli.component.Lov");
        keys.put("lov", "org.bud.cli.component.Lov");

    }

    @Override
    public Component parse(List<String> lines) {
        Component component = null;
        String line = lines.get(0);
        this.validate(line);
        String[] attrs = line.split("-");
        if (attrs.length == 1) {
            component = new Input();
        }
        if (attrs.length == 2) {
            String s = attrs[1];
            String clazz = keys.get(s.toLowerCase());
            if (clazz != null) {
                try {
                    component = (Component) Class.forName(clazz).newInstance();
                } catch (Exception e) {
                    throw new BudException(e);
                }
            } else {
                component = new Input();
            }
        }
        if (attrs.length >= 3) {
            String clazz = keys.get(attrs[2].toLowerCase());
            String code = attrs[1];
            if (clazz == null) {
                throw new BudException("类型:" + attrs[2] + " 不合法");
            }
            try {
                component = (Component) Class.forName(clazz).newInstance();
                component.setCode(code);
            } catch (Exception e) {
                throw new BudException(e);
            }
            if (attrs.length > 3) {
                component.setAttrExpression(attrs[3]);
            }
        }
        component.setSrc(line);
        component.setName(attrs[0]);
        return component;
    }


    private void validate(String line) {
        if (line == null || line.trim().length() == 0) {
            throw new BudException("解析错误");
        }
    }
}
