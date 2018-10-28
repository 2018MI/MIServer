package org.chengpx.mi.service.busstation.impl;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.framework.service.BaseServiceImpl;
import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.BusSystem;
import org.chengpx.mi.domain.BusBean;
import org.chengpx.mi.domain.BusStationBean;
import org.chengpx.mi.service.busstation.BusStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * create at 2018/4/19 21:53 by chengpx
 */
@Service
public class BusStationServiceImpl extends BaseServiceImpl implements BusStationService {

    @Autowired
    private BusSystem mBusSystem;

    @Override
    public List<Map<String, Integer>> getBusstationInfo(int busStationID) throws ServiceException {
        Map<Integer, BusBean> busMap = mBusSystem.getBusBeanMap();
        if (busMap == null || busMap.size() == 0) {
            throw new ServiceException("BusSystem no data");
        }
        List<Map<String, Integer>> mapList = new CopyOnWriteArrayList<>();
        for (Map.Entry<Integer, BusBean> busBeanEntry : busMap.entrySet()) {
            BusBean busBean = busBeanEntry.getValue();
            // 公交路线图
            List<BusStationBean> busRoute = busBean.getBusRoute();
            if (busRoute == null || busRoute.size() == 0) {
                throw new ServiceException("BusRoute no data");
            }
            // 获取上一个公交站点信息
            BusStationBean preBusStation = busBean.getPreBusStation();
            // 公交车还未运行
            if (busBean.getShouldRun() == null || !busBean.getShouldRun() || preBusStation == null) {
                preBusStation = busRoute.get(0);
            } else {
                int prePos = busRoute.indexOf(preBusStation);
                Map<Integer, BusStationBean> busStationMap = mBusSystem.getBusStationBeanMap();
                if (busStationMap == null || busStationMap.size() == 0) {
                    throw new ServiceException("BusSystem no data");
                }
                BusStationBean aimBusStation = busStationMap.get(busStationID);
                if (aimBusStation == null) {
                    throw new ServiceException("BusStationID does not exist");
                }
                // 公交车距离目标站台还需要经过的所有站台路线图(包括目标站台)
                List<BusStationBean> busStationBeanList = new CopyOnWriteArrayList<>();
                int count = 0;
                int index;
                for (index = prePos + 1;
                     (busRoute.get((index % busRoute.size())) != aimBusStation) && count < busRoute.size();
                     index++, count++) {
                    BusStationBean busStationBean = busRoute.get((index % busRoute.size()));
                    busStationBeanList.add(busStationBean);
                }
                busStationBeanList.add(aimBusStation);
                if (busStationBeanList.size() == 0) {
                    throw new ServiceException("aimBusStation does not exist");
                }
                Map<String, Integer> map = Collections.synchronizedMap(new LinkedHashMap<>());
                // 公交距离目标站台的距离
                int distance = 0;
                Random random = new Random();
                for (index = 0; index < busStationBeanList.size(); index++) {
                    BusStationBean busStationBean = busStationBeanList.get(index);
                    if (index == busStationBeanList.size() - 1) {
                        distance += random.nextInt(busStationBean.getDistance());
                    } else {
                        distance += busStationBean.getDistance();
                    }
                }
                map.put("BusId", busBean.getBusId());
                map.put("Distance", distance);
                mapList.add(map);
            }
        }
        return mapList;
    }

    @Override
    public BaseDao getBaseDao() {
        return null;
    }

}
