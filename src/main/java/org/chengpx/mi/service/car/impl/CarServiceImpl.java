package org.chengpx.mi.service.car.impl;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.framework.service.BaseServiceImpl;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.CarSystem;
import org.chengpx.mi.domain.CarBean;
import org.chengpx.mi.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * create at 2018/4/17 20:16 by chengpx
 */
@Service
public class CarServiceImpl extends BaseServiceImpl implements CarService {

    @Autowired
    private CarSystem mCarSystem;

    @Override
    public void setCarMove(int carId, String carAction) throws ServiceException {
        Map<Integer, CarBean> carBeanMap = mCarSystem.getCarBeanMap();
        if (carBeanMap == null) {
            throw new ServiceException("CarSystem no data");
        }
        CarBean carBean = carBeanMap.get(carId);
        if (carBean == null) {
            throw new ServiceException("CarId does not exist");
        }
        boolean isCarActionValid = false;
        for (CarBean.CarActionEnum CarActionEnum : CarBean.CarActionEnum.values()) {
            if (CarActionEnum.getValue().equals(carAction)) {
                isCarActionValid = true;
                break;
            }
        }
        if (!isCarActionValid) {
            throw new ServiceException("CarAction is illegal");
        }
        carBean.setCarAction(carAction);
        if (CarBean.CarActionEnum.START.getValue().equals(carAction)) {
            carBean.setCarSpeed(20);
        } else if (CarBean.CarActionEnum.STOP.getValue().equals(carAction)) {
            carBean.setCarSpeed(0);
        }
    }

    @Override
    public int getCarAccountBalance(int carId) throws ServiceException {
        Map<Integer, CarBean> carBeanMap = mCarSystem.getCarBeanMap();
        if (carBeanMap == null) {
            throw new ServiceException("CarSystem no data");
        }
        CarBean carBean = carBeanMap.get(carId);
        if (carBean == null) {
            throw new ServiceException("CarId does not exist");
        }
        return carBean.getBalance();
    }

    @Override
    public void setCarAccountRecharge(int carId, int money) throws ServiceException {
        Map<Integer, CarBean> carBeanMap = mCarSystem.getCarBeanMap();
        if (carBeanMap == null) {
            throw new ServiceException("CarSystem no data");
        }
        CarBean carBean = carBeanMap.get(carId);
        if (carBean == null) {
            throw new ServiceException("CarId does not exist");
        }
        carBean.setBalance(carBean.getBalance() + money);
    }

    @Override
    public int getCarSpeed(int carId) throws ServiceException {
        Map<Integer, CarBean> carBeanMap = mCarSystem.getCarBeanMap();
        if (carBeanMap == null) {
            throw new ServiceException("CarSystem no data");
        }
        CarBean carBean = carBeanMap.get(carId);
        if (carBean == null) {
            throw new ServiceException("CarId does not exist");
        }
        return carBean.getCarSpeed();
    }

    @Override
    public String getCarMove(int carId) throws ServiceException {
        Map<Integer, CarBean> carBeanMap = mCarSystem.getCarBeanMap();
        if (carBeanMap == null) {
            throw new ServiceException("CarSystem no data");
        }
        CarBean carBean = carBeanMap.get(carId);
        if (carBean == null) {
            throw new ServiceException("CarId does not exist");
        }
        return carBean.getCarAction();
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }
}
