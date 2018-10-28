package org.chengpx.framework.controller;

import org.chengpx.framework.BaseSysException;

/**
 * 控制层异常
 *
 * @author chengpx
 * @date 2018/6/27 14:25
 */
public final class ControllerException extends BaseSysException {

    public ControllerException() {
        super("[Controller 异常] ");
    }

    public ControllerException(String message) {
        super("[Controller 异常] " + message);
    }

    public ControllerException(String message, Throwable cause) {
        super("[Controller 异常] " + message, cause);
    }

    public ControllerException(Throwable cause) {
        super(cause);
    }

    public ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("[Controller 异常] " + message, cause, enableSuppression, writableStackTrace);
    }

}
