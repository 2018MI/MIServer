package org.chengpx.mi.service.parking.impl;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.framework.service.BaseServiceImpl;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.CarSystem;
import org.chengpx.mi.domain.parking.ParkingBean;
import org.chengpx.mi.domain.parking.ParkingSpacesBean;
import org.chengpx.mi.service.parking.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * create at 2018/4/20 13:00 by chengpx
 */
@Service
public class ParkServiceImpl extends BaseServiceImpl implements ParkService {

    @Autowired
    private CarSystem mCarSystem;

    @Override
    public void setParkRate(String rateType, Integer money) throws ServiceException {
        List<ParkingBean> parkingBeanList = mCarSystem.getParkingBeanList();
        if (parkingBeanList == null || parkingBeanList.size() == 0) {
            throw new ServiceException("CarSystem no data");
        }
        ParkingBean parkingBean = parkingBeanList.get(0);
        if (parkingBean == null) {
            throw new ServiceException("Parking does not exist");
        }
        boolean isRateTypeValid = false;
        for (ParkingBean.RateTypeEnum rateTypeEnum : ParkingBean.RateTypeEnum.values()) {
            if (rateTypeEnum.getValue().equals(rateType)) {
                isRateTypeValid = true;
                break;
            }
        }
        if (!isRateTypeValid) {
            throw new ServiceException("RateType is illegal");
        }
        parkingBean.setRateType(rateType);
        parkingBean.setMoney(money);
    }

    @Override
    public ParkingBean getParkRate() throws ServiceException {
        List<ParkingBean> parkingBeanList = mCarSystem.getParkingBeanList();
        if (parkingBeanList == null || parkingBeanList.size() == 0) {
            throw new ServiceException("CarSystem no data");
        }
        ParkingBean parkingBean = parkingBeanList.get(0);
        if (parkingBean == null) {
            throw new ServiceException("Parking does not exist");
        }
        ParkingBean retValParkingBean = new ParkingBean();
        retValParkingBean.setMoney(parkingBean.getMoney());
        retValParkingBean.setRateType(parkingBean.getRateType());
        return retValParkingBean;
    }

    @Override
    public List<Map<String, Integer>> getParkFree() throws ServiceException {
        List<ParkingBean> parkingBeanList = mCarSystem.getParkingBeanList();
        if (parkingBeanList == null || parkingBeanList.size() == 0) {
            throw new ServiceException("CarSystem no data");
        }
        ParkingBean parkingBean = parkingBeanList.get(0);
        if (parkingBean == null) {
            throw new ServiceException("Parking does not exist");
        }
        List<ParkingSpacesBean> availablePackSpacesList = mCarSystem.getAvailablePackSpacesList(parkingBean.getId());
        List<Map<String, Integer>> mapList = new CopyOnWriteArrayList<>();
        for (ParkingSpacesBean parkingSpacesBean : availablePackSpacesList) {
            Map<String, Integer> map = Collections.synchronizedMap(new LinkedHashMap<>());
            map.put("ParkFreeId", parkingSpacesBean.getId());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }
}
