package org.chengpx.mi.service.sense.impl;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.framework.service.BaseServiceImpl;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.SenseSystem;
import org.chengpx.mi.service.sense.SenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * create at 2018/4/20 12:41 by chengpx
 */
@Service
public class SenseServiceImpl extends BaseServiceImpl implements SenseService {

    @Autowired
    private SenseSystem mSenseSystem;

    @Override
    public Map<String, Integer> getAllSense() throws ServiceException {
        Map<String, Integer> dataMap = mSenseSystem.getDataMap();
        if (dataMap == null || dataMap.size() == 0) {
            throw new ServiceException("SenseSystem no data");
        }
        return dataMap;
    }

    @Override
    public Integer getSenseByName(String senseName) throws ServiceException {
        Map<String, Integer> dataMap = mSenseSystem.getDataMap();
        if (dataMap == null || dataMap.size() == 0) {
            throw new ServiceException("SenseSystem no data");
        }
        Integer value = dataMap.get(senseName);
        if (value == null) {
            throw new ServiceException("SenseName does not exist");
        }
        return value;
    }

    @Override
    public Map<String, Integer> getLightSenseValve() throws ServiceException {
        Map<String, SenseSystem.BaseSense> baseSenseMap = mSenseSystem.getBaseSenseMap();
        if (baseSenseMap == null || baseSenseMap.size() == 0) {
            throw new ServiceException("SenseSystem no data");
        }
        SenseSystem.BaseSense baseSense = baseSenseMap.get("LightIntensity");
        if (baseSense == null) {
            throw new ServiceException("LightIntensity no start");
        }
        if (baseSense instanceof SenseSystem.LightIntensity) {
            SenseSystem.LightIntensity lightIntensity = (SenseSystem.LightIntensity) baseSense;
            Map<String, Integer> map = Collections.synchronizedMap(new LinkedHashMap<>());
            map.put("Down", lightIntensity.getDown());
            map.put("Up", lightIntensity.getUp());
            return map;
        }
        return null;
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }

}
