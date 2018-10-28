package org.chengpx.mi.dao.bus;

import org.chengpx.framework.dao.BaseDao;
import org.chengpx.mi.domain.BusBean;

import java.util.Map;

/**
 * 公交巴士数据访问层接口
 * <p>
 * create at 2018/4/19 14:59 by chengpx
 */
public interface BusDao extends BaseDao {

    /**
     * 查询所有公交车表数据
     *
     * @return 每行数据以 BusBean 封装存储到 map 中, BusId 作为 map 的 key
     */
    Map<Integer, BusBean> select();

}
