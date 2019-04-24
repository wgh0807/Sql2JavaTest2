package cn.wgh0807.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static cn.wgh0807.utilities.SmallUtil.*;


/**
 * 默认表名命名时不同单词以 '_' 间隔
 * 如：ip_location
 * 最终返回格式：
 * 遍历数据库：LinkedList<TableObject>;
 * 遍历表：TableObject
 */
public class GetSqlModel {
    private static Logger logger = LoggerFactory.getLogger(GetSqlModel.class);
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql:///?useUnicode=true&characterEncoding=utf8";
    private static final String USER = "java";
    private static final String PASSWORD = "java";

//    用于存储 mysqlDataType - javaDataType
    private static HashMap<String, String> valueTypeMap = new HashMap<>();
    private static HashMap<String, String> valueMethodMap = new HashMap<>();


//    加载类
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("can not load jdbc driver" + e);
        }
    }

    /**
     * 获取数据库内的所有表名，存储类型为List
     *
     * @param dataBaseName 需要遍历的数据库名
     * @return List<String> 该数据库下的表名列表
     */
    private static List<String> getTableNameList(String dataBaseName) {
        List<String> list = new LinkedList<>();
        ResultSet rs = null;
        Connection conn = getConnection();

        try {
            DatabaseMetaData dbmeta = conn.getMetaData();
            rs = dbmeta.getTables(dataBaseName, null, null, new String[]{"table"});
            while (rs.next()) {
                list.add(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return list;
    }

    /**
     * 获取数据库下所有的column，
     *
     * @param dataBaseName:对象表所在的数据库名
     * @param tableName:对象表名
     * @return 表内元素对象的集合
     */
    private static List<ColumnObject> getColumnList(String dataBaseName, String tableName) {
        ResultSet rs = null;
        Connection conn = getConnection();
        List<ColumnObject> list = new LinkedList<>();
        try {
            DatabaseMetaData dbmeta = conn.getMetaData();
            rs = dbmeta.getColumns(dataBaseName, null, tableName, null);
            while (rs.next()) {

                String columnLabel = rs.getString(4);
                String columnType = rs.getString(6);
                String propName = classNameFormat(columnLabel);
                String propType = valueTypeMap.get(columnType);
                String method = valueMethodMap.get(columnType);
                ColumnObject co = new ColumnObject(columnLabel, columnType, propName, propType, method);
                list.add(co);
            }

        } catch (SQLException e) {
            logger.error("get Column List failure", e);
        } finally {
            closeResultSet(rs);
            closeConnection(conn);
        }
        return list;
    }

    /**
     * 返回一个数据库内表对象的列表
     * @param dbName:数据库名
     * @return List
     */
    public static List<TableObject> getAllObjectInDB(String dbName){
        List<TableObject> tableObjectList = new LinkedList<>();
        List<String> tableList = getTableNameList(dbName);
        for (String tableName : tableList) {
            tableObjectList.add(getAllObjectInTable(dbName,tableName));
        }
        return tableObjectList;
    }

    /**
     * 返回指定数据库内指定表的表对象
     * @param dbName 数据库名
     * @param tableName 表名
     * @return 表对象
     */
    public static TableObject getAllObjectInTable(String dbName, String tableName){
        return new TableObject(tableName,classNameFormat(tableName),getColumnList(dbName,tableName));
    }

    /**
     * 创建连接
     *
     * @return Connection 创建结束后的连接对象
     */
    private static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error("get connection failure", e);
        }
        return conn;
    }

    /**
     * 关闭连接
     *
     * @param connection: 需要被关闭的连接对象
     */
    private static void closeConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("close connection failure", e);
            }
        }
    }

    /**
     * 关闭ResultSet
     *
     * @param rs: 需要被关闭的ResultSet 对象
     */
    private static void closeResultSet(ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("close result set failure", e);
            }
        }
    }

    /**
     * 创建一个sql类型与java类型匹配的一个键值对
     * @return Map<mysqlType, javaType>
     */
    static {

        valueTypeMap.clear();

//        String 类型
        valueTypeMap.put("VARCHAR", "String");
        valueTypeMap.put("CHAR", "String");
        valueTypeMap.put("TINYTEXT", "String");
        valueTypeMap.put("TEXT", "String");

//        二进制类型
        valueTypeMap.put("BLOB", "Byte[]");

//      数值类型
        valueTypeMap.put("INTEGER", "Long");
        valueTypeMap.put("INT", "Integer");
        valueTypeMap.put("TINYINT", "Integer");
        valueTypeMap.put("SMALLINT", "Integer");
        valueTypeMap.put("MEDIUMINT", "Integer");
        valueTypeMap.put("BIT", "Boolean");
        valueTypeMap.put("BIGINT", "BigInteger");
        valueTypeMap.put("FLOAT", "BigDecimal");
        valueTypeMap.put("DOUBLE", "BigDecimal");
        valueTypeMap.put("DECIMAL", "BigDecimal");

//        布尔类型
        valueTypeMap.put("BOOLEAN", "Boolean");

//        时间类型
        valueTypeMap.put("DATE", "Date");
        valueTypeMap.put("TIME", "String");
        valueTypeMap.put("DATETIME", "Date");
        valueTypeMap.put("TIMESTAMP", "Date");
        valueTypeMap.put("YEAR", "Date");
    }
    static {
        valueMethodMap.clear();

//        String 类型
        valueMethodMap.put("VARCHAR", "getString");
        valueMethodMap.put("CHAR", "getString");
        valueMethodMap.put("TINYTEXT", "getString");
        valueMethodMap.put("TEXT", "getString");

//        二进制类型
        valueMethodMap.put("BLOB", "getBlob()");

//      数值类型
        valueMethodMap.put("INTEGER",   "getLong");
        valueMethodMap.put("INT",       "getInt");
        valueMethodMap.put("TINYINT",   "getInt");
        valueMethodMap.put("SMALLINT",  "getInt");
        valueMethodMap.put("MEDIUMINT", "getInt");
        valueMethodMap.put("BIT",       "getBoolean");
        valueMethodMap.put("BIGINT",    "getLong");
        valueMethodMap.put("FLOAT",     "getBigDecimal");
        valueMethodMap.put("DOUBLE",    "getBigDecimal");
        valueMethodMap.put("DECIMAL",   "getBigDecimal");

//        布尔类型
        valueMethodMap.put("BOOLEAN",   "getBoolean");

//        时间类型
        valueMethodMap.put("DATE", "getDate");
        valueMethodMap.put("TIME", "rs.getTime");
        valueMethodMap.put("DATETIME", "getDate");
        valueMethodMap.put("TIMESTAMP", "getDate");
        valueMethodMap.put("YEAR", "getDate");
    }
}
