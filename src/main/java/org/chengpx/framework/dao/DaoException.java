package org.chengpx.framework.dao;

import org.chengpx.framework.BaseSysException;

/**
 * 数据访问层异常
 *
 * @author chengpx
 * @date 2018/6/27 14:24
 */
public final class DaoException extends BaseSysException {

    public DaoException() {
        super("[Dao 异常] ");
    }

    public DaoException(String message) {
        super("[Dao 异常] " + message);
    }

    public DaoException(String message, Throwable cause) {
        super("[Dao 异常] " + message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("[Dao 异常] " + message, cause, enableSuppression, writableStackTrace);
    }

}
