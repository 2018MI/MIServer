package org.chengpx.framework;

/**
 * 项目系统异常
 *
 * @author chengpx
 * @date 2018/6/27 14:24
 */
public abstract class BaseSysException extends Exception {

    public BaseSysException() {
        super("[项目系统异常] ");
    }

    public BaseSysException(String message) {
        super("[项目系统异常] " + message);
    }

    public BaseSysException(String message, Throwable cause) {
        super("[项目系统异常] " + message, cause);
    }

    public BaseSysException(Throwable cause) {
        super(cause);
    }

    public BaseSysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("[项目系统异常] " + message, cause, enableSuppression, writableStackTrace);
    }

}
