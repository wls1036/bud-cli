package github.asan.bud.cli.component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/30 2:27 PM
 * @history: 1.2019/6/30 created by jianfeng.zheng
 */
public class ComponentTypeFactory {

    private static ComponentTypeFactory instance;

    private Map<String, ComponentType> componentTypes = new HashMap<>();

    public static ComponentTypeFactory getInstance() {
        if (instance == null) {
            instance = new ComponentTypeFactory();
        }
        return instance;
    }

    public void registComponentType(ComponentType ct) {
        componentTypes.put(ct.getName(), ct);
    }

    public ComponentType findType(String name) {
        return componentTypes.get(name);
    }

    public ComponentType fragmentComponent() {
        return componentTypes.get("fragment");
    }

}
