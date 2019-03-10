package org.bud.cli;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.bud.cli.component.Button;
import org.bud.cli.component.QueryPage;
import org.bud.cli.parser.Controller;
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
//        Controller controller = new Controller();
//        controller.run();

        Main.test01();
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


    public static void test01() {
        BudTemplateEngine engine = new BudTemplateEngine();
        QueryPage page = new QueryPage();
        page.setButtons(new Button());
        engine.setEngineData(page);
        engine.evaluateFromPath("data.txt","bb");
    }
}
