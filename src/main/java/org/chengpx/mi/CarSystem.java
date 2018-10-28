package org.chengpx.mi;

import org.chengpx.mi.dao.car.CarDao;
import org.chengpx.mi.dao.parking.ParkingDao;
import org.chengpx.mi.dao.parking.ParkingSpacesDao;
import org.chengpx.mi.domain.CarBean;
import org.chengpx.mi.domain.parking.ParkingBean;
import org.chengpx.mi.domain.parking.ParkingSpacesBean;
import org.chengpx.mi.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 小车系统
 * <p>
 * create at 2018/4/20 12:10 by chengpx
 */
@Component
public class CarSystem implements Comparator<Integer> {

    private static Logger sLogger = LoggerFactory.getLogger(CarSystem.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private CarDao mCarDao;
    @Autowired
    private ParkingDao mParkingDao;
    @Autowired
    private ParkingSpacesDao mParkingSpacesDao;
    /**
     * 所有小车
     */
    private Map<Integer, CarBean> mCarBeanMap;
    /**
     * 所有停车场
     */
    private Map<Integer, ParkingBean> mParkingBeanMap;
    /**
     * 停车站路线图
     */
    private List<ParkingBean> mParkingRoute;
    /**
     * 所有停车位集合
     */
    private Map<Integer, ParkingSpacesBean> mParkingSpacesBeanMap;
    /**
     * 所有停车场
     */
    private List<ParkingBean> mParkingBeanList;

    /**
     * 获取停车场路线图
     *
     * @param parkingBeanMap
     * @return
     */
    private List<ParkingBean> getParkingRoute(Map<Integer, ParkingBean> parkingBeanMap) {
        if (parkingBeanMap == null) {
            return null;
        }
        List<Integer> idList = new CopyOnWriteArrayList<>();
        idList.sort(this);
        idList.addAll(parkingBeanMap.keySet());
        List<ParkingBean> parkingBeanList = new CopyOnWriteArrayList<>();
        for (Integer id : idList) {
            ParkingBean parkingBean = mParkingBeanMap.get(id);
            parkingBeanList.add(parkingBean);
        }
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("停车场路线图: " + parkingBeanList);
        }
        return parkingBeanList;
    }

    public void start() {
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("CarSystem start");
        }
        mCarBeanMap = mCarDao.select();
        mParkingBeanMap = mParkingDao.selectToMap();
        mParkingBeanList = mParkingDao.select();
        mParkingSpacesBeanMap = mParkingSpacesDao.select();
        mParkingRoute = getParkingRoute(mParkingBeanMap);
        for (Map.Entry<Integer, CarBean> carBeanEntry : mCarBeanMap.entrySet()) {
            threadPoolTaskExecutor.execute(new Runnable() {

                private Random mRandom;

                @Override
                public void run() {
                    mRandom = new Random();
                    CarBean carBean = carBeanEntry.getValue();
                    carBean.setShouldRun(true);
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(carBean + " start");
                    }
                    while (carBean.getShouldRun()) {
                        if (mRandom.nextInt(2) == 0) {
                            for (ParkingBean parkingBean : mParkingRoute) {
                                if (mRandom.nextInt(2) == 0) {
                                    parking(carBean, parkingBean);
                                }
                                // 20 - 240
                                carBean.setCarSpeed(20 + mRandom.nextInt(220));
                                try {
                                    // 1000 * 30
                                    Thread.sleep(mRandom.nextInt(Constant.Cyscle.CAR_RUN));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            for (Map.Entry<Integer, ParkingBean> parkingBeanEntry : mParkingBeanMap.entrySet()) {
                                if (mRandom.nextInt(2) == 0) {
                                    parking(carBean, parkingBeanEntry.getValue());
                                }
                                // 20 - 240
                                carBean.setCarSpeed(20 + mRandom.nextInt(220));
                                try {
                                    Thread.sleep(mRandom.nextInt(Constant.Cyscle.CAR_RUN));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(carBean + " stop");
                    }
                }

                /**
                 * 停车
                 *
                 * @param carBean 小车
                 * @param parkingBean 停车场
                 */
                private void parking(CarBean carBean, ParkingBean parkingBean) {
                    if (!CarBean.CarActionEnum.START.getValue().equals(carBean.getCarAction())) {
                        if (sLogger.isDebugEnabled()) {
                            sLogger.debug(carBean.toString() + " CarAction is no " + CarBean.CarActionEnum.START.getValue());
                        }
                        return;
                    }
                    // 停车之前的小车速度
                    Integer parkingBeforeCarSpeed = carBean.getCarSpeed();
                    if (parkingBeforeCarSpeed == null) {
                        if (sLogger.isDebugEnabled()) {
                            sLogger.debug(carBean.toString() + " CarSpeed is null");
                        }
                        return;
                    }
                    if (parkingBeforeCarSpeed <= 0) {
                        if (sLogger.isDebugEnabled()) {
                            sLogger.debug(carBean.toString() + " CarSpeed <= 0");
                        }
                        return;
                    }
                    // 停车之后应该剩余的余额
                    int lessAfterBalance = carBean.getBalance() - parkingBean.getMoney();
                    if (lessAfterBalance < 0) {
                        if (sLogger.isDebugEnabled()) {
                            sLogger.debug(carBean.toString() + " Balance not enough. "
                                    + "lessAfterBalance = " + lessAfterBalance);
                        }
                        return;
                    }
                    // 查询停车场可用停车位
                    List<ParkingSpacesBean> availablePackSpacesList = getAvailablePackSpacesList(parkingBean.getId());
                    if (availablePackSpacesList == null || availablePackSpacesList.size() == 0) {
                        if (sLogger.isDebugEnabled()) {
                            sLogger.debug(carBean.toString() + " at " + parkingBean.toString() + " no parking available");
                        }
                        return;
                    }
                    // 随机挑选一个停车位
                    ParkingSpacesBean parkingSpacesBean = availablePackSpacesList
                            .get(mRandom.nextInt(availablePackSpacesList.size()));
                    // 停车之前停车位状态
                    Integer parkingBeforeStatus = parkingSpacesBean.getStatus();
                    // 停车之前的小车的 CarAction
                    String parkingBeforeCarAction = carBean.getCarAction();
                    // 停车之前的小车账户余额
                    Integer parkingBeforeBalance = carBean.getBalance();
                    // 尝试 3 次
                    for (int count = 0; count < 3; count++) {
                        try {
                            parkingSpacesBean.setStatus(ParkingSpacesBean.StatusEnum.UNAVAILABLE.getValue());
                            carBean.setCarAction(CarBean.CarActionEnum.STOP.getValue());
                            carBean.setCarSpeed(0);
                            carBean.setBalance(lessAfterBalance);
                            String rateType = parkingBean.getRateType();
                            if (ParkingBean.RateTypeEnum.HOUR.getValue().equals(rateType)) {
                                try {
                                    // 1000 * 10
                                    Thread.sleep(mRandom.nextInt(Constant.Cyscle.PARKING_HOUR));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else if (ParkingBean.RateTypeEnum.COUNT.getValue().equals(rateType)) {
                                try {
                                    // 1000 * 30
                                    Thread.sleep(mRandom.nextInt(Constant.Cyscle.PARKING_COUNT));
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            boolean isTry = true;
                            // 停车之后停车位状态
                            Integer parkingAfterStatus = parkingSpacesBean.getStatus();
                            // 停车之后小车的 CarAction
                            String parkingAfterCarAction = carBean.getCarAction();
                            Integer parkingAfterCarSpeed = carBean.getCarSpeed();
                            while (isTry) {
                                try {
                                    parkingSpacesBean.setStatus(ParkingSpacesBean.StatusEnum.AVAILABLE.getValue());
                                    carBean.setCarAction(CarBean.CarActionEnum.START.getValue());
                                    carBean.setCarSpeed(parkingBeforeCarSpeed);
                                    isTry = false;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    parkingSpacesBean.setStatus(parkingAfterStatus);
                                    carBean.setCarAction(parkingAfterCarAction);
                                    carBean.setCarSpeed(parkingAfterCarSpeed);
                                    try {
                                        Thread.sleep(1000 * 30);
                                    } catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                            if (sLogger.isDebugEnabled()) {
                                sLogger.debug(carBean + " end parking");
                            }
                            return;
                        } catch (Exception e) {
                            parkingSpacesBean.setStatus(parkingBeforeStatus);
                            carBean.setCarAction(parkingBeforeCarAction);
                            carBean.setCarSpeed(parkingBeforeCarSpeed);
                            carBean.setBalance(parkingBeforeBalance);
                            e.printStackTrace();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(carBean + " parking failed");
                    }
                }

            });
        }
    }

    /**
     * 获取停车场可用的停车位
     *
     * @param parkingId 停车场 id
     * @return 停车场内所有可用的停车位
     */
    public List<ParkingSpacesBean> getAvailablePackSpacesList(int parkingId) {
        List<ParkingSpacesBean> parkingSpacesBeanList = new CopyOnWriteArrayList<>();
        for (Map.Entry<Integer, ParkingSpacesBean> parkingSpacesBeanEntry : mParkingSpacesBeanMap.entrySet()) {
            ParkingSpacesBean parkingSpacesBean = parkingSpacesBeanEntry.getValue();
            if (!ParkingSpacesBean.StatusEnum.AVAILABLE.getValue().equals(parkingSpacesBean.getStatus())) {
                continue;
            }
            ParkingBean parkingBean = parkingSpacesBean.getParkingBean();
            if (parkingBean != null) {
                Integer parkingBeanId = parkingBean.getId();
                if (parkingBeanId != null) {
                    if (parkingBeanId == parkingId) {
                        parkingSpacesBeanList.add(parkingSpacesBean);
                    }
                }
            }
        }
        return parkingSpacesBeanList;
    }

    public void stop() {
        for (Map.Entry<Integer, CarBean> carBeanEntry : mCarBeanMap.entrySet()) {
            carBeanEntry.getValue().setShouldRun(false);
        }
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("CarSystem stop");
        }
    }

    public Map<Integer, CarBean> getCarBeanMap() {
        if (mCarBeanMap == null) {
            return new HashMap<>();
        }
        return mCarBeanMap;
    }

    public Map<Integer, ParkingBean> getParkingBeanMap() {
        if (mParkingBeanMap == null) {
            return new HashMap<>();
        }
        return mParkingBeanMap;
    }

    public List<ParkingBean> getParkingRoute() {
        if (mParkingRoute == null) {
            return new ArrayList<>();
        }
        return mParkingRoute;
    }

    public Map<Integer, ParkingSpacesBean> getParkingSpacesBeanMap() {
        if (mParkingSpacesBeanMap == null) {
            return new HashMap<>();
        }
        return mParkingSpacesBeanMap;
    }

    public List<ParkingBean> getParkingBeanList() {
        if (mParkingBeanList == null) {
            return new ArrayList<>();
        }
        return mParkingBeanList;
    }

    /**
     * 停车场分布规则
     *
     * @param id1 停车场 id
     * @param id2 停车场 id
     * @return
     */
    @Override
    public int compare(Integer id1, Integer id2) {
        return id1 - id2;
    }

}
