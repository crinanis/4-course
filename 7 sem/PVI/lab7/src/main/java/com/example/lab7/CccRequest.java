package com.example.lab7;

import com.example.lab7.beans.CBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/CccRequest")
public class CccRequest extends HttpServlet {
    CBean cBean = null;

    @Override
    public void init() throws ServletException {
        cBean = new CBean();
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
        CBean oldCBean = (CBean) req.getAttribute("atrCBean");
        System.out.println("ccc: init: " + oldCBean);
        if (cBeanParam != null && cBeanParam.equals("new")) {
            cBean = new CBean();
            System.out.println("ccc: new: " + req.getAttribute("atrCBean"));

            String param1 = req.getParameter("value1");
            String param2 = req.getParameter("value2");
            String param3 = req.getParameter("value3");

            if (!param1.isEmpty() && !param2.isEmpty() && !param3.isEmpty()) {
                cBean.setValue1(param1);
                cBean.setValue2(param2);
                cBean.setValue3(param3);
            }
            req.setAttribute("atrCBean", cBean);
            req.getRequestDispatcher("/CccRequest.jsp").forward(req, resp);
        } else if (cBeanParam == null || cBeanParam.equals("old")) {
            req.setAttribute("atrCBean", oldCBean);
            System.out.println("ccc: old: " + req.getAttribute("atrCBean"));
            req.getRequestDispatcher("/CccRequest.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
