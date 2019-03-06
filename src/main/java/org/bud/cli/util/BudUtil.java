package org.bud.cli.util;

import java.lang.reflect.Method;

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

    public static void main(String[] cmd) {
        System.out.println(BudUtil.isSubComponent("##菜单"));
    }

    /**
     * 动态赋值
     * @param clazz
     * @param field
     * @param value
     */
    public static void assignValue(Object clazz, String field, String value) {
        try {
            Method method = clazz.getClass().getDeclaredMethod("set" + field.substring(0, 1).toUpperCase() + field.substring(1), String.class);
            method.invoke(clazz, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
