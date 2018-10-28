package org.chengpx.mi.dao.trafficlight.impl;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.trafficlight.SignalLightDao;
import org.chengpx.mi.domain.trafficlight.SignalLightBean;
import org.chengpx.mi.domain.trafficlight.TrafficLightBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * create at 2018/4/18 12:53 by chengpx
 */
@Repository
public class SignalLightDaoImpl extends BaseDaoJdbcImpl implements SignalLightDao {

    @Override
    public int update(String fkTrafficLightId, String type, int time) {
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.update(connection,
                    "UPDATE signallight SET `Time` = ? WHERE `fk_TrafficLightId` = ? AND `type` = ?;",
                    time, fkTrafficLightId, type);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return 0;
    }

    @Override
    public Map<String, SignalLightBean> select(String fkTrafficLightId) {
        // SELECT * FROM singnallight WHERE fk_TrafficLightId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            List<Map<String, Object>> mapList = mQueryRunner.query(connection, "SELECT * FROM signallight WHERE fk_TrafficLightId = ?;",
                    new MapListHandler(), fkTrafficLightId);
            TrafficLightBean trafficLightBean = null;
            Map<String, SignalLightBean> signalLightBeanMap = Collections.synchronizedMap(new LinkedHashMap<>());
            for (Map<String, Object> map : mapList) {
                SignalLightBean signalLightBean = new SignalLightBean();
                signalLightBean.setId((Integer) map.get("id"));
                signalLightBean.setTime((Integer) map.get("Time"));
                signalLightBean.setType((String) map.get("type"));
                if (trafficLightBean == null) {
                    trafficLightBean = mQueryRunner.query(connection, "SELECT * FROM trafficlight WHERE TrafficLightId = ?;",
                            new BeanHandler<TrafficLightBean>(TrafficLightBean.class), map.get("fk_TrafficLightId"));
                }
                signalLightBean.setTrafficLightBean(trafficLightBean);
                signalLightBeanMap.put(signalLightBean.getType(), signalLightBean);
            }
            return signalLightBeanMap;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int updateTra(String fkTrafficLightId, String type, int time) {
        try {
            return mQueryRunner.update("UPDATE signallight SET `Time` = ? WHERE `fk_TrafficLightId` = ? AND `type` = ?;",
                    time, fkTrafficLightId, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
