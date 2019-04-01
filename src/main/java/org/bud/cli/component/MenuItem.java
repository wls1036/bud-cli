package org.bud.cli.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description: 我的草稿-component-icon
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:14
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class MenuItem extends Component {
    /**
     * 标题
     */
    private String title;
    /**
     * 路由的路径
     */
    private String router;
    /**
     * 图标
     */
    private String icon;
    /**
     * 展示类型
     */
    private String showType = "item";
    /**
     * 打开类型
     */
    private String target;

    private List<MenuItem> menuItems = new ArrayList<>();

    @Override
    public String getType() {
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
        if (router.startsWith("http://")) {
            target = "link";
        }
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
