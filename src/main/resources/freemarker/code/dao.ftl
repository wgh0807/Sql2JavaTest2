<#--package cn.wgh0807.dao.center.dao;-->
package ${package}.${midPackage}.${detailPackage};
<#--import cn.wgh0807.dao.base.dao.BaseDao;-->
import ${package}.${midPackage}.base.dao.BaseDao;
<#--import cn.wgh0807.model.model.SysUser;-->
<#--import ${package}.${modelPackage}.${tableObject.className};-->
<#--<#list modelDetailPackage as mdp>-->
<#--import ${package}.${modelMidPackage}.${mdp}.${tableObject.className}<#if mdp!="Model">${mdp}</#if>;-->
<#--</#list>-->
<#list modelDetailPackage as mdp>
import ${package}.${modelMidPackage}.${mdp}.*;
</#list>
<#--import com.lhcis.auth.center.param.*;-->

<#--import com.lhcis.auth.base.dao.BaseDao;-->
<#--import com.lhcis.auth.center.model.SysUser;-->
<#--import com.lhcis.auth.center.param.*;-->

<#--import com.lhcis.common.constants.SysLevelEnum;-->
<#--import com.lhcis.common.constants.ValidEnum;-->

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;


/**
 * Created by ${developer} on ${current_date}.
 */


@Repository("${urlClass}Dao")
public class ${tableObject.className}Dao extends BaseDao<${tableObject.className}> {

    private Logger LOG = Logger.getLogger(${tableObject.className}Dao.class);

    @Resource(name = "jdbcTemplate")
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
    * 查询总数
    *
    * @return
    */
    public long getUserCount() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select count(*) from ").append(${tableObject.className}.TABLE_NAME);
        long count = super.queryCount(sqlBuilder.toString());
        return count;
    }

    /**
    * 获取全部信息
    *
    * @return 全部信息列表
    */
    public List<${tableObject.className}> getAllUserList() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from ").append(${tableObject.className}.TABLE_NAME);
        List<${tableObject.className}> userList = super.queryList(sqlBuilder.toString(), new ${tableObject.className}Mapper());
            return userList;
    }


    protected class ${tableObject.className}Mapper implements RowMapper<${tableObject.className}> {
        public ${tableObject.className} mapRow(ResultSet rs, int rowNum) throws SQLException {
            ${tableObject.className} temp = new ${tableObject.className}();
            <#list tableObject.columnObjects as col>
            temp.set${col.javaName}(rs.${col.methodGetFromRS}("${col.columnName}"));
            </#list>
            return temp;
        }
    }
}
