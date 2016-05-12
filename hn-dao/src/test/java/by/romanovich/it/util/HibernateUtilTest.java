package by.romanovich.it.util;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testing class HibernateUtil. Create and testing sessionFactory
 * @see by.romanovich.it.util.HibernateUtil
 * @author Romanovich Andrei
 * @version 1.0
 */
public class HibernateUtilTest extends Assert {

    private static HibernateUtil hibernateUtil = null;

    private Session session = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initHibernateUtil() {
        hibernateUtil = HibernateUtil.getUtil();
    }

    /**
     * Clear custom parameter.
     */
    @AfterClass
    public static void clearHibernateUtil() {
        hibernateUtil = null;
    }

    /**
     * Testing hibernateUtil.getSession().
     * @throws Exception
     */
    @Test
    public void testGetSession() throws Exception {
        session = hibernateUtil.getSession();
        assertNotNull("Session not null", session);
    }

    /**
     * Testing hibernateUtil.sessionClose().
     * @throws Exception
     */
    @Test
    public void testSessionClose() throws Exception {
        hibernateUtil.sessionClose();
        assertNull("Session is null", session);
    }

    /**
     * Testing HibernateUtil.getUtil().
     * @throws Exception
     */
    @Test
    public void testGetUtil() throws Exception {
        hibernateUtil = null;
        assertNull("HibernateUtil is null", hibernateUtil);
        hibernateUtil = HibernateUtil.getUtil();
        assertNotNull("HibernateUtil not null", hibernateUtil);
    }
}