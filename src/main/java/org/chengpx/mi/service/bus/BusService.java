package org.chengpx.mi.service.bus;

import org.chengpx.framework.service.BaseService;
import org.chengpx.framework.service.ServiceException;

/**
 * 公交服务接口
 * <p>
 * create at 2018/4/19 20:36 by chengpx
 */
public interface BusService extends BaseService {

    Integer getBusCapacity(Integer busId) throws ServiceException;

}
