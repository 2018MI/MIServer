package org.chengpx.mi.dao.roadlight.impl;

import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.roadlight.RoadLightDao;
import org.chengpx.mi.domain.RoadLightBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * create at 2018/4/21 13:45 by chengpx
 */
@Repository
public class RoadLightDaoImpl extends BaseDaoJdbcImpl implements RoadLightDao {

    @Override
    public String selectStatus(int roadLightId) {
        // SELECT `Status` FROM roadlight WHERE RoadLightId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT `Status` FROM roadlight WHERE RoadLightId = ?;",
                    new ScalarHandler<String>(), roadLightId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int update(int roadLightId, String status) {
        // UPDATE roadlight SET `Status` = 'Stop' WHERE RoadLightId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.update(connection, "UPDATE roadlight SET `Status` = ? WHERE RoadLightId = ?;",
                    status, roadLightId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return 0;
    }

    @Override
    public String selectControlMode(int roadLightId) {
        // SELECT `Status` FROM roadlight WHERE RoadLightId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT ControlMode FROM roadlight WHERE RoadLightId = ?;",
                    new ScalarHandler<String>(), roadLightId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int update(String controlMode) {
        // UPDATE roadlight SET ControlMode = 'Manual';
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.update(connection, "UPDATE roadlight SET ControlMode = 'Manual';");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return 0;
    }

    @Override
    public Map<Integer, RoadLightBean> select() {
        // SELECT * FROM roadlight;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM roadlight;",
                    new BeanMapHandler<Integer, RoadLightBean>(RoadLightBean.class, "RoadLightId"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

}
