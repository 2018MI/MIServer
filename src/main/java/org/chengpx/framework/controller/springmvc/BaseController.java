package org.chengpx.framework.controller.springmvc;

import org.chengpx.framework.service.BaseService;
import org.chengpx.util.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * springmvc 控制器基类
 *
 * @author chengpx
 * @date 2018/8/14 17:22
 */
public abstract class BaseController<T, Q> {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    protected final Class<T> DOMAIN_CLASS;
    protected BaseService<T, Q> baseService;

    public BaseController() {
        DOMAIN_CLASS = ReflectUtils.getActualTypeArg(getClass());
    }

    /**
     * 转发到对应的视图
     *
     * @param viewName 视图名称(不带 UI)
     * @return UI 视图资源路径
     */
    @RequestMapping(value = "/{viewName}UI.do")
    public String toUI(@PathVariable String viewName) {
        Class<?> clazz = getClass();
        //判断是否存在requestMapping注释
        boolean annotationPresent = clazz.isAnnotationPresent(RequestMapping.class);
        if (!annotationPresent) {
            LOGGER.error("必须要有 @RequestMapping");
            throw new IllegalStateException();
        }
        //得到requestMapping注释
        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
        //得到value数组
        String[] value = requestMapping.value();
        String module = value[0];
        return "/WEB-INF/jsp" + module + "/" + viewName + "UI.jsp";
    }

    @InitBinder
    public void initBinder(DataBinder dataBinder, HttpServletRequest request, HttpServletResponse response) {
        try {
            LOGGER.debug("initBinder");
            // 处理 post 请求编码
            request.setCharacterEncoding("UTF-8");
            // 处理响应编码
            response.setContentType("text/html;charset=utf-8");
            dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
            dataBinder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
            dataBinder.registerCustomEditor(Timestamp.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取服务层接口实例
     *
     * @return 业务逻辑层接口对象
     */
    public abstract BaseService<T, Q> getBaseService();

    public void setBaseService(BaseService<T, Q> baseService) {
        this.baseService = baseService;
    }

}
