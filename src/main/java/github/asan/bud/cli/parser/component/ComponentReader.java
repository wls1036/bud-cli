package github.asan.bud.cli.parser.component;


import com.alibaba.fastjson.JSON;
import org.bud.cli.exception.BudException;
import org.bud.cli.util.BudUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/6/29 5:52 PM
 * @history: 1.2019/6/29 created by jianfeng.zheng
 */
public class ComponentReader {


    public static final String START = "target/classes";
    //public static final String START = ".";

    public Map<String, ComponentMeta> loadFromClassPath() {
        Map<String, ComponentMeta> components = new HashMap<>();
        File f = new File(START);
        File[] fs = f.listFiles();
        for (File ff : fs) {
            if (!ff.isDirectory() || !"components".equals(ff.getName())) {
                continue;
            }
            File[] f1 = ff.listFiles();
            for (File c : f1) {
                ComponentMeta meta = this.readComponent(c);
                this.assertComponent(c.getName(), meta);
                for (String s : meta.getNames()) {
                    components.put(s, meta);
                }
            }
            break;
        }
        return components;
    }

    /**
     * 读取单个组件
     */
    private ComponentMeta readComponent(File f) {
        ComponentMeta com = new ComponentMeta();
        File[] files = f.listFiles();
        for (File fi : files) {
            if (fi.getName().equals("config.json")) {
                com.setConfig(this.readComponentConfig(fi));
            } else {
                String name = fi.getName();
                com.addTemplate(name.substring(0, name.indexOf(".")), this.readComponentTemplate(fi));
            }
        }
        return com;
    }

    /**
     * 校验组件合法性
     *
     * @param name
     * @param com
     */
    private void assertComponent(String name, ComponentMeta com) {
        if (com.getConfig() == null) {
            throw new BudException("组件:" + name + "缺少config.json文件");
        }
        if (com.getConfig().getNames() == null) {
            throw new BudException("组件:" + name + "config 文件缺少names参数");
        }
        if (com.getDefaultTemplate() == null) {
            throw new BudException("组件:" + name + "缺少默认样式模板,请配置default.vm文件");
        }
    }

    /**
     * 读取主题模板文件
     *
     * @param f
     * @return
     */
    private ComponentTemplate readComponentTemplate(File f) {
        ComponentTemplate t = new ComponentTemplate();
        t.setContent(BudUtil.readText(f));
        return t;
    }

    /**
     * 读取config文件
     *
     * @param f
     * @return
     */
    private ComponentConfig readComponentConfig(File f) {
        return JSON.parseObject(BudUtil.readText(f), ComponentConfig.class);
    }


    public static void main(String[] cmd) {
        ComponentReader r = new ComponentReader();
        r.loadFromClassPath();
    }

}
