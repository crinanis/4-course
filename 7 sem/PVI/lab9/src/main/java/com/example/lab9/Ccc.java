package com.example.lab9;

import com.example.lab9.beans.CBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Ccc extends HttpServlet {
    CBean cBean = null;

    @Override
    public void init() {
        cBean = new CBean();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("goGet");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("doPost");
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.setMaxInactiveInterval(7);
        CBean oldCBean = (CBean) httpSession.getAttribute(httpSession.getId());
        String cBeanParam = req.getParameter("CBean");
        if (cBeanParam != null && cBeanParam.equals("new")) {
            cBean = new CBean();
            //System.out.println("ссс: new: " + req.getAttribute(httpSession.getId()));

            String param1 = req.getParameter("value1");
            String param2 = req.getParameter("value2");
            String param3 = req.getParameter("value3");

            if (!param1.isEmpty() && !param2.isEmpty() && !param3.isEmpty()) {
                cBean.setValue1(param1);
                cBean.setValue2(param2);
                cBean.setValue3(param3);
            }
            httpSession.setAttribute(httpSession.getId(), cBean);
            req.getRequestDispatcher("/Ccc.jsp").forward(req, resp);
        } else if (req.getParameter("CBean").equals("old")) {
            httpSession.setAttribute(httpSession.getId(), oldCBean);
            //System.out.println(httpSession.getId().toString());
            //System.out.println("old: " + httpSession.getAttribute(httpSession.getId()));
            req.getRequestDispatcher("/Ccc.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
