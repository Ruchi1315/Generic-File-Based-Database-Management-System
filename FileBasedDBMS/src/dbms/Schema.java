package dbms;

import java.util.ArrayList;
import java.util.List;

public class Schema {
    private String tableName;
    private List<Column> columns;

    public Schema(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<>();
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public String getTableName() {
        return tableName;
    }
}
