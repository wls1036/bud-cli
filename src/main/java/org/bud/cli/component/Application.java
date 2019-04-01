package org.bud.cli.component;

import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/4/1 上午12:23
 * @history: 1.2019/4/1 created by jianfeng.zheng
 */
public class Application {
    private List<MenuItem> menuItems;

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public static Application parseFromPages(List<Page> pages) {
        Application app = new Application();
        for (Page page : pages) {
            List<Component> components = page.getComponents();
            for (Component p : components) {
                if (p instanceof Menu) {
                    app.setMenuItems(((Menu) p).getMenuItems());
                }
            }
        }
        return app;
    }
}
