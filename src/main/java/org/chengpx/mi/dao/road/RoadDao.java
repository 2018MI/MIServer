package org.chengpx.mi.dao.road;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.RoadBean;

import java.util.Map;

/**
 * 道路数据访问层接口
 * <p>
 * create at 2018/4/20 10:46 by chengpx
 */
public interface RoadDao extends BaseDao {

    /**
     * 获取 road 表的所有表数据
     *
     * @return 每行表数据封装到一个 RoadBean, RoadId 作为 map 的 key, RoadBean 作为 value, 返回所有表记录的 map
     */
    Map<Integer, RoadBean> select();

}
