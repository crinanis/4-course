package com.example.lab9;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class F3 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("F3:init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("value3").equals("3")) {
            System.out.println("F3:doFilter");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            PrintWriter out = servletResponse.getWriter();
            out.println("F3, value3!=3");
        }
    }

    @Override
    public void destroy() {
        System.out.println("F3:destroy");
    }
}
