package org.bud.cli.component;


import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/5 上午7:21
 * @history: 1.2019/3/5 created by jianfeng.zheng
 */
public class Form extends Component {
    private List<Row> rows = new ArrayList<>();

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }


    public void addRow(Row row){
        this.rows.add(row);
    }
}
