package com.example.lab9;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class F1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("F1:init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("value1").equals("1")) {
            System.out.println("F1:doFilter");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            PrintWriter out = servletResponse.getWriter();
            out.println("F1, value1!=1");
        }
    }

    @Override
    public void destroy() {
        System.out.println("F1:destroy");
    }
}
