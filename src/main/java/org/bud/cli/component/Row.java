package org.bud.cli.component;

import com.sun.tools.javac.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:22
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Row {
    private List<Col> cols;

    public List<Col> getCols() {
        return cols;
    }

    public void setCols(List<Col> cols) {
        this.cols = cols;
    }
}
