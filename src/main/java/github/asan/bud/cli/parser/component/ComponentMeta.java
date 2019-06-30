package github.asan.bud.cli.parser.component;


import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/29 5:42 PM
 * @history: 1.2019/6/29 created by jianfeng.zheng
 */
public class ComponentMeta {
    private Map<String, ComponentTemplate> templates = new HashMap<>();
    private ComponentConfig config;

    public String[] getNames() {
        return config.getNames();
    }

    public ComponentConfig getConfig() {
        return config;
    }

    public void setConfig(ComponentConfig config) {
        this.config = config;
    }

    public void addTemplate(String theam, ComponentTemplate template) {
        templates.put(theam, template);
    }

    public ComponentTemplate getDefaultTemplate() {
        return templates.get("default");
    }

    public Map<String, ComponentTemplate> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, ComponentTemplate> templates) {
        this.templates = templates;
    }
}
