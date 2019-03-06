package org.bud.cli.component;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:20
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Button extends Component{
    private String name;
    private String actionCode;
    private String icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String getType() {
        return null;
    }
}
