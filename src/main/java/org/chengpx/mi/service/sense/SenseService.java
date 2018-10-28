package org.chengpx.mi.service.sense;

import org.chengpx.framework.service.ServiceException;

import java.util.Map;

/**
 * create at 2018/4/20 12:39 by chengpx
 */
public interface SenseService {

    /**
     * 查询所有传感器的当前值
     *
     * @return 所有传感器当前数据集合, key 传感器名称, value 数据
     */
    Map<String, Integer> getAllSense() throws ServiceException;

    /**
     * 查询单个传感器的当前值
     *
     * @param senseName 传感器名称
     * @return 传感器当前的数据
     */
    Integer getSenseByName(String senseName) throws ServiceException;

    /**
     * 查询光照传感器阀值
     *
     * @return 光照强度传感器阈值: {"Down":"1111","Up":"2222"}
     */
    Map<String, Integer> getLightSenseValve() throws ServiceException;

}
