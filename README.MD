# Sql2JavaTest2
> 项目描述   
一个根据数据库结构生成model/Dao层   
使用前 务必在utilities/GetSqlModel.java中配置自己的数据库用户名和密码。   
使用前务必导入freemarker，在框架下需要依赖spring-context-support及其依赖

## 使用须知
1.  默认表名命名时单词之间以 '_' 间隔，如：ip_location   
2. 最终返回格式：  
   遍历数据库：LinkedList<TableObject>;
   遍历表：TableObject
3. JDBC中使用的变量需要在utilities/GetSqlModel.java 文件中进行配置   
4. freemarker相关变量需要在utilities/freemarkerTest1.java 中进行配置

### 文件路径结构：
--src/main   
----java   
------{basePackage}   
--------{daoMidPackage}   
----------{daoOutPutPackage}   
------------XXXXDao.java   
--------{modelMidPackage}   
----------{modelOutputPackage}   
------------XXXType.java   
----resources   
------freemarker.code   
--------XXX.ftl   

###注意事项
- JDBC中使用的变量需要在utilities/GetSqlModel.java 文件中进行配置   
- 以上大括号内变量及相关变量需要在utilities/freemarkerTest1.java 中进行配置
   

### todo:
目前为止可以完成基本功能，但是也存在一定问题。
1. 没有实现在model层根据数据库数据类型按需导入数据包
2. model、param、vo 对象完全相同
3. 没有实现toString 方法
4. 在Dao中自动导入指定包遇到一些问题，所以Dao导入的是model/param/vo 下所有对象