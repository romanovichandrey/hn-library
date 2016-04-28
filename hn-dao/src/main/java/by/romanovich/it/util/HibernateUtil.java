package by.romanovich.it.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    private SessionFactory sessionFactory = null;

    /** Oblect class ThreadLocal*/
    private final ThreadLocal<Session> sessions = new ThreadLocal();

    /** Constructor class to initialize SessionFactory. */
    private HibernateUtil() {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
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
        Session session = (Session)sessions.get();
        if(session == null) {
            session = sessionFactory.openSession();
            sessions.set(session);
        }
        return session;
    }

    /**
     * Method for obtain object HibernateUtil implements pattern Singleton
     * @return HibernateUtil
     */
    public static HibernateUtil getUtil() {
        if(util == null) {
            util = new HibernateUtil();
        }
        return util;
    }
}
