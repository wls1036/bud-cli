package org.bud.cli.component;

import com.sun.tools.javac.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:16
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Table extends Component {
    private String selection = "single";
    private String actionURL;
    private String page = "page";
    private Columns columns;

    @Override
    public String getType() {
        return null;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getActionURL() {
        return actionURL;
    }

    public void setActionURL(String actionURL) {
        this.actionURL = actionURL;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Columns getColumns() {
        return columns;
    }

    public void setColumns(Columns columns) {
        this.columns = columns;
    }

    public void setValue(String type, String value) {
        if ("selection".equalsIgnoreCase(type)) {
            this.selection = value;
        } else if ("page".equalsIgnoreCase(type)) {
            this.page = value;
        }else if("url".equalsIgnoreCase(type)){
            this.setActionURL(value);
        }
    }
}
