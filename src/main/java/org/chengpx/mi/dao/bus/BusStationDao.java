package org.chengpx.mi.dao.bus;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.BusStationBean;

import java.util.Map;

/**
 * 公交站台数据访问层
 * <p>
 * create at 2018/4/19 19:33 by chengpx
 */
public interface BusStationDao extends BaseDao {

    /**
     * 查询公交站台表的所有数据
     *
     * @return 每行记录封装到 BusStationBean, 所有 BusBean 存储到 map 中, BusStationID 作为 key
     */
    Map<Integer, BusStationBean> select();

}
