package org.chengpx.mi.dao.road.impl;

import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.road.RoadDao;
import org.chengpx.mi.domain.RoadBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * create at 2018/4/20 10:50 by chengpx
 */
@Repository
public class RoadDaoImpl extends BaseDaoJdbcImpl implements RoadDao {

    @Override
    public Map<Integer, RoadBean> select() {
        // SELECT * FROM road;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM road;",
                    new BeanMapHandler<Integer, RoadBean>(RoadBean.class, "RoadId"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

}
