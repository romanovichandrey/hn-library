package by.romanovich.it.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * This is class helper for Hibernate.
 * @author Romanovich Andrey.
 * @version 1.0
 */
public class HibernateUtil {

    /** Event logging this class. */
    private static final Logger log = Logger.getLogger(HibernateUtil.class);

    /** Properties class implements pattern Singleton. */
    private static HibernateUtil util = null;

    /** Object class to create a cache of compiled mapping. */
    private static SessionFactory sessionFactory = null;

    /** Oblect class ThreadLocal*/
    private final ThreadLocal<Session> sessions = new ThreadLocal();

    /** Constructor class to initialize SessionFactory. */
    private HibernateUtil() {
        try{
            Configuration    configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.setNamingStrategy(new CustomNamingStrategy())
                    .buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            log.error("Initial SessionFactory creation faild. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Method for obtaining object Session
     * @return Session
     */
    public Session getSession() {
        Session session = sessions.get();
        if(session == null) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    /**
     * Disconnect the session from its underlying JDBC connection
     */
    public void sessionClose() {
        Session session = sessions.get();
        if(session != null) {
            session.close();
            sessions.set(null);
        }
    }

    /**
     * Method for sessionFactory.close() and null for HibernateUtil
     */
    public static void destroy() {
        if(util != null) {
            sessionFactory.close();
            util = null;
        }
    }

    /**
     * Method for obtain object HibernateUtil implements pattern Singleton
     * @return HibernateUtil
     */
    public synchronized static HibernateUtil getUtil() {
        if(util == null) {
            util = new HibernateUtil();
        }
        return util;
    }
}
