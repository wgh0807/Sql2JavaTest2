package cn.wgh0807.utilities;

public class ColumnObject {
    private String columnName;
    private String sqlType;
    private String javaName;
    private String javaType;
    private String methodGetFromRS;

    public ColumnObject(String columnName, String sqlType, String javaName, String javaType, String methodGetFromRS) {
        this.columnName = columnName;
        this.sqlType = sqlType;
        this.javaName = javaName;
        this.javaType = javaType;
        this.methodGetFromRS = methodGetFromRS;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getMethodGetFromRS() {
        return methodGetFromRS;
    }

    public void setMethodGetFromRS(String methodGetFromRS) {
        this.methodGetFromRS = methodGetFromRS;
    }

    @Override
    public String toString() {
        return "ColumnObject{" +
                "columnName='" + columnName + '\'' +
                ", sqlType='" + sqlType + '\'' +
                ", javaName='" + javaName + '\'' +
                ", javaType='" + javaType + '\'' +
                ", methodGetFromRS='" + methodGetFromRS + '\'' +
                '}';
    }
}
