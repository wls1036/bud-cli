package org.bud.cli.util;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午2:21
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class BudUtil {

    public static Boolean isComponent(String line) {
        return line.matches("^#[^#]*");
    }

    public static Boolean isSubComponent(String line) {
        return line.matches("^##[^#]*");
    }

    public static void main(String[]cmd){
        System.out.println(BudUtil.isSubComponent("##菜单"));
    }
}
