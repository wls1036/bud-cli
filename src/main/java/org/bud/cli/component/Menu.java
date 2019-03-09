package org.bud.cli.component;


import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:13
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Menu extends Component{
    private List<MenuItem> menuItems=new ArrayList<>();


    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void addMenuItem(MenuItem menuItem){
        this.menuItems.add(menuItem);
    }
}
