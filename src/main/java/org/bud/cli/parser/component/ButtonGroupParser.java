package org.bud.cli.parser.component;

import org.bud.cli.component.*;
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
 * @since: 2019/3/6 上午7:12
 * @history: 1.2019/3/6 created by jianfeng.zheng
 */
public class ButtonGroupParser implements IComponentParser {
    private static Map<String, AttrToken> keys;

    static {
        keys = new HashMap<>();

    }


    @Override
    public Component parse(List<String> lines) {
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String line : lines) {
            this.validate(line);
            String[] bs = line.split("\\s+");
            for (String s : bs) {
                buttonGroup.addButton(this.parseButton(s));
            }
        }
        return buttonGroup;
    }

    private Button parseButton(String line) {
        Button button = new Button();
        String[] ts = line.split("-");
        button.setName(ts[0]);
        int begin = 1;
        if (ts.length > 1) {
            button.setActionCode(ts[1]);
            begin = 2;
        }
        if (ts.length > 2) {
            button.setIcon(ts[2]);
            begin = 3;
        }
        for (int i = begin; i < ts.length; ++i) {
            String s = ts[i];
            AttrToken token = keys.get(s);
            if (token != null) {
                BudUtil.assignValue(button, token.getType(), token.getValue());
            }
        }
        return button;
    }

    public void validate(String line) {
        if (line == null || line.trim().length() == 0) {
            throw new BudException("解析错误");
        }
        String[] strs = line.split("-");
        if (strs.length < 1) {
            throw new BudException(line + " 参数不够 列名-编码-类型-可选(主键)");
        }
    }
}
