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


@Repository("cuteCatDao")
public class CuteCatDao extends BaseDao<CuteCat> {

    private Logger LOG = Logger.getLogger(CuteCatDao.class);

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
        sqlBuilder.append("select count(*) from ").append(CuteCat.TABLE_NAME);
        long count = super.queryCount(sqlBuilder.toString());
        return count;
    }

    /**
    * 获取全部信息
    *
    * @return 全部信息列表
    */
    public List<CuteCat> getAllUserList() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select * from ").append(CuteCat.TABLE_NAME);
        List<CuteCat> userList = super.queryList(sqlBuilder.toString(), new CuteCatMapper());
            return userList;
    }


    protected class CuteCatMapper implements RowMapper<CuteCat> {
        public CuteCat mapRow(ResultSet rs, int rowNum) throws SQLException {
            CuteCat temp = new CuteCat();
            temp.setId(rs.getInt("id"));
            temp.setName(rs.getString("name"));
            temp.setSex(rs.getString("sex"));
            temp.setAge(rs.getInt("age"));
            return temp;
        }
    }
}
