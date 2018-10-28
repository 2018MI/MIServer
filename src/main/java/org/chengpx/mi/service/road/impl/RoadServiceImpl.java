package org.chengpx.mi.service.road.impl;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.framework.service.BaseServiceImpl;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.RoadSystem;
import org.chengpx.mi.domain.RoadBean;
import org.chengpx.mi.service.road.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * create at 2018/4/20 11:26 by chengpx
 */
@Service
public class RoadServiceImpl extends BaseServiceImpl implements RoadService {

    @Autowired
    private RoadSystem mRoadSystem;

    @Override
    public Integer getRoadStatus(Integer roadId) throws ServiceException {
        Map<Integer, RoadBean> roadBeanMap = mRoadSystem.getRoadBeanMap();
        if (roadBeanMap == null || roadBeanMap.size() == 0) {
            throw new ServiceException("RoadSystem no data");
        }
        RoadBean roadBean = roadBeanMap.get(roadId);
        if (roadBean == null) {
            throw  new ServiceException("RoadId does not exist");
        }
        return roadBean.getStatus();
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }

}
