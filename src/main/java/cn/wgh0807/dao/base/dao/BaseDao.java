package cn.wgh0807.dao.base.dao;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * BaseDao
 *
 * @author liuzhenkun
 * @date 2016-01-27
 */
public class BaseDao<T> implements Serializable {

    private static final long serialVersionUID = -2564407321188709784L;

    private static final Logger LOG = Logger.getLogger(BaseDao.class);

    protected JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 设置一些操作的常量
     */
    private Class<T> entityClass;

    public BaseDao() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * 查询唯一对象(返回非唯一对象或者NULL会抛出异常--废弃)
     *
     * @param sql
     * @param rowMapper
     * @param args
     * @return
     */
    @Deprecated
    protected T queryUnique(String sql, RowMapper<T> rowMapper, Object... args) {
        return jdbcTemplate.queryForObject(sql, rowMapper, args);
    }

    /**
     * 查询数据对象集合
     *
     * @param sql
     * @param rowMapper
     * @param args
     * @return
     */
    protected List<T> queryList(String sql, RowMapper<T> rowMapper, Object... args) {
        return jdbcTemplate.query(sql, rowMapper, args);
    }

    /**
     * 查询结果数量
     *
     * @param sql
     * @param args
     * @return
     */
    protected long queryCount(String sql, Object... args) {
        return jdbcTemplate.queryForObject(sql, args, Long.class);
    }

    /**
     * 更新
     *
     * @param sql
     * @param args
     */
    protected int update(String sql, Object... args) {
        int flag = jdbcTemplate.update(sql, args);
        return flag;
    }

    /**
     * 删除
     *
     * @param sql
     * @param args
     */
    protected void delete(String sql, Object... args) {

        jdbcTemplate.update(sql, args);
    }

    /**
     * 返回Map集合，注意，这个是不带参数返回的Map，需要带参数可自行定义
     * @param sql
     * @return
     */
    public Map<String, Object> queryForMap(String sql) {
        return jdbcTemplate.queryForMap(sql);
    }

}
