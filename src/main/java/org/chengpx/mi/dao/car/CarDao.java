package org.chengpx.mi.dao.car;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.CarBean;

import java.util.Map;

/**
 * create at 2018/4/17 12:54 by chengpx
 */
public interface CarDao extends BaseDao {

    /**
     * 根据小车 id, 查询对应的小车数据
     *
     * @param carId 小车 id
     * @return 对应小车的所有数据
     */
    CarBean select(int carId);

    /**
     * 更改指定小车的动作
     *
     * @param carId     小车 id
     * @param carAction 要修改的动作
     * @return 对数据库的影响行数
     */
    int update(int carId, String carAction);

    /**
     * 查询指定小车的账户余额
     *
     * @param carId 小车编号
     * @return 小车的账户余额
     */
    Number selectBalance(int carId);

    /**
     * 修改指定小车的账户余额
     *
     * @param carId   小车编号
     * @param balance 要修改的账户余额
     * @return 对数据库的影响行数
     */
    int update(int carId, int balance);

    /**
     * 查询 car 表中的所有记录
     *
     * @return 每行记录封装到 CarBean 中, CarId 作为 map 的 key, CarBean 作为 map 的值
     */
    Map<Integer, CarBean> select();

    /**
     * 更新 car 表中的指定 CarId 编号的记录的 CarAction 字段值(开启事务)
     *
     * @param carId     小车编号
     * @param carAction 小车动作
     * @return 数据库的影响行数
     */
    int updateTra(Integer carId, String carAction);

    /**
     * 车查询 car 表的指定 id 编号的 CarAction 字段值
     *
     * @param carId 小车编号
     * @return 小车的动作
     */
    String selectCarAction(int carId);

    /**
     * 查询 car 表指定 id 编号的 CarSpeed 字段值
     *
     * @param carId 小车编号
     * @return 小车速度
     */
    Number selectCarSpeed(int carId);

    /**
     * 更新 car 表指定 id 编号的 CarSpeed 字段值
     *
     * @param carId    小车编号
     * @param carSpeed 小车速度
     * @return 数据库的影响行数
     */
    int updateCarSpeedTra(int carId, int carSpeed);

}
