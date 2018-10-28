package org.chengpx.mi.dao.parking.impl;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.parking.ParkingDao;
import org.chengpx.mi.domain.parking.ParkingBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * create at 2018/4/20 15:38 by chengpx
 */
@Repository
public class ParkingDaoImpl extends BaseDaoJdbcImpl implements ParkingDao {

    @Override
    public List<ParkingBean> select() {
        // SELECT * FROM parking;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM parking;",
                    new BeanListHandler<ParkingBean>(ParkingBean.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int update(int id, String rateType, Integer money) {
        // UPDATE parking SET RateType = 'Count', Money = 200 WHERE id = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.update(connection, "UPDATE parking SET RateType = ?, Money = ? WHERE id = ?;",
                    rateType, money, id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return 0;
    }

    @Override
    public Map<Integer, ParkingBean> selectToMap() {
        // SELECT * FROM parking;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM parking;",
                    new BeanMapHandler<Integer, ParkingBean>(ParkingBean.class, "id"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

}
