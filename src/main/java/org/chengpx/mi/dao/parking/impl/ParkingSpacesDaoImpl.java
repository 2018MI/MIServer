package org.chengpx.mi.dao.parking.impl;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.mi.dao.parking.ParkingSpacesDao;
import org.chengpx.mi.domain.parking.ParkingBean;
import org.chengpx.mi.domain.parking.ParkingSpacesBean;
import org.chengpx.util.ResUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * create at 2018/4/20 16:33 by chengpx
 */
@Repository
public class ParkingSpacesDaoImpl extends BaseDaoJdbcImpl implements ParkingSpacesDao {

    @Override
    public List<ParkingSpacesBean> select(int fkParkingId, int status) {
        // SELECT * FROM parkingspaces WHERE `status` = 1 AND fk_parkingId = 1;
        Connection connection = mJdbcUtils.getConnection();
        try {
            List<Map<String, Object>> mapList = mQueryRunner.query(connection, "SELECT * FROM parkingspaces WHERE `status` = ? AND fk_parkingId = ?;",
                    new MapListHandler(), status, fkParkingId);
            ParkingBean parkingBean = null;
            List<ParkingSpacesBean> parkingSpacesBeanList = new CopyOnWriteArrayList<>();
            for (Map<String, Object> map : mapList) {
                ParkingSpacesBean parkingSpacesBean = new ParkingSpacesBean();
                parkingSpacesBean.setId((Integer) map.get("id"));
                parkingSpacesBean.setStatus((Integer) map.get("status"));
                if (parkingBean == null) {
                    parkingBean = mQueryRunner.query(connection, "SELECT * FROM parking WHERE id = ?;",
                            new BeanHandler<ParkingBean>(ParkingBean.class), map.get("fk_parkingId"));
                }
                parkingSpacesBean.setParkingBean(parkingBean);
                parkingSpacesBeanList.add(parkingSpacesBean);
            }
            return parkingSpacesBeanList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

    @Override
    public int updateTra(int id, int status) {
        // UPDATE parkingspaces SET `status` = 1 WHERE id = 1;
        try {
            return mQueryRunner.update("UPDATE parkingspaces SET `status` = ? WHERE id = ?;", status, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Map<Integer, ParkingSpacesBean> select() {
        Connection connection = mJdbcUtils.getConnection();
        try {
            List<Map<String, Object>> mapList = mQueryRunner.query(connection, "SELECT * FROM parkingspaces;",
                    new MapListHandler());
            ParkingBean parkingBean = null;
            Map<Integer, ParkingSpacesBean> parkingSpacesBeanMap = Collections.synchronizedMap(new LinkedHashMap<>());
            for (Map<String, Object> map : mapList) {
                ParkingSpacesBean parkingSpacesBean = new ParkingSpacesBean();
                parkingSpacesBean.setId((Integer) map.get("id"));
                parkingSpacesBean.setStatus((Integer) map.get("status"));
                if (parkingBean == null) {
                    parkingBean = mQueryRunner.query(connection, "SELECT * FROM parking WHERE id = ?;",
                            new BeanHandler<ParkingBean>(ParkingBean.class), map.get("fk_parkingId"));
                }
                parkingSpacesBean.setParkingBean(parkingBean);
                parkingSpacesBeanMap.put(parkingSpacesBean.getId(), parkingSpacesBean);
            }
            return parkingSpacesBeanMap;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ResUtils.close(connection);
        }
        return null;
    }

}
