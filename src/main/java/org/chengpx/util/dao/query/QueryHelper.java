package org.chengpx.util.dao.query;

/**
 * 查询助手
 *
 * @author chengpx
 * @date 2018/8/29 8:35
 */
public interface QueryHelper<T> {

    /**
     * Hibernate 类型
     */
    interface Hibernate {
    }

    /**
     * Lucene 类型
     */
    interface Lucene {
    }

    /**
     * Mybatis 类型
     */
    interface Mybatis {
    }

}
