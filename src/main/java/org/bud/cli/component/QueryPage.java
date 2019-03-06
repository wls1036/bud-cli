package org.bud.cli.component;

import org.bud.cli.parser.component.ButtonGroupParser;
import org.bud.cli.parser.component.ColumnsParser;
import org.bud.cli.parser.component.FormParser;
import org.bud.cli.parser.component.TableParser;

import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/6 上午12:48
 * @history: 1.2019/3/6 created by jianfeng.zheng
 */
public class QueryPage extends Component {

    private Component table;
    private Component columns;
    private Component buttons;
    private Component form;

    public void addComponent(String type, Component component) {
        if ("表格".equals(type) || "Table".equalsIgnoreCase(type)) {
            this.table = component;
        } else if ("列".equals(type) ||
                "Col".equalsIgnoreCase(type) ||
                "Columnn".equalsIgnoreCase(type)) {
            this.columns = component;
        } else if ("按钮".equals(type) ||
                "Button".equalsIgnoreCase(type)) {
            this.buttons = component;
        } else if ("表单".equals(type) ||
                "Form".equalsIgnoreCase(type)) {
            this.form = component;
        }
    }


    @Override
    public String getType() {
        return null;
    }

    public Component getTable() {
        return table;
    }

    public void setTable(Component table) {
        this.table = table;
    }

    public Component getColumns() {
        return columns;
    }

    public void setColumns(Component columns) {
        this.columns = columns;
    }

    public Component getButtons() {
        return buttons;
    }

    public void setButtons(Component buttons) {
        this.buttons = buttons;
    }

    public Component getForm() {
        return form;
    }

    public void setForm(Component form) {
        this.form = form;
    }
}
