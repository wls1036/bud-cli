package org.bud.cli.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: jianfeng.zheng
 * @since: 2019/3/6 上午12:57
 * @history: 1.2019/3/6 created by jianfeng.zheng
 */
public class Columns extends Component{
    private List<Column> columns = new ArrayList<>();

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    @Override
    public String getType() {
        return null;
    }
}
