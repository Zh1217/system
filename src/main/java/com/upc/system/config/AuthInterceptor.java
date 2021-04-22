package com.upc.system.config;

import com.upc.system.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        log.info("现在的请求路径是{}",uri);


        // 登录，注册，找回密码，获取验证码，默认不拦截
        if(uri.endsWith("/user/login") ||
                uri.endsWith("/user/logout") ||
                uri.endsWith("/user/register") ||
                uri.endsWith("/user/findPhone") ||
                uri.endsWith("/user/findPassword")){
            return true;
        }

        //如果是option请求，直接执行？
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        //其他请求，获取session中的user，自动刷新Session的有效期，需要设置拦截
        HttpSession session = request.getSession();
        if (session.getId() == null){
            log.warn("####\n" + "拦截器中，session内容为空，" + "收到session的id：" + session.getId());
            throw new GlobalException(CodeMsg.SESSION_ERROR);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }


}
