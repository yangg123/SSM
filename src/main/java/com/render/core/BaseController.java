package com.render.core;

import com.render.kit.PropKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yg on 17/2/16.
 */
public abstract class BaseController {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);
    protected static PropKit propKit = new PropKit("jdbc.properties");

    /**
     * 获取请求属性封装为Map类型
     * @param request
     * @return
     */
    protected HashMap<String, Object> getRequestMapSingle(HttpServletRequest request) {
        HashMap<String, Object> conditions = new HashMap<String, Object>();
        Map map = request.getParameterMap();
        for (Object o : map.keySet()) {
            String key = (String) o;
            conditions.put(key, ((String[]) map.get(key))[0]);
        }
        return conditions;
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){

        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    @ModelAttribute  // 等价于 Interceptor
    public void preRun() {
    }
}
