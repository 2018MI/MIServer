package org.chengpx.mi.service.car;

import org.chengpx.framework.service.ServiceException;

/**
 * create at 2018/4/17 12:53 by chengpx
 */
public interface CarService {

    /**
     * 设置小车动作
     *
     * @param carId     小车 id
     * @param carAction 小车动作
     */
    void setCarMove(int carId, String carAction) throws ServiceException;

    /**
     * 查询小车账户余额
     *
     * @param carId 小车 id
     * @return 小车的账户余额
     */
    int getCarAccountBalance(int carId) throws ServiceException;

    /**
     * 对小车账户进行充值
     *
     * @param carId 小车编号
     * @param money 充值金额
     */
    void setCarAccountRecharge(int carId, int money) throws ServiceException;

    /**
     * 查询小车当前速度
     *
     * @param carId 小车编号
     * @return 小车当前速度
     */
    int getCarSpeed(int carId) throws ServiceException;

    /**
     * 查询小车动作
     *
     * @param carId 小车 id
     * @return 小车当前动作
     */
    String getCarMove(int carId) throws ServiceException;

}
