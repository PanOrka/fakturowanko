package com.fakturowanko.db;

import java.util.Properties;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;

import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    private static String user;
    private static String password;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {

            try {

                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties

                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");

                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/fakturowanie");

                settings.put(Environment.USER, HibernateUtil.user);

                settings.put(Environment.PASS, HibernateUtil.password);

                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(FakturyEntity.class);
                configuration.addAnnotatedClass(IloscProduktuEntity.class);
                configuration.addAnnotatedClass(KlientEntity.class);
                configuration.addAnnotatedClass(ProduktEntity.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()

                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        return sessionFactory;

    }

    public static void setUser(String user) {
        HibernateUtil.user = user;
    }

    public static void setPassword(String password) {
        HibernateUtil.password = password;
    }

}