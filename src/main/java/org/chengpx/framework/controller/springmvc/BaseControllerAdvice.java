package org.chengpx.framework.controller.springmvc;

/**
 * 控制器基类建议
 *
 * @author chengpx
 * @date 2018/8/14 17:31
 */
//@ControllerAdvice(assignableTypes = {BaseController.class})
public class BaseControllerAdvice {

    //    protected final Logger LOGGER = Logger.getLogger(getClass());
    //
    //    @InitBinder
    //    public void initBinder(DataBinder dataBinder, HttpServletRequest request, HttpServletResponse response) {
    //        try {
    //            LOGGER.debug("initBinder");
    //            // 处理 post 请求编码
    //            request.setCharacterEncoding("UTF-8");
    //            // 处理响应编码
    //            response.setContentType("text/html;charset=utf-8");
    //            dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    //            dataBinder.registerCustomEditor(java.sql.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    //            dataBinder.registerCustomEditor(Timestamp.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            LOGGER.error(e.getMessage());
    //            throw new RuntimeException(e);
    //        }
    //    }

}
