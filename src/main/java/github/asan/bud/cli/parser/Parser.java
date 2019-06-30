package github.asan.bud.cli.parser;

import com.alibaba.fastjson.JSON;
import github.asan.bud.cli.component.Component;
import github.asan.bud.cli.component.ComponentType;
import github.asan.bud.cli.component.ComponentTypeFactory;
import github.asan.bud.cli.parser.component.ComponentAttribute;
import github.asan.bud.cli.parser.component.ComponentConfig;
import github.asan.bud.cli.parser.component.ComponentMeta;
import github.asan.bud.cli.parser.component.ComponentReader;
import github.asan.bud.cli.parser.page.PageLine;
import github.asan.bud.cli.parser.page.PageReader;
import github.asan.bud.cli.pattern.AttrPattern;
import github.asan.bud.cli.pattern.AttrPatternFactory;
import github.asan.bud.cli.util.KeyValue;
import org.bud.cli.util.BudUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/30 10:50 AM
 * @history: 1.2019/6/30 created by jianfeng.zheng
 */
public class Parser {
    private PageReader pageReader;
    private ComponentReader componentReader;
    private ComponentTypeFactory ctFactory = ComponentTypeFactory.getInstance();

    private Map<String, ComponentMeta> components;
    private List<Component> pages;

    public Parser() {
        this.init();
    }

    private void init() {
        this.pageReader = new PageReader();
        this.componentReader = new ComponentReader();
    }

    /**
     * 解析页面
     */
    public void parser() {
        //1.加载组件信息
        this.components = componentReader.loadFromClassPath();
        //2.加载用户md文档信息
        this.pages = pageReader.readPages("./target/classes");
        //3.注册组件类型
        this.registComponentType();
        //4.解析所有的页面
        this.parsePages();

        // System.out.println(JSON.toJSONString(this.components));
        System.out.println(JSON.toJSONString(this.pages));
    }

    /**
     * 注册组件类型
     */
    private void registComponentType() {
        //解析组件
        for (String name : this.components.keySet()) {
            ComponentMeta meta = this.components.get(name);
            ComponentType type = new ComponentType(name);
            type.setTheamTemplates(meta.getTemplates());
            ComponentConfig config = meta.getConfig();
            for (ComponentAttribute attr : config.getAttributes()) {
                Map<String, String> pts = attr.getPattern();
                for (String key : pts.keySet()) {
                    if ("list".equals(key)) {
                        type.addAttrPatter(attr.getNames(),
                                AttrPatternFactory.getInstance().registStaticListPattern(pts.get(key)));
                    } else if ("regx".equals(key)) {
                        type.addAttrPatter(attr.getNames(),
                                AttrPatternFactory.getInstance().registRegxPattern(pts.get(key)));
                    }
                }
            }
            ComponentTypeFactory.getInstance().registComponentType(type);
        }
        //设置默认的组件类型
        for (String name : this.components.keySet()) {
            ComponentMeta meta = this.components.get(name);
            if (meta.getConfig().getDefaultChildType() == null) {
                continue;
            }
            for (String n : meta.getNames()) {
                ComponentType ct = ctFactory.findType(n);
                ct.setDefaultChildType(ctFactory.findType(meta.getConfig().getDefaultChildType()));
            }
        }
    }

    /**
     * 解析所有的页面
     */
    private void parsePages() {
        for (Component item : this.pages) {
            item.setComponentType(ComponentTypeFactory.getInstance().findType("page"));
            this.parseComponent(item);
        }
    }


    /**
     * 解析单个组件
     *
     * @param component
     * @return
     */
    public Component parseComponent(Component component) {
        List<PageLine> lines = component.getRawLines();
        for (int i = 1; i < lines.size(); ++i) {
            PageLine line = lines.get(i);
            KeyValue<Integer, List<PageLine>> result = null;
            if (line.isContainer() || line.isFragment()) {
                //解析布局类
                result = this.containerEnd(i, lines);
                Component child = this.buildFromPageLines(result.value(), component.getComponentType().getDefaultChildType());
                child = this.parseComponent(child);
                component.addChild(child);
            } else if (line.isComponent()) {
                //解析组件类
                result = this.componentEnd(i, lines);
                Component child = this.buildFromPageLines(result.value(), component.getComponentType().getDefaultChildType());
                child = this.parseComponent(child);
                component.addChild(child);
            } else if (line.isRemark()) {
                //解析注释类
                result = this.remarkEnd(i, lines);
                component.setRemark(BudUtil.combinePageLines(result.value()));
                result.key(result.key() + 1);
            }
            i = result.key() - 1;
        }
        return component;
    }

    /**
     * 构建组件
     *
     * @param lines
     * @param defaultType
     * @return
     */
    private Component buildFromPageLines(List<PageLine> lines, ComponentType defaultType) {
        Component component = new Component();
        component.setRawLines(lines);
        PageLine first = lines.get(0);
        component = this.parseComponentAttribute(first, component, defaultType);
        return component;
    }

    /**
     * 解析单行
     *
     * @param line
     * @param component
     * @return
     */
    private Component parseComponentAttribute(PageLine line, Component component, ComponentType defaultType) {
        String content = line.getContent();
        String[] ss = content.split("-");
        ComponentType ct;
        if (line.isFragment()) {
            ct = ctFactory.fragmentComponent();
        } else {
            ct = ctFactory.findType(ss[0]);
        }
        int start = 1;
        if (ct == null) {
            ct = defaultType;
            start = 0;
        }
        component.setComponentType(ct);
        for (int i = start; i < ss.length; ++i) {
            String s = ss[i];
            component = this.parseAttribute(s, component);
        }
        return component;
    }

    /**
     * 查找layout结束符
     *
     * @param start
     * @param lines
     * @return
     */
    private KeyValue<Integer, List<PageLine>> containerEnd(int start, List<PageLine> lines) {
        List<PageLine> result = new ArrayList<>();
        KeyValue<Integer, List<PageLine>> kv = new KeyValue<>();
        result.add(lines.get(start));
        start = start + 1;
        kv.value(result);
        kv.key(start);
        for (int i = start; i < lines.size(); ++i) {
            PageLine line = lines.get(i);
            if (line.isContainer() || line.isFragment()) {
                kv.key(i);
                kv.value(result);
                return kv;
            } else {
                result.add(line);
            }
        }
        kv.key(lines.size());
        kv.value(result);
        return kv;
    }

    /**
     * 查找layout结束符
     *
     * @param start
     * @param lines
     * @return
     */
    private KeyValue<Integer, List<PageLine>> componentEnd(int start, List<PageLine> lines) {
        List<PageLine> result = new ArrayList<>();
        KeyValue<Integer, List<PageLine>> kv = new KeyValue<>();
        result.add(lines.get(start));
        start = start + 1;
        kv.value(result);
        kv.key(start);
        for (int i = start; i < lines.size(); ++i) {
            PageLine line = lines.get(i);
            if (line.isAnyComponet()) {
                kv.key(i);
                kv.value(result);
                return kv;
            } else {
                result.add(line);
            }
            if (line.isRemark()) {
                KeyValue<Integer, List<PageLine>> kv1 = this.remarkEnd(i, lines);
                i = kv1.key();
                result.addAll(kv1.value());
                continue;
            }
        }
        kv.key(lines.size());
        kv.value(result);
        return kv;
    }

    /**
     * 查找备注结束符
     *
     * @param start
     * @param lines
     * @return
     */
    private KeyValue<Integer, List<PageLine>> remarkEnd(int start, List<PageLine> lines) {
        KeyValue<Integer, List<PageLine>> kv = new KeyValue<>();
        List<PageLine> result = new ArrayList<>();
        start = start + 1;
        kv.value(result);
        kv.key(start);
        for (int i = start; i < lines.size(); ++i) {
            PageLine line = lines.get(i);
            if (line.isRemark()) {
                kv.key(i);
                kv.value(result);
                return kv;
            } else {
                result.add(line);
            }
        }
        kv.key(lines.size());
        kv.value(result);
        return kv;
    }


    /**
     * 解析属性
     *
     * @param s
     * @param component
     * @return
     */
    private Component parseAttribute(String s, Component component) {
        String key = null;
        String value = null;
        String[] ss = s.split(":");
        Map<String, AttrPattern> attrPatterns = component.getComponentType().getAttrPatterns();
        if (ss.length > 1) {
            key = ss[0];
            value = ss[1];
        } else {
            for (String k : attrPatterns.keySet()) {
                AttrPattern pt = attrPatterns.get(k);
                if (pt.match(s)) {
                    key = k;
                    value = s;
                }
            }
        }
        component.setAttribute(key, value);
        return component;
    }
}
