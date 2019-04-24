package cn.wgh0807.dao.center.dao;
import cn.wgh0807.dao.base.dao.BaseDao;
import cn.wgh0807.model.model.*;
import cn.wgh0807.model.param.*;
import cn.wgh0807.model.vo.*;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;


/**
 * Created by Wgh0807 on 2019/04/24 13:19:36.
 */


@Repository("luckyPersonDao")
public class LuckyPersonDao extends BaseDao<LuckyPerson> {

    private Logger LOG = Logger.getLogger(LuckyPersonDao.class);

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
        sqlBuilder.append("select count(*) from ").append(LuckyPerson.TABLE_NAME);
        long count = super.queryCount(sqlBuilder.toString());
        return count;
    }

    /**
    * 获取全部信息
    *
    * @return 全部信息列表
    */
    public List<LuckyPerson> getAllUserList() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from ").append(LuckyPerson.TABLE_NAME);
        List<LuckyPerson> userList = super.queryList(sqlBuilder.toString(), new LuckyPersonMapper());
            return userList;
    }


    protected class LuckyPersonMapper implements RowMapper<LuckyPerson> {
        public LuckyPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
            LuckyPerson temp = new LuckyPerson();
            temp.setId(rs.getInt("id"));
            temp.setName(rs.getString("name"));
            temp.setMoney(rs.getBigDecimal("money"));
            return temp;
        }
    }
}
