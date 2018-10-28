package org.chengpx.framework.service;

import org.apache.commons.dbutils.ResultSetHandler;
import org.chengpx.util.dao.query.Page;
import org.chengpx.util.dao.query.QueryHelper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 业务逻辑层接口基类
 *
 * @author chengpx
 * @date 2018/6/19 21:12
 */
public interface BaseService<T, Q> {

    /**
     * 根据条件查询返回符合条件的所有数据集合
     *
     * @param queryHelper 查询助手
     * @return 符合条件的结果的 JavaBean 集合
     */
    List<T> list(QueryHelper<Q> queryHelper);

    /**
     * 分页获取实例对象, 封装在 Page 对象中
     *
     * @param queryHelper 查询助手
     * @return 分页获取的数据的 Page 对象
     */
    Page<T> page(QueryHelper<Q> queryHelper);

    /**
     * 计算与给定查询匹配的文档数。
     *
     * @param queryHelper 查询助手
     * @return 给定查询匹配的文档数。
     */
    int count(QueryHelper<Q> queryHelper);

    /**
     * 判断符合条件的记录是否存在
     *
     * @param queryHelper 查询助手
     * @return 存在返回 true, 否则返回 false
     */
    boolean exist(QueryHelper<Q> queryHelper);

    /**
     * 获取符合条件的唯一值
     *
     * @param queryHelper 查询助手
     * @param <R>         返回结果类型
     * @return 符合条件的唯一值
     */
    <R> R uniqueResult(QueryHelper<Q> queryHelper);

    /**
     * 删除索引中的所有文档。
     *
     * @return 序列号用于此操作
     */
    long deleteAll();

    /**
     * 删除与任何提供的查询匹配的文档。所有给定的删除都是同时应用和原子刷新的。
     *
     * @param fields  查询字词的默认字段数组
     * @param queries 查询数组以识别文档将被删除
     * @return 序列号用于此操作
     */
    long deleteDocuments(String[] fields, String... queries);

    //    /**
    //     * *删除包含任何文档的文档条款。 应用所有给定的删除并以原子方式刷新与此同时。
    //     *
    //     * @param terms 用于标识文档将被删除
    //     * @return 序列号用于此操作
    //     */
    //    long deleteDocuments(Term... terms);

    /**
     * 索引库添加数据
     *
     * @param t 索引数据 JavaBean
     * @return 序列号用于此操作
     */
    long addDocument(T t);

    //    /**
    //     * 索引库更新数据
    //     *
    //     * @param term 用于标识文档已删除
    //     * @param t    更新的索引数据
    //     * @return 序列号用于此操作
    //     */
    //    long updateDocument(Term term, T t);

    /**
     * 索引库添加数据
     *
     * @param tList 索引数据 JavaBean 集合
     * @return 序列号用于此操作集合
     */
    List<Long> addDocument(List<T> tList);

    /**
     * 存储实例
     *
     * @param t 实例
     * @return 数据库自动生成的 id
     */
    @Transactional(rollbackFor = Throwable.class)
    Serializable save(T t);

    /**
     * 存储实例
     *
     * @param t 实例
     * @return 成功与否
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean insert(T t);

    /**
     * 更新实例数据
     *
     * @param t 要更新的实例数据
     * @return 是否更新成功
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean update(T t);

    /**
     * 根据 id 删除数据库中的实例
     *
     * @param id 主键
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean delete(Serializable id);

    /**
     * 批量删除(In 语句)
     *
     * @param field  字段名称
     * @param values In 集合范围
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean deleteIn(String field, Object... values);

    /**
     * 批量删除(In 语句)
     *
     * @param field  字段名称
     * @param values In 集合范围
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean deleteIn(String field, Collection<Object> values);

    /**
     * 根据 id 获取数据库实例
     *
     * @param id 主键
     * @return 实例
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    T get(Serializable id);

    /**
     * 懒加载获取数据库实例
     *
     * @param id 主键
     * @return 懒加载实例
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    T load(Serializable id);

    /**
     * 数据库保存(不存在)或更新(已存在)实例对象
     *
     * @param t 要持久化的对象
     * @return 成功返回 true, 否则返回 false
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean saveOrUpdate(T t);

    /**
     * 获取表中全部实例对象
     *
     * @return 表全部实例对象
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    List<T> list();

    /**
     * 获取表中符合条件的全部实例对象
     *
     * @param sql    sql 语句
     * @param params sql 参数数组
     * @param rsh    结果集对象
     * @param <R>    返回结果类型
     * @return 符合条件的全部实例对象
     */
    @Transactional(rollbackFor = Throwable.class, readOnly = true)
    <R> List<R> list(String sql, ResultSetHandler<List<R>> rsh, Object... params);

    /**
     * 批处理保存
     *
     * @param tList 需要保存的实体数据集合
     * @return 保存成功返回 true, 否则返回 false
     */
    @Transactional(rollbackFor = Throwable.class)
    boolean batchSave(List<T> tList);

    //    /**
    //     * 获取表中符合条件的全部实例对象
    //     *
    //     * @param hbmQueryHelper hibernate 查询助手
    //     * @param <R>            返回结果类型
    //     * @return 符合条件的全部实例对象
    //     */
    //    <R> List<R> list(HbmQueryHelper hbmQueryHelper);

    //    /**
    //     * 离线获取表中符合条件的全部实例对象(默认结果转换类型为泛型类型)
    //     *
    //     * @param hbmQueryHelper hibernate 查询助手
    //     * @param <R>            返回结果类型
    //     * @return 符合条件的全部实例对象
    //     */
    //    <R> List<R> detachedList(HbmQueryHelper hbmQueryHelper);

    //    /**
    //     * 分页获取实例对象, 封装在 Page 对象中
    //     *
    //     * @param hbmQueryHelper hibernate 查询助手
    //     * @return 分页获取的数据的 Page 对象
    //     */
    //    Page<T> page(HbmQueryHelper hbmQueryHelper);

    //    /**
    //     * 离线分页获取实例对象, 封装在 Page 对象中(默认结果转换类型为泛型类型)
    //     *
    //     * @param hbmQueryHelper hibernate 查询助手
    //     * @return 分页获取的数据的 Page 对象
    //     */
    //    Page<T> detachedPage(HbmQueryHelper hbmQueryHelper);

    //    /**
    //     * 获取符合条件的记录个数
    //     *
    //     * @param hbmQueryHelper hibernate 查询助手
    //     * @return 符合条件的记录个数
    //     */
    //    int count(HbmQueryHelper hbmQueryHelper);

    //    /**
    //     * 离线获取符合条件的唯一值(默认结果转换类型为泛型类型)
    //     *
    //     * @param hbmQueryHelper hibernate 查询助手
    //     * @param <R>            返回结果类型
    //     * @return 符合条件的唯一值
    //     */
    //    <R> R detachedUniqueResult(HbmQueryHelper hbmQueryHelper);

}
