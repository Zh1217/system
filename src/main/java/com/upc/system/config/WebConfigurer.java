package com.upc.system.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    /* addInterceptors：拦截器
     * addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
     * addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
     * excludePathPatterns：用于设置不需要拦截的过滤规则
     * 拦截器主要用途：进行用户登录状态的拦截，日志的拦截等。
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/logout","/user/register", "/user/getCheckNumber", "/user/findPhone","/user/findPassword" );
        System.out.println("配置拦截器成功");
    }

    /**
     * 2、配置过滤器（全局跨域）
     * 设置header、允许跨域请求的提交方式method、过期时间等
     * 注意！前端ajax也要配置：发送AJAX请求前需设置通信对象XHR的withCredentials 属性为true，否则即使服务器同意发送Cookie，浏览器也无法获取。
     * 参考：https://www.jianshu.com/p/477e7eaa6c2f
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许访问的接口地址
                .addMapping("/**")
                //允许发起跨域访问的域名
                .allowedOriginPatterns("*")
                //允许跨域访问的方法
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
                //是否带上cookie信息
                .allowCredentials(true)

                .maxAge(3600)

                .allowedHeaders("*");
    }


}
