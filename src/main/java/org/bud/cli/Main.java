package org.bud.cli;

import com.alibaba.fastjson.JSON;
import github.asan.bud.cli.parser.Parser;
import github.asan.bud.cli.parser.component.ComponentReader;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.bud.cli.component.Button;
import org.bud.cli.component.Menu;
import org.bud.cli.component.MenuItem;
import org.bud.cli.component.QueryPage;
import org.bud.cli.template.BudTemplateEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午2:50
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Main {

    public static void main(String[] cmd) {
        Parser parser=new Parser();
        parser.parser();
    }

    public static void templateEngine() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        // 获取模板文件
        Template t = ve.getTemplate("hello.vm");
        // 设置变量
        VelocityContext ctx = new VelocityContext();
        ctx.put("name", "Velocity");
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        ctx.put("list", list);
        // 输出
        StringWriter sw = new StringWriter();
        //t.merge(ctx,sw);

        ve.evaluate(ctx, sw, "bud", "$name");
        System.out.println(sw.toString());
    }


    public static void test01(Object data) {
        BudTemplateEngine engine = new BudTemplateEngine();
        QueryPage page = new QueryPage();
        page.setButtons(new Button());
        //engine.setEngineData(page);
        List<MenuItem> items = new ArrayList<>();
        MenuItem m1 = new MenuItem();
        m1.setTitle("Dashboard");
        m1.setShowType("header");
        System.out.println(JSON.toJSONString(m1));
        MenuItem m2 = new MenuItem();
        m2.setTitle("Dashboard");
        m2.setShowType("item");
        m2.setRouter("Dashboard");
        m2.setIcon("list");
        MenuItem m3 = new MenuItem();
        m3.setTitle("Dashboard");
        m3.setShowType("item");
        m3.setIcon("list");
        m3.setRouter("Helen");
        items.add(m1);
        items.add(m2);
        items.add(m3);
        Menu m = new Menu();
        m.setMenuItems(items);
        engine.setEngineData(data);
        engine.evaluateFromPath("/Users/asan/workspace/asan/vue-material-admin/src/api/menu_vel.vm", "/Users/asan/workspace/asan/vue-material-admin/src/api/menu.js");
        engine.evaluateFromPath("/Users/asan/workspace/asan/vue-material-admin/src/router/paths.vm", "/Users/asan/workspace/asan/vue-material-admin/src/router/paths.js");
    }
}
