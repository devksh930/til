package me.devksh930;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Context Initialzed");
        sce.getServletContext().setAttribute("name","sungho");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context Destroy");
    }
}
