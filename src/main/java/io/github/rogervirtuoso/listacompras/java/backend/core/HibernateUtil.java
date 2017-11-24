package io.github.rogervirtuoso.listacompras.java.backend.core;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Roger
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
