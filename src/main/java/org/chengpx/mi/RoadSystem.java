package org.chengpx.mi;

import org.chengpx.mi.dao.road.RoadDao;
import org.chengpx.mi.domain.RoadBean;
import org.chengpx.mi.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 道路系统
 * <p>
 * create at 2018/4/20 10:44 by chengpx
 */
@Component
public class RoadSystem {

    private static Logger sLogger = LoggerFactory.getLogger(RoadSystem.class);

    @Autowired
    private RoadDao mRoadDao;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 所有道路的集合
     */
    private Map<Integer, RoadBean> mRoadBeanMap;

    public void start() {
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("RoadSystem start");
        }
        mRoadBeanMap = mRoadDao.select();
        for (Map.Entry<Integer, RoadBean> roadBeanEntry : mRoadBeanMap.entrySet()) {
            threadPoolTaskExecutor.execute(new Runnable() {

                private Random mRandom;

                @Override
                public void run() {
                    mRandom = new Random();
                    RoadBean roadBean = roadBeanEntry.getValue();
                    roadBean.setShouldRun(true);
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(roadBean + " start");
                    }
                    while (roadBean.getShouldRun()) {
                        try {
                            // 1000 * 10
                            Thread.sleep(mRandom.nextInt(Constant.Cyscle.ROAD_STATUS));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        RoadBean.StatusEnum[] statusEnumArr = RoadBean.StatusEnum.values();
                        // 随机获取一个状态
                        RoadBean.StatusEnum statusEnum = statusEnumArr[mRandom.nextInt(statusEnumArr.length)];
                        roadBean.setStatus(statusEnum.getValue());
                        if (sLogger.isDebugEnabled()) {
                            sLogger.debug(roadBean.toString());
                        }
                    }
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(roadBean + " stop");
                    }
                }

            });
        }
    }

    public void stop() {
        for (Map.Entry<Integer, RoadBean> roadBeanEntry : mRoadBeanMap.entrySet()) {
            roadBeanEntry.getValue().setShouldRun(false);
        }
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("RoadSystem stop");
        }
    }

    public Map<Integer, RoadBean> getRoadBeanMap() {
        if (mRoadBeanMap == null) {
            return new HashMap<>();
        }
        return mRoadBeanMap;
    }

}
