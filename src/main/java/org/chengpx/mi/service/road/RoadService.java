package org.chengpx.mi.service.road;

import org.chengpx.framework.service.ServiceException;

/**
 * 道路服务逻辑层接口
 * <p>
 * create at 2018/4/20 11:25 by chengpx
 */
public interface RoadService {

    /**
     * 查询道路的拥挤状态
     *
     * @param roadId 道路编号
     * @return 道路的拥挤状态
     */
    Integer getRoadStatus(Integer roadId) throws ServiceException;

}
