package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Autor;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Testing class AutorServiceImpl.
 * @author Romanovich Andrei
 * @version 1.0
 */
public class AutorServiceImplTest extends Assert {

    private static AutorService autorService = null;

    private static Autor autor1 = null;

    private static Autor autor2 = null;

    private static Autor autor3 = null;

    private static HibernateUtil hibernateUtil = null;

    private static Session session = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initAutorDao() {
        hibernateUtil = HibernateUtil.getUtil();
        session = hibernateUtil.getSession();
        String firstname_num1 = "Брюс";
        String lastname_num1 = "Эккель";

        String firstname_num2 = "Александр";
        String lastname_num2 = "Пушкин";

        String firstname_num3 = "Джеймс";
        String lastname_num3 = "Гаскин";

        autor1 = new Autor(firstname_num1, lastname_num1);
        autor2 = new Autor(firstname_num2, lastname_num2);
        autor3 = new Autor(firstname_num3, lastname_num3);
    }

    /**
     * Clear custom parameter.
     */
    @AfterClass
    public static void clearHibernateUtil() {
        hibernateUtil.sessionClose();
        hibernateUtil = null;
        autor1 = null;
        autor2 = null;
        autor3 = null;
    }

    /**
     * Testing autorService.saveAutor() and autorService.getAutorById()
     * and autorService.deleteAutor()
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    @Test
    public void testSaveAutor() throws ServiceExeption {
        Boolean autorSaveResult1 = false;
        Boolean autorSaveResult2 = false;
        Boolean autorSaveResult3 = false;
        autorService = AutorServiceImpl.getAutorService();
        Autor autorIdResult = autor1;
        Autor autorResult = autor3;
        Boolean autorDeleteResult = false;
        try {
            session.beginTransaction();
            autorSaveResult1 = autorService.saveAutor(autor1);
            autorSaveResult2 = autorService.saveAutor(autor2);
            autorSaveResult3 = autorService.saveAutor(autor3);
            autorIdResult = autorService.getAutorById(autor1.getId());
            autorDeleteResult = autorService.deleteAutor(autor1);
            autorResult= autorService.getAutorById(autor1.getId());
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertTrue(autorSaveResult1);
        assertTrue(autorSaveResult2);
        assertTrue(autorSaveResult3);
        assertNotNull(autorIdResult);
        assertEquals(autor1, autorIdResult);
        assertTrue(autorDeleteResult);
        assertNull(autorResult);
    }

    /**
     * Testing autorService.getAutorService()
     */
    @Test
    public void testGetAutorService() {
        autorService = AutorServiceImpl.getAutorService();
        assertNotNull(autorService);
        autorService = null;
        assertNull(autorService);

    }

    /**
     * Testing autorService.getAllAutor()
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    @Test
    public void testGetAllAutor() throws ServiceExeption {
        List<Autor> autors = null;
        autorService = AutorServiceImpl.getAutorService();
        try {
            session.beginTransaction();
            autors = autorService.getAllAutors();
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(autors);
    }

    /**
     * Testing autorService.updateAutor()
     * @throws ServiceExeption
     */
    @Test
    public void testUpdateAutor() throws ServiceExeption {
        Boolean autorUpdateResult = false;
        autor1.setFirstname("Andrei");
        autorService = AutorServiceImpl.getAutorService();
        try {
            session.beginTransaction();
             autorUpdateResult = autorService.updateAutor(autor1);
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertTrue(autorUpdateResult);

    }

}