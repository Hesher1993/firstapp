package ru.geekbrains.hesher.servlet;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GlobalSessionFactory {

    private static SessionFactory sessionFactory;

    public GlobalSessionFactory(String config){
        sessionFactory = new Configuration()
                .configure(config)
                .buildSessionFactory();
    }

    public static SessionFactory get() {
        return sessionFactory;
    }

    public static void close(){
        sessionFactory.close();
    }
}
