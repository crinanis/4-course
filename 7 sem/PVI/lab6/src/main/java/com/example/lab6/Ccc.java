package com.example.lab6;

import com.example.lab6.beans.CBean;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Ccc")
public class Ccc extends HttpServlet {
    public CBean cBean = null;

    @Override
    public void init() throws ServletException {
        cBean = new CBean();

        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("atrCBean", cBean);
        System.out.println("init " + servletContext.getAttribute("atrCBean"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("goGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cBeanParam = req.getParameter("CBean");
        if (cBeanParam != null && cBeanParam.equals("new")) {
            ServletContext servletContext = getServletContext();
            CBean oldCBean = (CBean) servletContext.getAttribute("atrCBean");
            cBean = new CBean();
            servletContext.setAttribute("atrCBean", cBean);
            System.out.println("ccc: new: " + servletContext.getAttribute("atrCBean"));

            String param1 = req.getParameter("value1");
            String param2 = req.getParameter("value2");
            String param3 = req.getParameter("value3");

            if (!param1.isEmpty() && !param2.isEmpty() && !param3.isEmpty()) {
                cBean.setValue1(param1);
                cBean.setValue2(param2);
                cBean.setValue3(param3);
            } else {
                cBean.setValue1(oldCBean.getValue1());
                cBean.setValue2(oldCBean.getValue2());
                cBean.setValue3(oldCBean.getValue3());
            }
            req.getRequestDispatcher("/Ccc.jsp").forward(req, resp);
        } else if (cBeanParam == null || cBeanParam.equals("old")) {
            ServletContext servletContext = getServletContext();
            System.out.println("ccc: old: " + servletContext.getAttribute("atrCBean"));
            req.getRequestDispatcher("/Ccc.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
