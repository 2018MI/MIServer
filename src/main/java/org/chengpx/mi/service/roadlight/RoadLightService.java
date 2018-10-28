package org.chengpx.mi.service.roadlight;

import org.chengpx.framework.service.ServiceException;

/**
 * 路灯服务逻辑层接口
 * <p>
 * create at 2018/4/21 13:37 by chengpx
 */
public interface RoadLightService {

    /**
     * 查询当前路灯的状态
     *
     * @param roadLightId 路灯 id
     * @return 路灯对应的状态
     */
    String getRoadLightStatus(int roadLightId) throws ServiceException;

    /**
     * 手动打开关闭指定路灯
     *
     * @param roadLightId 路灯 id
     * @param action      操作动作
     */
    void setRoadLightStatusAction(int roadLightId, String action) throws ServiceException;

    /**
     * 设置自动手动控制模式
     *
     * @param controlMode 路灯控制模式
     * @see org.chengpx.mi.domain.RoadLightBean.ControlModeEnum
     */
    void setRoadLightControlMode(String controlMode) throws ServiceException;

}
