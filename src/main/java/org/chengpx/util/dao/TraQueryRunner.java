package org.chengpx.util.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 具备处理事务能力的 QueryRunner<br>
 * 内部维护了一个 JdbcUtils 对象(每个线程中的单例对象)<br>
 *
 * @author chengpx
 */
public class TraQueryRunner extends QueryRunner {

    private static final Logger S_LOGGER = LoggerFactory.getLogger(TraQueryRunner.class);
    private static final ThreadLocal<QueryRunner> S_TRA_QUERY_RUNNER_THREAD_LOCAL = new ThreadLocal<>();
    @Autowired
    private JdbcUtils jdbcUtils;

    private TraQueryRunner() {
        // jdbcUtils = JdbcUtils.getThreadInstance();
    }

    public static QueryRunner getThreadInstance() {
        QueryRunner queryRunner = S_TRA_QUERY_RUNNER_THREAD_LOCAL.get();
        if (queryRunner == null) {
            queryRunner = new TraQueryRunner();
            S_TRA_QUERY_RUNNER_THREAD_LOCAL.remove();
            S_TRA_QUERY_RUNNER_THREAD_LOCAL.set(queryRunner);
        }
        return queryRunner;
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @Override
    public int[] batch(String sql, Object[][] params) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return batch(connection, sql, params);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param rsh
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return query(connection, sql, rsh, params);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param rsh
     * @param <T>
     * @return
     * @throws SQLException
     */
    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return query(connection, sql, rsh);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    @Override
    public int update(String sql) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return update(connection, sql);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param param
     * @return
     * @throws SQLException
     */
    @Override
    public int update(String sql, Object param) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return update(connection, sql, param);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @Override
    public int update(String sql, Object... params) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return update(connection, sql, params);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param rsh
     * @param <T>
     * @return
     * @throws SQLException
     */
    @Override
    public <T> T insert(String sql, ResultSetHandler<T> rsh) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return insert(connection, sql, rsh);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param rsh
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    @Override
    public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return insert(connection, sql, rsh, params);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param rsh
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    @Override
    public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return insertBatch(connection, sql, rsh, params);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    @Override
    public int execute(String sql, Object... params) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return execute(connection, sql, params);
    }

    /**
     * 内部使用的是本线程中的 JdbcUtils 单例对象的开启了事务的数据库库连接
     *
     * @param sql
     * @param rsh
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    @Override
    public <T> List<T> execute(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        Connection connection = jdbcUtils.getTraConnection();
        if (connection == null) {
            throw new IllegalStateException("请开启事务");
        }
        return execute(connection, sql, rsh, params);
    }

}
