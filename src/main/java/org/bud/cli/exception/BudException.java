package org.bud.cli.exception;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 下午1:40
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class BudException extends RuntimeException {

    public BudException(String msg){
        super(msg);
    }
}
