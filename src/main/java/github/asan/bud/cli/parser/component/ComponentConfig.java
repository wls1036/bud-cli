package github.asan.bud.cli.parser.component;

import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/29 5:38 PM
 * @history: 1.2019/6/29 created by jianfeng.zheng
 */
public class ComponentConfig {
    private String[]names;
    private List<ComponentAttribute> attributes;
    private String  defaultChildType;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public List<ComponentAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ComponentAttribute> attributes) {
        this.attributes = attributes;
    }

    public String getDefaultChildType() {
        return defaultChildType;
    }

    public void setDefaultChildType(String defaultChildType) {
        this.defaultChildType = defaultChildType;
    }
}
