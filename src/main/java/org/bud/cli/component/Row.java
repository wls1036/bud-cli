package org.bud.cli.component;


import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:22
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Row {
    private List<Component> components = new ArrayList<>();

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public void addComponent(Component component) {
        components.add(component);
    }
}
