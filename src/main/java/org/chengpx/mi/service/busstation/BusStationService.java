package org.chengpx.mi.service.busstation;

import org.chengpx.framework.service.BaseService;
import org.chengpx.framework.service.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * 站台服务逻辑接口
 * <p>
 * create at 2018/4/19 21:51 by chengpx
 */
public interface BusStationService extends BaseService {

    /**
     * 获取指定站台 id 的信息
     *
     * @param busStationID 站台 id
     * @return 所有公交车距离指定站台的距离
     */
    List<Map<String, Integer>> getBusstationInfo(int busStationID) throws ServiceException;

}
