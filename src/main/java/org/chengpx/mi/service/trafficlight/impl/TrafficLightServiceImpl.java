package org.chengpx.mi.service.trafficlight.impl;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.framework.service.BaseServiceImpl;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.TrafficLightSystem;
import org.chengpx.mi.domain.trafficlight.SignalLightBean;
import org.chengpx.mi.domain.trafficlight.TrafficLightBean;
import org.chengpx.mi.service.trafficlight.TrafficLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * create at 2018/4/18 11:36 by chengpx
 */
@Service
public class TrafficLightServiceImpl extends BaseServiceImpl implements TrafficLightService {

    @Autowired
    private TrafficLightSystem mTrafficLightSystem;

    @Override
    public void setTrafficLightNowStatus(String trafficLightId, String status, int time) throws ServiceException {
        Map<String, TrafficLightBean> trafficLightMap = mTrafficLightSystem.getTrafficLightMap();
        if (trafficLightMap == null || trafficLightMap.size() == 0) {
            throw new ServiceException("TrafficLightSystem no data");
        }
        TrafficLightBean trafficLightBean = trafficLightMap.get(trafficLightId);
        if (trafficLightBean == null) {
            throw new ServiceException("TrafficLightId does not exist");
        }
        Map<String, Map<String, SignalLightBean>> signalLightMapMap = mTrafficLightSystem.getSignalLightMapMap();
        if (signalLightMapMap == null || signalLightMapMap.size() == 0) {
            throw new ServiceException("TrafficLightSystem no data");
        }
        boolean isStatusValid = false;
        for (TrafficLightBean.StatusEnum statusEnum : TrafficLightBean.StatusEnum.values()) {
            if (statusEnum.getValue().equals(status)) {
                isStatusValid = true;
                break;
            }
        }
        if (!isStatusValid) {
            throw new ServiceException("Status is illegal");
        }
        trafficLightBean.setStatus(status);
        Map<String, SignalLightBean> signalLightBeanMap = signalLightMapMap.get(trafficLightId);
        if (signalLightBeanMap == null || signalLightBeanMap.size() == 0) {
            throw new ServiceException("TrafficLightId does not exist");
        }
        SignalLightBean signalLightBean = signalLightBeanMap.get(status);
        if (signalLightBean == null) {
            throw new ServiceException("Status does not exist");
        }
        signalLightBean.setTime(time);
    }

    @Override
    public void setTrafficLightConfig(String trafficLightId, int redTime, int yellowTime, int greenTime) throws ServiceException {
        Map<String, Map<String, SignalLightBean>> signalLightMapMap = mTrafficLightSystem.getSignalLightMapMap();
        if (signalLightMapMap == null || signalLightMapMap.size() == 0) {
            throw new ServiceException("TrafficLightSystem no data");
        }
        Map<String, SignalLightBean> signalLightMap = signalLightMapMap.get(trafficLightId);
        if (signalLightMap == null || signalLightMap.size() != 3) {
            throw new ServiceException("The number of SignalLightBean does not equal 3");
        }
        signalLightMap.get(TrafficLightBean.StatusEnum.RED.getValue()).setTime(redTime);
        signalLightMap.get(TrafficLightBean.StatusEnum.YELLOW.getValue()).setTime(yellowTime);
        signalLightMap.get(TrafficLightBean.StatusEnum.GREEN.getValue()).setTime(greenTime);
    }

    @Override
    public Map<String, Integer> getTrafficLightConfigAction(String trafficLightId) throws ServiceException {
        Map<String, Map<String, SignalLightBean>> signalLightMapMap = mTrafficLightSystem.getSignalLightMapMap();
        if (signalLightMapMap == null || signalLightMapMap.size() == 0) {
            throw new ServiceException("TrafficLightSystem no data");
        }
        Map<String, SignalLightBean> signalLightMap = signalLightMapMap.get(trafficLightId);
        if (signalLightMap == null || signalLightMap.size() != 3) {
            throw new ServiceException("The number of SignalLightBean does not equal 3");
        }
        Integer redTime = signalLightMap.get(TrafficLightBean.StatusEnum.RED.getValue()).getTime();
        Integer yellowTime = signalLightMap.get(TrafficLightBean.StatusEnum.YELLOW.getValue()).getTime();
        Integer greenTime = signalLightMap.get(TrafficLightBean.StatusEnum.GREEN.getValue()).getTime();
        Map<String, Integer> map = Collections.synchronizedMap(new LinkedHashMap<>());
        map.put("RedTime", redTime);
        map.put("YellowTime", yellowTime);
        map.put("GreenTime", greenTime);
        return map;
    }

    @Override
    public Map<String, Object> getTrafficLightNowStatus(String trafficLightId) throws ServiceException {
        Map<String, TrafficLightBean> trafficLightMap = mTrafficLightSystem.getTrafficLightMap();
        if (trafficLightMap == null || trafficLightMap.size() == 0) {
            throw new ServiceException("TrafficLightId no data");
        }
        TrafficLightBean trafficLightBean = trafficLightMap.get(trafficLightId);
        if (trafficLightBean == null) {
            throw new ServiceException("TrafficLightId does not exist");
        }
        Map<String, SignalLightBean> signalLightMap = mTrafficLightSystem.getSignalLightMapMap().get(trafficLightId);
        if (signalLightMap == null || signalLightMap.size() == 0) {
            throw new ServiceException("TrafficLightId does not exist");
        }
        String status = trafficLightBean.getStatus();
        SignalLightBean signalLightBean = signalLightMap.get(status);
        Map<String, Object> map = Collections.synchronizedMap(new LinkedHashMap<>());
        map.put("Status", status);
        map.put("Time", signalLightBean.getTime() + "");
        return map;
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }
}
