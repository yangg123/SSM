package com.render.exception;


import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yg on 17/2/20.
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        // 日志记录异常message和stacktrace
        logger.error("456" + ex.getMessage(),ex);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("exception", ex.getMessage());
        String header = request.getHeader("X-Requested-With");
        // 处理异步请求
        if (StringUtils.isNotBlank(header) && (header.equals("X-Requested-With") || header.equals("XMLHttpRequest"))) {
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = null;
            try {
                pw = response.getWriter();
                pw.write(JSON.toJSONString(map));
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
                // 如果不是人为抛出的异常，就发送邮件到我的QQ邮箱
                if (ex.getClass() != java.lang.RuntimeException.class) {
                    ErrorKit.sendErrorEmail(e);
                }
            } finally {
                if (pw != null)
                    pw.close();
            }
            return null;
        } else {
            if (ex.getClass() != java.lang.RuntimeException.class) {
                ErrorKit.sendErrorEmail(ex);
            }
        }

        return new ModelAndView("error_404", map);
    }
}
