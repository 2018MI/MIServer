package org.chengpx.mi;

import org.chengpx.mi.dao.bus.BusDao;
import org.chengpx.mi.dao.bus.BusStationDao;
import org.chengpx.mi.domain.BusBean;
import org.chengpx.mi.domain.BusStationBean;
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
 * 公交巴士系统
 * <p>
 * create at 2018/4/19 14:57 by chengpx
 */
@Component
public class BusSystem implements Comparator<Integer> {

    private static Logger sLogger = LoggerFactory.getLogger(BusSystem.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private BusDao mBusDao;
    @Autowired
    private BusStationDao mBusStationDao;
    /**
     * 公交车集合
     */
    private Map<Integer, BusBean> mBusBeanMap;
    /**
     * 公交站台集合
     */
    private Map<Integer, BusStationBean> mBusStationBeanMap;
    /**
     * 公交路线图
     */
    private List<BusStationBean> mBusRoute;

    /**
     * 获取公交路线图
     *
     * @param busStationMap 所有公交站的数据
     * @return
     */
    private List<BusStationBean> getBusRoute(Map<Integer, BusStationBean> busStationMap) {
        List<Integer> BusStationIDList = new CopyOnWriteArrayList<>();
        BusStationIDList.addAll(busStationMap.keySet());
        BusStationIDList.sort(this);
        List<BusStationBean> busStationBeanList = new CopyOnWriteArrayList<>();
        for (Integer BusStationID : BusStationIDList) {
            BusStationBean busStationBean = busStationMap.get(BusStationID);
            busStationBeanList.add(busStationBean);
        }
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("公交路线图: " + busStationBeanList.toString());
        }
        return busStationBeanList;
    }

    //    public static BusSystem getInstance() {
    //        return sBusSystem;
    //    }

    /**
     * 公交系统启动
     */
    public void start() {
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("BusSystem start");
        }
        mBusStationBeanMap = mBusStationDao.select();
        mBusBeanMap = mBusDao.select();
        mBusRoute = getBusRoute(mBusStationBeanMap);
        for (Map.Entry<Integer, BusBean> busBeanEntry : mBusBeanMap.entrySet()) {
            busBeanEntry.getValue().setBusRoute(mBusRoute);
        }
        if (mBusBeanMap != null) {
            for (Map.Entry<Integer, BusBean> beanEntry : mBusBeanMap.entrySet()) {
                startBus(beanEntry.getValue());
            }
        }
    }

    private void startBus(BusBean busBean) {
        threadPoolTaskExecutor.execute(new Runnable() {

            private Random mRandom;

            @Override
            public void run() {
                mRandom = new Random();
                try {
                    Thread.sleep(mRandom.nextInt(1000 * 30));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                busBean.setShouldRun(true);
                if (sLogger.isDebugEnabled()) {
                    sLogger.debug(busBean.toString() + " start");
                }
                onAndOff(busBean, mRandom);
                while (busBean.getShouldRun()) {
                    try {
                        if (mRandom.nextInt(2) == 0) {
                            Thread.sleep((Constant.Cyscle.BUS_ARRIVAL - mRandom.nextInt(1000 * 10)));
                        } else {
                            Thread.sleep((Constant.Cyscle.BUS_ARRIVAL + mRandom.nextInt(1000 * 10)));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    onAndOff(busBean, mRandom);
                }
                if (sLogger.isDebugEnabled()) {
                    sLogger.debug(busBean.toString() + " stop");
                }
            }

            /**
             * 上下车
             *
             * @param busBean
             * @param random
             */
            private void onAndOff(BusBean busBean, Random random) {
                int bound = 0;
                Integer busCapacity = busBean.getBusCapacity();
                bound = busCapacity;
                if (busCapacity != 0) {
                    int less = random.nextInt(bound);
                    busBean.setBusCapacity(busCapacity - less);
                }
                busCapacity = busBean.getBusCapacity();
                bound = BusBean.MAX_NUM - busCapacity;
                if (bound != 0) {
                    int add = random.nextInt(bound);
                    busBean.setBusCapacity(busCapacity + add);
                }
                List<BusStationBean> busRoute = busBean.getBusRoute();
                if (busRoute != null && busRoute.size() > 0) {
                    // 已到达下一站
                    BusStationBean currentBusStation = busBean.getNextBusStation();
                    // 为第一站
                    if (currentBusStation == null) {
                        currentBusStation = busRoute.get(0);
                    }
                    busBean.setPreBusStation(currentBusStation);
                    busBean.setNextBusStation(busRoute.get((busRoute.indexOf(currentBusStation) + 1) % busRoute.size()));
                }
                if (sLogger.isDebugEnabled()) {
                    sLogger.debug(busBean.toString());
                }
            }

        });
    }

    /**
     * 公交系统关闭
     */
    public void stop() {
        for (Map.Entry<Integer, BusBean> beanEntry : mBusBeanMap.entrySet()) {
            beanEntry.getValue().setShouldRun(false);
        }
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("BusSystem stop");
        }
    }

    public Map<Integer, BusBean> getBusBeanMap() {
        if (mBusBeanMap == null) {
            return new HashMap<>();
        }
        return mBusBeanMap;
    }

    /**
     * 公交路线分布规则
     *
     * @param BusStationID1 公交站 id
     * @param BusStationID2 公交站 id
     * @return
     */
    @Override
    public int compare(Integer BusStationID1, Integer BusStationID2) {
        if (BusStationID1 != null && BusStationID2 != null) {
            return BusStationID1 - BusStationID2;
        } else {
            return 0;
        }
    }

    public Map<Integer, BusStationBean> getBusStationBeanMap() {
        if (mBusStationBeanMap == null) {
            return new HashMap<>();
        }
        return mBusStationBeanMap;
    }

    public List<BusStationBean> getBusRoute() {
        if (mBusRoute == null) {
            return new ArrayList<>();
        }
        return mBusRoute;
    }

}
