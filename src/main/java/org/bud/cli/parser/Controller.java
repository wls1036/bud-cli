package org.bud.cli.parser;

import com.alibaba.fastjson.JSON;
import org.bud.cli.component.Application;
import org.bud.cli.component.Page;
import org.bud.cli.parser.component.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午1:41
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Controller {

    public Object run() {
        this.registComponentParser();
        PageFile pf = Reader.readFile("/Users/asan/workspace/ermei/menu.bud");
        Parser parser = new Parser(pf);
        Page page = parser.parseSinglePage();
        //System.out.println(JSON.toJSONString(page));
        List<Page> pages = new ArrayList<>();
        pages.add(page);
        return Application.parseFromPages(pages);
    }

    private void registComponentParser() {
        ParserRegistry registry = ParserRegistry.getInstance();
        registry.registry("菜单", new MenuParser());
        registry.registry("menu", new MenuParser());
        registry.registry("查询页", new QueryPageParser());
        registry.registry("QueryPage", new QueryPageParser());
        registry.registry("表格", new TableParser());
        registry.registry("Table", new TableParser());

        registry.registry("列", new ColumnsParser());
        registry.registry("Col", new ColumnsParser());
        registry.registry("Column", new ColumnsParser());
        registry.registry("按钮", new ButtonGroupParser());
        registry.registry("Button", new ButtonGroupParser());
        registry.registry("表单", new FormParser());
        registry.registry("Form", new FormParser());
    }
}
