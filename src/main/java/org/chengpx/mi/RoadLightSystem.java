package org.chengpx.mi;

import org.chengpx.mi.dao.roadlight.RoadLightDao;
import org.chengpx.mi.domain.RoadLightBean;
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
 * 路灯系统
 * <p>
 * create at 2018/4/21 15:27 by chengpx
 */
@Component
public class RoadLightSystem {

    private static Logger sLogger = LoggerFactory.getLogger(RoadLightSystem.class);

    @Autowired
    private RoadLightDao mRoadLightDao;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private SenseSystem senseSystem;

    /**
     * 所有路灯集合
     */
    private Map<Integer, RoadLightBean> mLightBeanMap;

    public void start() {
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("RoadLightSystem start");
        }
        mLightBeanMap = mRoadLightDao.select();
        for (Map.Entry<Integer, RoadLightBean> roadLightBeanEntry : mLightBeanMap.entrySet()) {
            threadPoolTaskExecutor.execute(new Runnable() {

                private Random mRandom;

                @Override
                public void run() {
                    mRandom = new Random();
                    RoadLightBean roadLightBean = roadLightBeanEntry.getValue();
                    roadLightBean.setShouldRun(true);
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(roadLightBean + " start");
                    }
                    while (roadLightBean.getShouldRun()) {
                        if (senseSystem == null) {
                            try {
                                Thread.sleep(mRandom.nextInt(3000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        Map<String, SenseSystem.BaseSense> baseSenseMap = senseSystem.getBaseSenseMap();
                        if (baseSenseMap == null) {
                            try {
                                Thread.sleep(mRandom.nextInt(3000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        SenseSystem.BaseSense baseSense = baseSenseMap.get("LightIntensity");
                        if (baseSense == null) {
                            try {
                                Thread.sleep(mRandom.nextInt(3000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        if (!(baseSense instanceof SenseSystem.LightIntensity)) {
                            try {
                                Thread.sleep(mRandom.nextInt(3000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        SenseSystem.LightIntensity lightIntensity = (SenseSystem.LightIntensity) baseSense;
                        if (!RoadLightBean.ControlModeEnum.AUTO.getValue().equals(roadLightBean.getControlMode())) {
                            try {
                                if (sLogger.isDebugEnabled()) {
                                    sLogger.debug(roadLightBean + " ControlMode not Auto");
                                }
                                Thread.sleep(mRandom.nextInt(3000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        int lightIntensityValue = lightIntensity.getValue();
                        // 光照强度弱
                        if (lightIntensityValue < lightIntensity.getDown()) {
                            // 打开路灯
                            roadLightBean.setStatus(RoadLightBean.StatusEnum.OPEN.getValue());
                        } else {
                            // 关闭路灯
                            roadLightBean.setStatus(RoadLightBean.StatusEnum.CLOSE.getValue());
                        }
                        if (sLogger.isDebugEnabled()) {
                            sLogger.debug(roadLightBean.toString());
                        }
                        try {
                            // 10 * 1000
                            Thread.sleep(mRandom.nextInt(Constant.Cyscle.ROADLIGHT_RUN));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(roadLightBean + " stop");
                    }
                }

            });
        }
    }

    public void stop() {
        for (Map.Entry<Integer, RoadLightBean> roadLightBeanEntry : mLightBeanMap.entrySet()) {
            roadLightBeanEntry.getValue().setShouldRun(false);
        }
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("RoadLightSystem stop");
        }
    }

    public Map<Integer, RoadLightBean> getLightBeanMap() {
        if (mLightBeanMap == null) {
            return new HashMap<>();
        }
        return mLightBeanMap;
    }

}
