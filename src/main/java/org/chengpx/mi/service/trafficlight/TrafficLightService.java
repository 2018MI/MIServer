package org.chengpx.mi.service.trafficlight;

import org.chengpx.framework.service.ServiceException;

import java.util.Map;

/**
 * 交通灯业务逻辑接口
 * <p>
 * create at 2018/4/18 11:29 by chengpx
 */
public interface TrafficLightService {

    /**
     * 设置指定红绿灯的信号灯的时长
     *
     * @param trafficLightId 红绿灯 id
     * @param status         信号灯名称
     * @param time           信号灯时间
     */
    void setTrafficLightNowStatus(String trafficLightId, String status, int time) throws ServiceException;

    /**
     * 对指定的红绿灯信号灯进行配置
     *
     * @param trafficLightId 红绿灯编号
     * @param redTime        红灯时长
     * @param yellowTime     黄灯时长
     * @param greenTime      绿灯时长
     */
    void setTrafficLightConfig(String trafficLightId, int redTime, int yellowTime, int greenTime) throws ServiceException;

    /**
     * 查询红绿灯的各信号灯的时长
     *
     * @param trafficLightId 红绿灯编号
     * @return 红绿灯对应的所有信号灯的时长数据, key 为信号灯类型, value 为信号灯时长
     */
    Map<String, Integer> getTrafficLightConfigAction(String trafficLightId) throws ServiceException;


    /**
     * 查询红绿灯的当前状态
     *
     * @param trafficLightId 红绿灯 id
     * @return 红绿灯当前状态信息(当前信号灯, 以及时间)
     */
    Map<String, Object> getTrafficLightNowStatus(String trafficLightId) throws ServiceException;

}
