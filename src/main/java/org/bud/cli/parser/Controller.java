package org.bud.cli.parser;

import com.alibaba.fastjson.JSON;
import org.bud.cli.component.Page;
import org.bud.cli.parser.component.MenuParser;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午1:41
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Controller {

    public void run() {
        this.registComponentParser();
        PageFile pf = Reader.readFile("/Users/asan/workspace/ermei/menu.bud");
        Parser parser = new Parser(pf);
        Page page = parser.parseSinglePage();
        System.out.println(JSON.toJSONString(page));


    }

    private void registComponentParser() {
        ParserRegistry registry = ParserRegistry.getInstance();
        registry.registry("菜单", new MenuParser());
        registry.registry("menu", new MenuParser());
    }
}
