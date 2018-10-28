package org.chengpx.mi.dao.bus.impl;

import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.bus.BusStationDao;
import org.chengpx.mi.domain.BusStationBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * create at 2018/4/19 19:40 by chengpx
 */
@Repository
public class BusStationDaoImpl extends BaseDaoJdbcImpl implements BusStationDao {

    @Override
    public Map<Integer, BusStationBean> select() {
        // SELECT * FROM busstation;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM busstation;",
                    new BeanMapHandler<Integer, BusStationBean>(BusStationBean.class, "BusStationID"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

}
