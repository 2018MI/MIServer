package org.chengpx.mi.dao.car.impl;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.car.CarDao;
import org.chengpx.mi.domain.CarBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * create at 2018/4/17 21:11 by chengpx
 */
@Repository
public class CarDaoImpl extends BaseDaoJdbcImpl implements CarDao {

    @Override
    public CarBean select(int carId) {
        // SELECT * FROM car WHERE CarId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM car WHERE CarId = ?;",
                    new BeanHandler<>(CarBean.class), carId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int update(int carId, String carAction) {
        // UPDATE car SET CarAction  = 'stop' WHERE CarId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.update(connection, "UPDATE car SET CarAction  = ? WHERE CarId = ?;",
                    carAction, carId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return 0;
    }

    @Override
    public Number selectBalance(int carId) {
        // SELECT Balance FROM car WHERE CarId = 100;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT Balance FROM car WHERE CarId = ?;",
                    new ScalarHandler<>(), carId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int update(int carId, int balance) {
        // UPDATE car SET Balance = 50 WHERE CarId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.update(connection, "UPDATE car SET Balance = ? WHERE CarId = ?;",
                    balance, carId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return 0;
    }

    @Override
    public Map<Integer, CarBean> select() {
        // SELECT * FROM car;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT * FROM car;",
                    new BeanMapHandler<Integer, CarBean>(CarBean.class, "CarId"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int updateTra(Integer carId, String carAction) {
        // UPDATE car SET CarAction  = 'stop' WHERE CarId = 1;
        try {
            return mQueryRunner.update("UPDATE car SET CarAction  = ? WHERE CarId = ?;",
                    carAction, carId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public String selectCarAction(int carId) {
        // SELECT CarAction FROM car WHERE CarId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT CarAction FROM car WHERE CarId = ?;",
                    new ScalarHandler<String>(), carId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public Number selectCarSpeed(int carId) {
        // SELECT CarSpeed FROM car WHERE CarId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            return mQueryRunner.query(connection, "SELECT CarSpeed FROM car WHERE CarId = ?;",
                    new ScalarHandler<Number>(), carId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int updateCarSpeedTra(int carId, int carSpeed) {
        // UPDATE car SET CarSpeed = 50 WHERE CarId = 1;
        try {
            return mQueryRunner.update("UPDATE car SET CarSpeed = ? WHERE CarId = ?;", carSpeed, carId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
