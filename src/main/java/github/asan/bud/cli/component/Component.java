package github.asan.bud.cli.component;

import github.asan.bud.cli.parser.page.PageLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/22 4:15 PM
 * @history: 1.2019/6/22 created by jianfeng.zheng
 */
public class Component {
    /**
     * 组件名称，前端组件可将该字段作为显示名称
     */
    private String name;

    /**
     * 组件编号，不允许为中文，作为前端组件跟后台交互到标识符
     */
    private String code;
    /**
     * 组件的位置，系统根据bud文件中的定义自动解析
     */
    private Location location;
    /**
     * 存储组件的额外信息，定义在`xxx`里的信息，如果为json就解析为map否则解析为字符串
     */
    private Map<String, String> tag;

    /**
     * 组件的属性，系统根据attrPatterns解析出组件的属性
     */
    private Map<String, String> attributes=new HashMap<>();

    /**
     * 子组件列表
     */
    private List<Component> childs;

    /**
     * 说明
     * ```
     * ```
     * 中内容
     * 1.如果是json，一起解析到tag里（tag优先级较高）
     * 判断的依据
     * 如果第一行第一个字母是{解析为json
     * 如果第一行满足`key=value`格式解析为properties
     * remark存储原始信息
     */
    private String remark;

    /**
     * 存储该组件原始代码信息
     */
    private String raw;

    private List<PageLine> rawLines = new ArrayList<>();

    private ComponentType componentType;

    public Component() {

    }

    public Component(List<PageLine> lines) {
        this.rawLines.addAll(lines);
    }

//    /**
//     * 组件渲染
//     *
//     * @param theam 主题
//     * @return 组件渲染的结果
//     */
//    public RenderResult render(String theam) {
//        return componentType.render(theam, this);
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<String, String> getTag() {
        return tag;
    }

    public void setTag(Map<String, String> tag) {
        this.tag = tag;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<Component> getChilds() {
        if (childs == null) {
            childs = new ArrayList<>();
        }
        return childs;
    }

    public void setChilds(List<Component> childs) {
        this.childs = childs;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public List<PageLine> getRawLines() {
        return rawLines;
    }

    public void setRawLines(List<PageLine> rawLines) {
        this.rawLines.addAll(rawLines);
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }


    public void setAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

    public void addChild(Component component) {
        this.getChilds().add(component);
    }
}
