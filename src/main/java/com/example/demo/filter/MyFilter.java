package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest s_request, ServletResponse s_response, FilterChain filterChain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) s_request;
        System.out.println("this is MyFilter,url :"+request.getRequestURI());
        filterChain.doFilter(s_request, s_response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}