package com.example.lab9;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class L1 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context was created");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context was destroyed");
    }
}
