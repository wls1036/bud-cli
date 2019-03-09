package org.bud.cli.parser.component;

import org.bud.cli.component.Component;
import org.bud.cli.component.Form;
import org.bud.cli.component.Row;
import org.bud.cli.parser.IComponentParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/6 上午7:17
 * @history: 1.2019/3/6 created by jianfeng.zheng
 */
public class FormParser implements IComponentParser {
    @Override
    public Component parse(List<String> lines) {
        Form form = new Form();
        FormItemParser itemParser = new FormItemParser();
        for (String line : lines) {
            String[] cs = line.split("\\s+");
            Row row = new Row();
            for (String c : cs) {
                List items = new ArrayList();
                items.add(c);
                row.addComponent(itemParser.parse(items));
            }
            form.addRow(row);
        }
        return form;
    }
}
