package github.asan.bud.cli.component;

import github.asan.bud.cli.parser.component.ComponentTemplate;
import github.asan.bud.cli.pattern.AttrPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/22 4:34 PM
 * @history: 1.2019/6/22 created by jianfeng.zheng
 */
public class ComponentType {
    /**
     * 布局类
     */
    public static final String LAYOUT = "LAYOUT";
    /**
     * 组件类
     */
    public static final String COMPONENT = "COMPONENT";

    /**
     * 组件类型
     */
    private String type = COMPONENT;

    /**
     * 允许的名称(依次匹配)
     * 模板的名称要以"names[0]-theam.tpl"格式命名
     */
    private String name;
    /**
     * 定义组件的解析方式
     */
    private Map<String, AttrPattern> attrPatterns = new HashMap<>();

    /**
     * 渲染模板
     */
    private Map<String, ComponentTemplate> theamTemplates = new HashMap<>();

    /**
     * 默认子组件类型
     */
    private ComponentType defaultChildType;

    public ComponentType(String name) {
        this.name = name;
    }

    public ComponentType(String name, Map<String, AttrPattern> attrPatterns, Map<String, ComponentTemplate> templates) {
        this.name = name;
        this.attrPatterns = attrPatterns;
        this.theamTemplates = templates;
    }

    public String getName() {
        return name;
    }

    public void setName(String names) {
        this.name = names;
    }

    public Map<String, AttrPattern> getAttrPatterns() {
        return attrPatterns;
    }

    public void setAttrPatterns(Map<String, AttrPattern> attrPatterns) {
        this.attrPatterns = attrPatterns;
    }

    public Map<String, ComponentTemplate> getTheamTemplates() {
        return theamTemplates;
    }

    public void setTheamTemplates(Map<String, ComponentTemplate> theamTemplates) {
        this.theamTemplates = theamTemplates;
    }

    public void addAttrPatter(String name, AttrPattern attrPattern) {
        this.getAttrPatterns().put(name, attrPattern);
    }

    public void addAttrPatter(String[] names, AttrPattern attrPattern) {
        for (String name : names) {
            this.addAttrPatter(name, attrPattern);
        }
    }

    public void addTheamTempate(String theam, ComponentTemplate template) {
        this.getTheamTemplates().put(theam, template);
    }

    public ComponentType getDefaultChildType() {
        return defaultChildType;
    }

    public void setDefaultChildType(ComponentType defaultChildType) {
        this.defaultChildType = defaultChildType;
    }
}
