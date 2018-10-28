package org.chengpx.mi.service.bus.impl;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.framework.service.BaseServiceImpl;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.BusSystem;
import org.chengpx.mi.domain.BusBean;
import org.chengpx.mi.service.bus.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * create at 2018/4/19 20:38 by chengpx
 */
@Service
public class BusServiceImpl extends BaseServiceImpl implements BusService {

    @Autowired
    private BusSystem mBusSystem;

    @Override
    public Integer getBusCapacity(Integer busId) throws ServiceException {
        Map<Integer, BusBean> busMap = mBusSystem.getBusBeanMap();
        if (busMap == null || busMap.size() == 0) {
            throw new ServiceException("BusSystem no data");
        }
        BusBean busBean = busMap.get(busId);
        if (busBean == null) {
            throw new ServiceException("BusId does not exist");
        }
        return busBean.getBusCapacity();
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }

}
