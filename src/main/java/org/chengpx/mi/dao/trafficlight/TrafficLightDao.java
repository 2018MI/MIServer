package org.chengpx.mi.dao.trafficlight;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.trafficlight.TrafficLightBean;

import java.util.Map;

/**
 * 红绿灯持久化接口
 * <p>
 * create at 2018/4/18 11:38 by chengpx
 */
public interface TrafficLightDao extends BaseDao {


    /**
     * 获取所有交通灯表记录数据
     *
     * @return 获取所有交通灯表记录数据, 以 TrafficLightId 作为 map 的 key
     */
    Map<String, TrafficLightBean> select();

    /**
     * 修改指定 TrafficLightId 表记录的 Status 值
     *
     * @param trafficLightId 交通灯 id
     * @param status         交通灯当前的信号灯
     * @return 对数据库的影响行数
     */
    int update(String trafficLightId, String status);

}
