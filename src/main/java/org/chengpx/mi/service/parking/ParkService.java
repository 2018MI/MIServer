package org.chengpx.mi.service.parking;

import org.chengpx.framework.service.ServiceException;
import org.chengpx.mi.domain.parking.ParkingBean;

import java.util.List;
import java.util.Map;

/**
 * 停车场服务逻辑层接口
 * <p>
 * create at 2018/4/20 12:58 by chengpx
 */
public interface ParkService {

    /**
     * 停车场费率设置
     *
     * @param rateType 费率类型
     * @param money    费率
     * @see ParkingBean.RateTypeEnum
     */
    void setParkRate(String rateType, Integer money) throws ServiceException;

    /**
     * 查询当前费率信息
     *
     * @return 当前停车场的 RateType, Money 数据
     */
    ParkingBean getParkRate() throws ServiceException;

    /**
     * 查询当前空闲车位
     *
     * @return 当前停车场的所有空闲车位, map 中 ParkFreeId 为 key, ParkingSpacesBean.id 为 map 的 value
     */
    List<Map<String, Integer>> getParkFree() throws ServiceException;

}
