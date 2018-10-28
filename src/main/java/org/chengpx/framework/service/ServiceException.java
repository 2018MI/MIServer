package org.chengpx.framework.service;

import org.chengpx.framework.BaseSysException;

/**
 * 业务逻辑层异常
 *
 * @author chengpx
 * @date 2018/6/19 21:44
 */
public final class ServiceException extends BaseSysException {

    public ServiceException() {
        super("[Service 异常] ");
    }

    public ServiceException(String message) {
        super("[Service 异常] " + message);
    }

    public ServiceException(String message, Throwable cause) {
        super("[Service 异常] " + message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("[Service 异常] " + message, cause, enableSuppression, writableStackTrace);
    }

}
