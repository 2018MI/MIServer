package org.chengpx.framework.service;

import org.apache.commons.dbutils.ResultSetHandler;
import org.chengpx.framework.dao.BaseDao;
import org.chengpx.util.ReflectUtils;
import org.chengpx.util.dao.query.Page;
import org.chengpx.util.dao.query.QueryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 业务逻辑层实现基类
 *
 * @author chengpx
 * @date create at 2018/6/8 11:29
 */
public abstract class BaseServiceImpl<T, Q> implements BaseService<T, Q> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    protected final Class<T> DOMAIN_CLAZZ;
    protected BaseDao<T, Q> baseDao;

    public BaseServiceImpl() {
        DOMAIN_CLAZZ = ReflectUtils.getActualTypeArg(getClass());
    }

    @Override
    public List<T> list(QueryHelper<Q> queryHelper) {
        return baseDao.list(queryHelper);
    }

    @Override
    public Page<T> page(QueryHelper<Q> queryHelper) {
        return baseDao.page(queryHelper);
    }

    @Override
    public int count(QueryHelper<Q> queryHelper) {
        return baseDao.count(queryHelper);
    }

    @Override
    public boolean exist(QueryHelper<Q> queryHelper) {
        return baseDao.exist(queryHelper);
    }

    @Override
    public <R> R uniqueResult(QueryHelper<Q> queryHelper) {
        return baseDao.uniqueResult(queryHelper);
    }

    @Override
    public long deleteAll() {
        return baseDao.deleteAll();
    }

    @Override
    public long deleteDocuments(String[] fields, String... queries) {
        return baseDao.deleteDocuments(fields, queries);
    }

    //    @Override
    //    public long deleteDocuments(Term... terms) {
    //        return baseDao.deleteDocuments(terms);
    //    }

    @Override
    public long addDocument(T t) {
        return baseDao.addDocument(t);
    }

    //    @Override
    //    public long updateDocument(Term term, T t) {
    //        return baseDao.updateDocument(term, t);
    //    }

    @Override
    public List<Long> addDocument(List<T> tList) {
        return baseDao.addDocument(tList);
    }

    @Override
    public Serializable save(T t) {
        return baseDao.save(t);
    }

    @Override
    public boolean insert(T t) {
        return baseDao.insert(t) > 0;
    }

    @Override
    public boolean update(T t) {
        return baseDao.update(t);
    }

    @Override
    public boolean delete(Serializable id) {
        return baseDao.delete(id);
    }

    @Override
    public boolean deleteIn(String field, Object... values) {
        return baseDao.deleteIn(field, values);
    }

    @Override
    public boolean deleteIn(String field, Collection<Object> values) {
        return baseDao.deleteIn(field, values);
    }

    @Override
    public T get(Serializable id) {
        return baseDao.get(id);
    }

    @Override
    public T load(Serializable id) {
        return baseDao.load(id);
    }

    @Override
    public boolean saveOrUpdate(T t) {
        return baseDao.saveOrUpdate(t);
    }

    @Override
    public List<T> list() {
        return baseDao.list();
    }

    @Override
    public <R> List<R> list(String sql, ResultSetHandler<List<R>> rsh, Object... params) {
        return baseDao.list(sql, rsh, params);
    }

    @Override
    public boolean batchSave(List<T> tList) {
        return baseDao.batchSave(tList) > 0;
    }

    /**
     * 获取数据访问层接口实例
     *
     * @return 数据访问层接口实例
     */
    public abstract BaseDao<T, Q> getBaseDao();

    public void setBaseDao(BaseDao<T, Q> baseDao) {
        this.baseDao = baseDao;
    }

}
