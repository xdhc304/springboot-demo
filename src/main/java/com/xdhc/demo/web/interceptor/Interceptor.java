package com.xdhc.demo.web.interceptor;

import com.github.pagehelper.util.StringUtil;
import com.rabbitmq.http.client.domain.UserInfo;
import com.xdhc.demo.entity.Result;
import com.xdhc.demo.entity.User;
import com.xdhc.demo.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Interceptor implements HandlerInterceptor {

    private static final Logger logger  =  LoggerFactory.getLogger(Interceptor.class);

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object object) throws Exception {

        System.out.println("被Interceptor拦截");
        // return true;

        String token = request.getHeader("ACCESS_TOKEN");
        logger.info("token: " + token);
        if (StringUtil.isEmpty(token)) {
            throw new RuntimeException("未登录");
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("无权限请先登录");
//            request.setAttribute("msg","无权限请先登录");
//            request.getRequestDispatcher("/index.html").forward(request, response);
//            return false;
        } else {
            return true;
        }
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object object, ModelAndView mv)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
     * （主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object object, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}

