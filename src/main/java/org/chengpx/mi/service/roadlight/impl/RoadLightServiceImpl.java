package org.chengpx.mi.service.roadlight.impl;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.framework.service.BaseServiceImpl;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.RoadLightSystem;
import org.chengpx.mi.domain.RoadLightBean;
import org.chengpx.mi.service.roadlight.RoadLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * create at 2018/4/21 13:38 by chengpx
 */
@Service
public class RoadLightServiceImpl extends BaseServiceImpl implements RoadLightService {

    @Autowired
    private RoadLightSystem mRoadLightSystem;

    @Override
    public String getRoadLightStatus(int roadLightId) throws ServiceException {
        Map<Integer, RoadLightBean> lightBeanMap = mRoadLightSystem.getLightBeanMap();
        if (lightBeanMap == null || lightBeanMap.size() == 0) {
            throw new ServiceException("RoadLightSystem not data");
        }
        RoadLightBean roadLightBean = lightBeanMap.get(roadLightId);
        if (roadLightBean == null) {
            throw new ServiceException("RoadLightId does not exist");
        }
        return roadLightBean.getStatus();
    }

    @Override
    public void setRoadLightStatusAction(int roadLightId, String action) throws ServiceException {
        Map<Integer, RoadLightBean> lightBeanMap = mRoadLightSystem.getLightBeanMap();
        if (lightBeanMap == null || lightBeanMap.size() == 0) {
            throw new ServiceException("RoadLightSystem no data");
        }
        RoadLightBean roadLightBean = lightBeanMap.get(roadLightId);
        if (roadLightBean == null) {
            throw new ServiceException("RoadLightId does not exist");
        }
        // 非手动控制模式
        if (!RoadLightBean.ControlModeEnum.MANUAL.getValue().equals(roadLightBean.getControlMode())) {
            throw new ServiceException("ControlMode not Manual");
        }
        boolean isActionValid = false;
        for (RoadLightBean.StatusEnum statusEnum : RoadLightBean.StatusEnum.values()) {
            if (statusEnum.getValue().equals(action)) {
                isActionValid = true;
                break;
            }
        }
        if (!isActionValid) {
            throw new ServiceException("Action is illegal");
        }
        roadLightBean.setAction(action);
        roadLightBean.setStatus(action);
    }

    @Override
    public void setRoadLightControlMode(String controlMode) throws ServiceException {
        Map<Integer, RoadLightBean> lightBeanMap = mRoadLightSystem.getLightBeanMap();
        if (lightBeanMap == null || lightBeanMap.size() == 0) {
            throw new ServiceException("RoadLightSystem no data");
        }
        boolean isControlModeValid = false;
        for (RoadLightBean.ControlModeEnum controlModeEnum : RoadLightBean.ControlModeEnum.values()) {
            if (controlModeEnum.getValue().equals(controlMode)) {
                isControlModeValid = true;
                break;
            }
        }
        if (!isControlModeValid) {
            throw new ServiceException("ControlMode is illegal");
        }
        for (Map.Entry<Integer, RoadLightBean> roadLightBeanEntry : lightBeanMap.entrySet()) {
            RoadLightBean roadLightBean = roadLightBeanEntry.getValue();
            roadLightBean.setControlMode(controlMode);
        }
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }

}
