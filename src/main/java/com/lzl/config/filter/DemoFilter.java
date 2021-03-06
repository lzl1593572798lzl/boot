package com.lzl.config.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * create by lzl ON 2018/08/06
 * @author lzl
 */
@Order(1)
@WebFilter(filterName = "demoFilter",urlPatterns = "/admin/*")
public class DemoFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DemoFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("RequestURI -> "+((HttpServletRequest)servletRequest).getRequestURI());
    }

    @Override
    public void destroy() {
        System.out.println("DemoFilter 销毁");
    }
}
