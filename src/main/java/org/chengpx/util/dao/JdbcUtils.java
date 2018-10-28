package org.chengpx.util.dao;

import com.zaxxer.hikari.HikariDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * 1. 维护了一个 c3p0 配置文件默认配置的连接池<br>
 * 2. 从数据库连接池获取数据库连接<br>
 * 3. 支持开启数据库事务<br>
 *
 * @author chengpx
 */
public class JdbcUtils {

    private static final Logger S_LOGGER = LoggerFactory.getLogger(JdbcUtils.class);

    private static final ThreadLocal<JdbcUtils> S_JDBC_UTILS_THREAD_LOCAL = new ThreadLocal<>();
    /**
     * c3p0 连接池对象
     */
    private DataSource dataSource;
    /**
     * 开启事务的数据库连接
     */
    private Connection traConnection;

    private JdbcUtils() {
        try {
            Properties props = new Properties();
            props.load(JdbcUtils.class.getClassLoader().getResourceAsStream("project.properties"));
            HikariDataSource hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName(props.getProperty("jdbc.driver", "com.mysql.jdbc.Driver"));
            hikariDataSource.setJdbcUrl(props.getProperty("jdbc.url"));
            hikariDataSource.setUsername(props.getProperty("jdbc.username", "root"));
            hikariDataSource.setPassword(props.getProperty("jdbc.password", "123456"));
            hikariDataSource.setReadOnly(Boolean.parseBoolean(props.getProperty("jdbc.readOnly", "false")));
            hikariDataSource.setConnectionTimeout(Integer.parseInt(props.getProperty("jdbc.connectionTimeout", "30000")));
            hikariDataSource.setIdleTimeout(Integer.parseInt(props.getProperty("jdbc.idleTimeout", "600000")));
            hikariDataSource.setMaxLifetime(Integer.parseInt(props.getProperty("jdbc.maxLifetime", "1800000")));
            hikariDataSource.setMaximumPoolSize(Integer.parseInt(props.getProperty("jdbc.maximumPoolSize", "15")));
            dataSource = hikariDataSource;
        } catch (IOException e) {
            e.printStackTrace();
            S_LOGGER.debug(e.getMessage());
        }
    }

    /**
     * 获取每个线程中的 JdbcUtils 单例对象
     *
     * @return 每个线程中的 JdbcUtils 单例对象
     */
    public static JdbcUtils getThreadInstance() {
        JdbcUtils jdbcUtils = S_JDBC_UTILS_THREAD_LOCAL.get();
        if (jdbcUtils == null) {
            jdbcUtils = new JdbcUtils();
            S_JDBC_UTILS_THREAD_LOCAL.remove();
            S_JDBC_UTILS_THREAD_LOCAL.set(jdbcUtils);
        }
        return jdbcUtils;
    }

    /**
     * 从连接池产生一个新的数据库连接并开启事务
     */
    public void beginTransaction() {
        try {
            traConnection = dataSource.getConnection();
            if (!traConnection.getAutoCommit()) {
                throw new IllegalStateException("事务已开启");
            }
            traConnection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            S_LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 提交事务, 并关闭与数据库的连接
     */
    public void commitTransaction() {
        if (traConnection == null) {
            return;
        }
        try {
            if (traConnection.getAutoCommit()) {
                throw new IllegalStateException("请开启事务");
            }
            traConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            S_LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                traConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                S_LOGGER.error(e.getMessage());
            }
            traConnection = null;
        }
    }

    /**
     * 回滚事务, 并关闭与数据库的连接
     */
    public void rollbackTransaction() {
        if (traConnection == null) {
            return;
        }
        try {
            if (traConnection.getAutoCommit()) {
                throw new IllegalStateException("请开启事务");
            }
            traConnection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
            S_LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                traConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                S_LOGGER.error(e.getMessage());
            }
            traConnection = null;
        }
    }

    /**
     * 从连接池产生一个数据库连接
     *
     * @return 连接池产生的一个新的数据库连接
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            S_LOGGER.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 返回本线程中开启了事务的数据库连接
     *
     * @return 本线程中开启了事务的数据库连接
     */
    public Connection getTraConnection() {
        return traConnection;
    }

}
