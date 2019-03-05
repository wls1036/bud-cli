package org.bud.cli.parser.component;

import org.bud.cli.component.Component;
import org.bud.cli.component.Menu;
import org.bud.cli.component.MenuItem;
import org.bud.cli.exception.BudException;
import org.bud.cli.parser.IComponentParser;

import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description: 菜单解析函数
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午2:14
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */

/**
 * 示例
 * 我的草稿-component-icon
 * +我的待办-component-icon
 * 系统管理-component-icon
 * +用户管理-component-icon
 */
public class MenuParser implements IComponentParser {

    /**
     * 解析菜单
     * @param lines
     * @return
     */
    @Override
    public Component parse(List<String> lines) {
        Menu menu = new Menu();
        MenuItem item = null;
        for (int i = 0; i < lines.size(); ++i) {
            String line = lines.get(i);
            if (line.startsWith("+")) {
                line=line.substring(1);
                item.addMenuItem(this.parseMetuItem(line));
            } else {
                item = this.parseMetuItem(line);
                menu.addMenuItem(item);
            }
        }
        return menu;
    }

    /**
     * 解析菜单项
     * @param line
     * @return
     */
    private MenuItem parseMetuItem(String line) {
        this.validate(line);
        MenuItem item = new MenuItem();
        String[] strs = line.split("-");
        item.setTitle(strs[0]);
        item.setRouter(strs[1]);
        if (strs.length > 2) {
            item.setIcon(strs[2]);
        }
        return item;
    }

    /**
     * 校验语法
     * @param line
     */
    private void validate(String line) {
        if (line == null || line.trim().length() == 0) {
            throw new BudException("解析错误");
        }
        String[] strs = line.split("-");
        if (strs.length < 2) {
            throw new BudException(line + " 参数不够 菜单名称-菜单页面-图标(可选)");
        }
    }
}
