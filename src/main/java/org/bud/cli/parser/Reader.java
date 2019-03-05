package org.bud.cli.parser;

import org.bud.cli.exception.BudException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午1:13
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Reader {


    /**
     * 读取单个文件
     * 只读取.bud结尾的文件
     *
     * @param path
     * @return
     */
    public static PageFile readFile(String path) {
        return Reader.readFile(new File(path));
    }


    /**
     * 读取bud文件
     *
     * @param file
     * @return
     */
    public static PageFile readFile(File file) {
        if (!file.isFile() || !file.getName().endsWith(".bud")) {
            throw new BudException("无法读取文件:" + file.getName() + " 请检查");
        }
        PageFile pageFile = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            pageFile = new PageFile();
            String line = null;
            while ((line = br.readLine()) != null) {
                pageFile.addLine(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageFile;
    }

    /**
     * 读取当前目录下所有以bud结尾的文件
     * 不递归读取
     *
     * @param path
     * @return
     */
    public static List<PageFile> readFiles(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        List<PageFile> rs = new ArrayList<>();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".bud")) {
                rs.add(Reader.readFile(f));
            }
        }
        return rs;
    }
}
