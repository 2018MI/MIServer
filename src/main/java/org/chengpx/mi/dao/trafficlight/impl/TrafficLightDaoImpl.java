package org.chengpx.mi.dao.trafficlight.impl;

import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.trafficlight.TrafficLightDao;
import org.chengpx.mi.domain.trafficlight.TrafficLightBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * create at 2018/4/18 15:00 by chengpx
 */
@Repository
public class TrafficLightDaoImpl extends BaseDaoJdbcImpl implements TrafficLightDao {

    @Override
    public Map<String, TrafficLightBean> select() {
        // SELECT * FROM trafficlight;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM trafficlight;",
                    new BeanMapHandler<>(TrafficLightBean.class, "TrafficLightId"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int update(String trafficLightId, String status) {
        // UPDATE trafficlight SET `Status` = 'Yellow' WHERE TrafficLightId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.update(connection, "UPDATE trafficlight SET `Status` = ? WHERE TrafficLightId = ?;",
                    status, trafficLightId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return 0;
    }

}
