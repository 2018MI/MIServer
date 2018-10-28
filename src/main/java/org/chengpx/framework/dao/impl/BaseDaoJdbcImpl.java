package org.chengpx.framework.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.chengpx.framework.dao.BaseDao;
import org.chengpx.util.dao.JdbcUtils;
import org.chengpx.util.dao.TraQueryRunner;
import org.chengpx.util.dao.query.Page;
import org.chengpx.util.dao.query.QueryHelper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author chengpx
 * @date 2018/10/27 19:02
 */
public abstract class BaseDaoJdbcImpl implements BaseDao {

    protected JdbcUtils mJdbcUtils;
    protected QueryRunner mQueryRunner;

    public BaseDaoJdbcImpl() {
        mJdbcUtils = JdbcUtils.getThreadInstance();
        mQueryRunner = TraQueryRunner.getThreadInstance();
    }


    @Override
    public void flush() {

    }

    @Override
    public void clear() {

    }

    @Override
    public List list(QueryHelper queryHelper) {
        return null;
    }

    @Override
    public Page page(QueryHelper queryHelper) {
        return null;
    }

    @Override
    public int count(QueryHelper queryHelper) {
        return 0;
    }

    @Override
    public boolean exist(QueryHelper queryHelper) {
        return false;
    }

    @Override
    public Object uniqueResult(QueryHelper queryHelper) {
        return null;
    }

    @Override
    public long deleteAll() {
        return 0;
    }

    @Override
    public long deleteDocuments(String[] fields, String... queries) {
        return 0;
    }

    @Override
    public long addDocument(Object o) {
        return 0;
    }

    @Override
    public List<Long> addDocument(List list) {
        return null;
    }

    @Override
    public Serializable save(Object o) {
        return null;
    }

    @Override
    public long insert(Object o) {
        return 0;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public boolean delete(Serializable id) {
        return false;
    }

    @Override
    public boolean deleteIn(String field, Object... values) {
        return false;
    }

    @Override
    public boolean deleteIn(String field, Collection values) {
        return false;
    }

    @Override
    public Object get(Serializable id) {
        return null;
    }

    @Override
    public Object load(Serializable id) {
        return null;
    }

    @Override
    public boolean saveOrUpdate(Object o) {
        return false;
    }

    @Override
    public List list() {
        return null;
    }

    @Override
    public List list(String sql, ResultSetHandler rsh, Object... params) {
        return null;
    }

    @Override
    public long batchSave(List list) {
        return 0;
    }
}
