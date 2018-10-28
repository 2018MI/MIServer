package org.chengpx.mi;


import org.chengpx.mi.dao.trafficlight.SignalLightDao;
import org.chengpx.mi.dao.trafficlight.TrafficLightDao;
import org.chengpx.mi.domain.trafficlight.SignalLightBean;
import org.chengpx.mi.domain.trafficlight.TrafficLightBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 交通灯控制系统
 * <p>
 * create at 2018/4/18 15:35 by chengpx
 */
@Component
public class TrafficLightSystem {

    private static Logger sLogger = LoggerFactory.getLogger(TrafficLightSystem.class);

    @Autowired
    private SignalLightDao mSignalLightDao;
    @Autowired
    private TrafficLightDao mTrafficLightDao;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 所有交通灯的所有信号灯集合
     */
    private Map<String, Map<String, SignalLightBean>> signalLightMapMap;
    /**
     * 所有交通灯
     */
    private Map<String, TrafficLightBean> trafficLightMap;

    /**
     * 启动系统
     */
    public void start() {
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("TrafficLightSystem start");
        }
        trafficLightMap = mTrafficLightDao.select();
        signalLightMapMap = Collections.synchronizedMap(new LinkedHashMap<>());
        for (Map.Entry<String, TrafficLightBean> trafficLightEntry : trafficLightMap.entrySet()) {
            TrafficLightBean trafficLightBean = trafficLightEntry.getValue();
            Map<String, SignalLightBean> signalLightMap = mSignalLightDao.select(trafficLightBean.getTrafficLightId());
            signalLightMapMap.put(trafficLightBean.getTrafficLightId(), signalLightMap);
        }
        for (Map.Entry<String, TrafficLightBean> trafficLightEntry : trafficLightMap.entrySet()) {
            TrafficLightBean trafficLightBean = trafficLightEntry.getValue();
            startTrafficLight(trafficLightBean, signalLightMapMap.get(trafficLightBean.getTrafficLightId()));
        }
    }


    /**
     * 红绿灯开始工作
     *
     * @param trafficLightBean 红绿灯对象
     * @param signalLightMap   该红绿灯对应的所有信号灯集合
     */
    private void startTrafficLight(TrafficLightBean trafficLightBean, Map<String, SignalLightBean> signalLightMap) {
        if (signalLightMap.size() == 3) {
            threadPoolTaskExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    SignalLightBean red = signalLightMap.get(TrafficLightBean.StatusEnum.RED.getValue());
                    SignalLightBean yellow = signalLightMap.get(TrafficLightBean.StatusEnum.YELLOW.getValue());
                    SignalLightBean green = signalLightMap.get(TrafficLightBean.StatusEnum.GREEN.getValue());
                    trafficLightBean.setShouldRun(true);
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(trafficLightBean.toString() + " start");
                    }
                    while (trafficLightBean.getShouldRun()) {
                        try {
                            String status = trafficLightBean.getStatus();
                            if (TrafficLightBean.StatusEnum.RED.getValue().equals(status)) {
                                Thread.sleep(red.getTime() * 1000);
                                trafficLightBean.setStatus(TrafficLightBean.StatusEnum.YELLOW.getValue());
                            } else if (TrafficLightBean.StatusEnum.YELLOW.getValue().equals(status)) {
                                Thread.sleep(yellow.getTime() * 1000);
                                trafficLightBean.setStatus(TrafficLightBean.StatusEnum.GREEN.getValue());
                            } else if (TrafficLightBean.StatusEnum.GREEN.getValue().equals(status)) {
                                Thread.sleep(green.getTime() * 1000);
                                trafficLightBean.setStatus(TrafficLightBean.StatusEnum.RED.getValue());
                            }
                            // mTrafficLightDao.update(trafficLightBean.getTrafficLightId(), trafficLightBean.getStatus());
                            if (sLogger.isDebugEnabled()) {
                                sLogger.debug(trafficLightBean.toString());
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (sLogger.isDebugEnabled()) {
                        sLogger.debug(trafficLightBean.toString() + " stop");
                    }
                }

            });
        }
    }

    /**
     * 停止系统
     */
    public void stop() {
        for (Map.Entry<String, TrafficLightBean> lightEntry : trafficLightMap.entrySet()) {
            lightEntry.getValue().setShouldRun(false);
        }
        if (sLogger.isDebugEnabled()) {
            sLogger.debug("TrafficLightSystem stop");
        }
    }

    public Map<String, TrafficLightBean> getTrafficLightMap() {
        if (trafficLightMap == null) {
            return new HashMap<>();
        }
        return trafficLightMap;
    }

    public Map<String, Map<String, SignalLightBean>> getSignalLightMapMap() {
        if (signalLightMapMap == null) {
            return new HashMap<>();
        }
        return signalLightMapMap;
    }

}
