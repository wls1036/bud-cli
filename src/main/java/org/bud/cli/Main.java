package org.bud.cli;

import org.bud.cli.parser.Controller;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午2:50
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Main {

    public static void main(String[] cmd) {
        Controller controller = new Controller();
        controller.run();
    }
}
