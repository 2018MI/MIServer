package org.chengpx.mi.dao.parking;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.parking.ParkingSpacesBean;

import java.util.List;
import java.util.Map;

/**
 * 停车位数据访问层
 * <p>
 * create at 2018/4/20 16:31 by chengpx
 */
public interface ParkingSpacesDao extends BaseDao {

    /**
     * 查询parkingspaces 表中 fk_parkingId, status 为指定值的所有记录
     *
     * @param fkParkingId 停车场 id
     * @param status      车位状态
     * @return 每行记录封装到 ParkingSpacesBean, 所有记录的 list
     * @see ParkingSpacesBean.StatusEnum
     */
    List<ParkingSpacesBean> select(int fkParkingId, int status);

    /**
     * 更新 parkingspaces 表中指定 id 的记录的 status 字段值(开启事务)
     *
     * @param id     停车位编号
     * @param status 停车位状态
     * @return 数据库的影响行数
     * @see ParkingSpacesBean.StatusEnum
     */
    int updateTra(int id, int status);

    /**
     * 查询 parkingspace 表中的所有数据
     *
     * @return 每行记录封装到 ParkingSpacesBean, ParkingSpacesBean.id 作为 map 的 key
     */
    Map<Integer, ParkingSpacesBean> select();

}
