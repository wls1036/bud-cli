package org.bud.cli.util;

import github.asan.bud.cli.parser.page.PageLine;
import org.bud.cli.exception.BudException;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
     *
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

    public static String readText(File f) {
        String content = "";
        try {
            FileInputStream fin = new FileInputStream(f);
            byte[] data = new byte[fin.available()];
            fin.read(data);
            fin.close();
            content = new String(data, "UTF-8");
        } catch (Exception e) {
            throw new BudException(e.getMessage());
        }
        return content;
    }

    /**
     * 逐行读取文本文件
     *
     * @param f
     * @return
     */
    public static List<PageLine> readTextLines(File f) {
        String content = BudUtil.readText(f);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes())));
        String line = null;
        List<PageLine> lines = new ArrayList<>();
        try {
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    lines.add(new PageLine(line));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lines;
    }

    public static String getRawFileName(String fileName) {
        int index = fileName.indexOf(".");
        if (index > 0) {
            return fileName.substring(0, index);
        }
        return fileName;
    }

    public static String combinePageLines(List<PageLine> lines) {
        StringBuffer buf = new StringBuffer();
        for (PageLine line : lines) {
            buf.append(line.getLine());
            buf.append("\r\n");
        }
        return buf.toString();
    }
}
