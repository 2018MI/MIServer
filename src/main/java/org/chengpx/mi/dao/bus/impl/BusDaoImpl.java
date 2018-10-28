package org.chengpx.mi.dao.bus.impl;

import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.bus.BusDao;
import org.chengpx.mi.domain.BusBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * create at 2018/4/19 15:00 by chengpx
 */
@Repository
public class BusDaoImpl extends BaseDaoJdbcImpl implements BusDao {

    @Override
    public Map<Integer, BusBean> select() {
        // SELECT * FROM bus;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM bus;",
                    new BeanMapHandler<Integer, BusBean>(BusBean.class, "BusId"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

}
