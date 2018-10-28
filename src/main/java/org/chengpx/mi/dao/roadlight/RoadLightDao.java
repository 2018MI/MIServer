package org.chengpx.mi.dao.roadlight;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.RoadLightBean;

import java.util.Map;

/**
 * 路灯数据访问层接口
 * <p>
 * create at 2018/4/21 13:43 by chengpx
 */
public interface RoadLightDao extends BaseDao {

    /**
     * 查询 roadlight 表指定 RoadLightId 字段的 Status 字段值
     *
     * @param roadLightId 路灯 id
     * @return 路灯的状态
     */
    String selectStatus(int roadLightId);

    /**
     * 更新 roadlight 表指定 RoadLightId 字段的 Status 字段值
     *
     * @param roadLightId 路灯 id
     * @return 数据库的影响行数
     */
    int update(int roadLightId, String status);

    /**
     * 查询 roadlight 表指定 RoadLightId 字段的 ControlMode 字段值
     *
     * @param roadLightId 路灯 id
     * @return 路灯的控制模式
     */
    String selectControlMode(int roadLightId);

    /**
     * 更新 roadlight 表所有记录的 ControlMode 字段值
     *
     * @param controlMode 路灯控制模式
     * @return 对数据库的影响行数
     */
    int update(String controlMode);

    /**
     * 查询 roadlight 表中的所有记录
     *
     * @return 每行记录封装到 RoadLightBean, RoadLightId 作为 map 的 key
     */
    Map<Integer, RoadLightBean> select();

}
