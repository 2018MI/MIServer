package org.chengpx.mi.dao.trafficlight;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.trafficlight.SignalLightBean;

import java.util.Map;

/**
 * 信号灯持久化接口
 * <p>
 * create at 2018/4/18 12:43 by chengpx
 */
public interface SignalLightDao extends BaseDao {

    /**
     * 修改指定红绿灯的指定信号灯的 Time
     *
     * @param fkTrafficLightId 红绿灯 id
     * @param type             信号灯类型
     * @param time             信号灯时间
     * @return 对数据库的影响行数
     */
    int update(String fkTrafficLightId, String type, int time);

    /**
     * 查询指定符合 fk_TrafficLightId 的所有表记录
     *
     * @param fkTrafficLightId 红绿灯外键 id
     * @return 外键 fk_TrafficLightId 为指定值的所有表记录,每条记录以 type 作为 map 中的 key
     */
    Map<String, SignalLightBean> select(String fkTrafficLightId);

    /**
     * 修改指定红绿灯的指定信号灯的 Time(开启事务)
     *
     * @param fkTrafficLightId 红绿灯 id
     * @param type             信号灯类型
     * @param time             信号灯时间
     * @return 对数据库的影响行数
     */
    int updateTra(String fkTrafficLightId, String type, int time);

}
