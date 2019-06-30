package github.asan.bud.cli.parser.component;

import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/29 5:39 PM
 * @history: 1.2019/6/29 created by jianfeng.zheng
 */
public class ComponentAttribute {
    private String[] names;
    private Map<String,String> pattern;

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public Map<String,String> getPattern() {
        return pattern;
    }

    public void setPattern(Map<String,String> pattern) {
        this.pattern = pattern;
    }
}
