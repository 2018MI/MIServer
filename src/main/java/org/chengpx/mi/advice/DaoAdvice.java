package org.chengpx.mi.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.chengpx.framework.dao.impl.BaseDaoJdbcImpl;
import org.chengpx.util.dao.JdbcUtils;
import org.chengpx.util.dao.TraQueryRunner;
import org.springframework.stereotype.Component;

/**
 * @author chengpx
 * @date 2018/10/28 14:15
 */
@Aspect
@Component
public class DaoAdvice {

    /**
     * 每个 dao 的方法执行时, 重新获取一次 JdbcUtils, TraQueryRunner
     *
     * @param pjp
     */
    @Before("execution(* org.chengpx.mi.dao.*.*.*(..))")
    public void aa(JoinPoint pjp) {
        // 在执行目标方法之前执行
        Object target = pjp.getTarget();
        if (!(target instanceof BaseDaoJdbcImpl)) {
            return;
        }
        BaseDaoJdbcImpl baseDaoJdbc = (BaseDaoJdbcImpl) target;
        baseDaoJdbc.setmJdbcUtils(JdbcUtils.getThreadInstance());
        baseDaoJdbc.setmQueryRunner(TraQueryRunner.getThreadInstance());
    }

}
