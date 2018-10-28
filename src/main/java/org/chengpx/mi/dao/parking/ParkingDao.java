package org.chengpx.mi.dao.parking;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.parking.ParkingBean;

import java.util.List;
import java.util.Map;

/**
 * 停车场数据访问层接口
 * <p>
 * create at 2018/4/20 15:27 by chengpx
 */
public interface ParkingDao extends BaseDao {

    /**
     * 查询 parking 表的所有记录
     *
     * @return 每行记录存储到 ParkingBean, 返回所有记录的 list 集合
     */
    List<ParkingBean> select();

    /**
     * 更新 parking 表, 指定 id 行的 RateType, Money 字段值
     *
     * @param id       停车场 id
     * @param rateType 停车场费率类型
     * @param money    费率
     * @return 对数据库的影响行数
     * @see ParkingBean.RateTypeEnum
     */
    int update(int id, String rateType, Integer money);

    /**
     * 查询 parking 表的所有记录
     *
     * @return 每行记录存储到 ParkingBean, ParkingBean.id 作为 map 的 key
     */
    Map<Integer, ParkingBean> selectToMap();

}
