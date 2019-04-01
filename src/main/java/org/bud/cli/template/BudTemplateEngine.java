package org.bud.cli.template;

import com.alibaba.fastjson.JSON;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.bud.cli.exception.BudException;
import org.bud.cli.util.BudUtil;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/9 下午11:12
 * @history: 1.2019/3/9 created by jianfeng.zheng
 */
public class BudTemplateEngine {

    public static final int BUFFER_SIZE = 1024;

    private VelocityEngine engine;

    private Map<String, Object> engineData = new HashMap<>();

    private String basePath;

    private String sp = System.getProperty("file.separator");

    public BudTemplateEngine() {

    }

    /**
     * 设置上下文数据
     *
     * @param data
     */
    public void setEngineData(Object data) {
        this.toEngineData(data);
    }

    /**
     * 将文件夹里的模板生成
     *
     * @param from
     */
    public void evaluateFromPath(String from) {
        String to = System.getProperty("user.dir");
        this.evaluateFromPath(from, to);
    }

    /**
     * 将文件夹里的模板生成
     *
     * @param from
     * @param to
     */
    public void evaluateFromPath(String from, String to) {
        from = this.evaluatePath(from);
        if (basePath == null) {
            basePath = this.evaluateBashPath(from);
        }
        File f1 = new File(from);
        if (!f1.isDirectory()) {
            this.evaluateFromFile(f1, to);
        } else {
            File[] files = f1.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    this.evaluateFromPath(f.getPath(), to);
                } else {
                    this.evaluateFromFile(f, to);
                }
            }
        }
    }

    /**
     * 将模板file转换为to
     *
     * @param file
     * @param to
     */
    private void evaluateFromFile(File file, String to) {
        to = this.evaluatePath(to);
        this.mkdir(to);
        String toFile = this.getTargetFileName(file.getPath(), to);
        String tpl = BudUtil.readTemplateText(file);
        this.evaluate(tpl, this.engineData, toFile);
    }

    /**
     * 获取生成的文件名
     *
     * @param from
     * @param to
     * @return
     */
    private String getTargetFileName(String from, String to) {
        int index = to.lastIndexOf(sp);
        //to如果是文件直接返回
        if (index != -1 && to.substring(index).indexOf(".") != -1) {
            return to;
        }
        int size = basePath.length();
        String t = to + from.substring(size);
        return t;
    }


    /**
     * 从压缩文件中解压出文件
     *
     * @param zipName
     */
    public void evaluateFromZip(String zipName) {
        String to = System.getProperty("user.dir");
        this.evaluateFromZip(zipName, to);
    }

    /**
     * 从资源zip文件中读取模板信息
     *
     * @param zipName
     * @param to
     */
    public void evaluateFromZip(String zipName, String to) {
        InputStream fin = ClassLoader.getSystemResourceAsStream(zipName);
        this.evaluateFromZip(fin, to);

    }

    /**
     * 从输入流中读取文件
     * @param fin
     * @param to
     */
    public void evaluateFromZip(InputStream fin, String to) {
        ZipInputStream zin = new ZipInputStream(fin);
        ZipEntry ze = null;
        basePath = "";
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            while ((ze = zin.getNextEntry()) != null) {
                System.out.println(ze.getName() + zin.available());
                if (ze.isDirectory()) {
                    continue;
                }
                String name = ze.getName();
                name = "/" + name;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int size = 0;
                while ((size = zin.read(buf)) != -1) {
                    bos.write(buf, 0, size);
                }
                String s = bos.toString("utf-8");
                String fileName = this.getTargetFileName(name, to);
                this.evaluate(s, this.engineData, fileName);
            }
            zin.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BudException(e);
        }
    }

    /**
     * 处理路径
     *
     * @param path
     * @return
     */
    private String evaluatePath(String path) {
        if (path.endsWith(sp)) {
            path = path.substring(0, path.length() - 1);
        }
        if (path.indexOf(sp) == -1) {
            path = System.getProperty("user.dir") + sp + path;
        }
        return path;
    }

    /**
     * 处理基础路径
     *
     * @param path
     * @return
     */
    private String evaluateBashPath(String path) {
        File f = new File(path);
        if (f.isDirectory()) {
            return path;
        }
        int index = path.lastIndexOf(sp);
        if (index != -1) {
            return path.substring(0, index);
        }
        return System.getProperty("user.dir");
    }


    /**
     * 模版转换
     *
     * @param tpl
     * @param data
     * @param path
     */
    public void evaluate(String tpl, Map<String, Object> data, String path) {
        this.initEngie();
        VelocityContext context = new VelocityContext();
        for (String key : data.keySet()) {
            context.put(key, data.get(key));
        }
        System.out.println(JSON.toJSONString(data));
        this.mkdir(path);
        File output = new File(path);
        try {
            FileWriter writer = new FileWriter(output);
            engine.evaluate(context, writer, "bud", tpl);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建目录
     *
     * @param path
     */
    private void mkdir(String path) {
        int index = path.lastIndexOf(sp);
        if (index > 0) {
            File f = new File(path.substring(0, index));
            f.mkdirs();
        }
    }

    /**
     * 模板初始化
     */
    private void initEngie() {
        if (engine == null) {
            engine = new VelocityEngine();
            engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            engine.init();
        }
    }

    /**
     * 将pojo数据转换为map
     *
     * @param ob
     */
    private void toEngineData(Object ob) {
        if (ob instanceof Map) {
            this.engineData = (Map<String, Object>) ob;
        } else {
            this.engineData = new HashMap<>();
            Method[] methods = ob.getClass().getDeclaredMethods();
            for (Method m : methods) {
                String name = m.getName();
                if (!name.startsWith("get")) {
                    continue;
                }
                System.out.println(name);
                name = name.substring(3);
                try {
                    this.engineData.put(name.substring(0, 1).toLowerCase() + name.substring(1), m.invoke(ob));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
