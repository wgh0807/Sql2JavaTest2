package cn.wgh0807.utilities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TableObject implements Serializable {
    private String tableName;
    private String className;
    private List<ColumnObject> columnObjects = new LinkedList<>();

    public TableObject(String tableName, String className, List<ColumnObject> tableObjects) {
        this.tableName = tableName;
        this.className = className;
        this.columnObjects = tableObjects;
    }
    public TableObject(TableObject t) {
        this.tableName = t.getTableName();
        this.className = t.getClassName();
        this.columnObjects = t.getcolumnObjects();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ColumnObject> getcolumnObjects() {
        return columnObjects;
    }

    public void setTableObjects(List<ColumnObject> columnObjects) {
        this.columnObjects = columnObjects;
    }

    @Override
    public String toString() {
        return "TableObject{" +
                "tableName='" + tableName + '\'' +
                ", className='" + className + '\'' +
                ", columnObjects=" + columnObjects +
                '}';
    }
}

