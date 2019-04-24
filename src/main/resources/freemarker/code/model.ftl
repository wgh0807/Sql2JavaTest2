package ${package}.${midPackage}.${lastPackage};

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ${developer} on ${current_date}.
 */

public class ${tableObject.className} implements Serializable {
    <#--生成序列号-->
    private static final long serialVersionUID = 1L;

    <#--该类所使用的表-->
    public static final String TABLE_NAME = "${tableObject.tableName}";

    <#--生成自己的属性-->
    <#list tableObject.columnObjects as col>
    private ${col.javaType} ${col.javaName};
    </#list>

    <#--生成 get/set 方法-->
    <#list tableObject.columnObjects as col>
    public void set${col.javaName}(${col.javaType} ${col.javaName}){
        this.${col.javaName} = ${col.javaName};
    }
    public ${col.javaType} get${col.javaName}(){
        return ${col.javaName};
    }
    </#list>
}
