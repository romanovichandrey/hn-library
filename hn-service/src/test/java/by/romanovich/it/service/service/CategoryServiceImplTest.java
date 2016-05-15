package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Category;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Testing class CategoryServiceImpl.
 * @author Romanovich Andrei
 * @version 1.0
 */
public class CategoryServiceImplTest extends Assert {

    private static CategoryService categoryService= null;

    private static Category category1= null;

    private static Category category2= null;

    private static Category category3= null;

    private static HibernateUtil hibernateUtil = null;

    private static Session session = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initAutorDao() {
        hibernateUtil = HibernateUtil.getUtil();
        session = hibernateUtil.getSession();
        String name_num1 = "Программирование";
        String name_num2 = "Фантрмтика";
        String name_num3 = "РОман";
        category1 = new Category(name_num1);
        category2 = new Category(name_num2);
        category3 = new Category(name_num3);
    }

    /**
     * Clear custom parameter.
     */
    @AfterClass
    public static void clearHibernateUtil() {
        hibernateUtil.sessionClose();
        hibernateUtil = null;
        categoryService = null;
        category1 = null;
        category2 = null;
        category3 = null;
    }

    /**
     * Testing categorySercice.saveCategory.
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    @Test
    public void testSaveCategory() throws ServiceExeption {
        Boolean categorySaveResult1 = false;
        Boolean categorySaveResult2 = false;
        Boolean categorySaveResult3 = false;
        categoryService = CategoryServiceImpl.getCategoryService();
        try {
            session.beginTransaction();
            categorySaveResult1 = categoryService.saveCategory(category1);
            categorySaveResult2 = categoryService.saveCategory(category2);
            categorySaveResult3 = categoryService.saveCategory(category3);
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertTrue(categorySaveResult1);
        assertTrue(categorySaveResult2);
        assertTrue(categorySaveResult3);
    }

    /**
     * Testing categoryService.getService()
     */
    @Test
    public void testGetBookService() {
        categoryService = CategoryServiceImpl.getCategoryService();
        assertNotNull(categoryService);
        categoryService = null;
        assertNull(categoryService);

    }

    /**
     * Testing categoryService.getAllCategories()
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    @Test
    public void testGetAllCategories() throws ServiceExeption {
        List<Category> categories = null;
        categoryService = CategoryServiceImpl.getCategoryService();
        try {
            session.beginTransaction();
            categories = categoryService.getAllCategories();
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(categories);
        List<Category> categoryTest = Arrays.asList(category1, category2, category3);
        //assertEquals(categoryTest.size(), categories.size());
    }

    /**
     * Testing categoryService.updateCategory()
     * @throws ServiceExeption
     */
    @Test
    public void testUpdateCategory() throws ServiceExeption {
        Boolean categoryUpdateResult = false;
        category1.setName("New name");
        categoryService = CategoryServiceImpl.getCategoryService();
        try {
            session.beginTransaction();
            categoryUpdateResult = categoryService.updateCategory(category3);
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertTrue(categoryUpdateResult);

    }

    /**
     * Testing categoryService.getBookById()
     * @throws ServiceExeption
     */
    @Test
    public void testGetUserById() throws ServiceExeption {
        Category categoryIdResult = category1;
        categoryService = CategoryServiceImpl.getCategoryService();
        try {
            session.beginTransaction();
           categoryIdResult = categoryService.getCategoryById(category1.getId());
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().commit();
        }
        assertNotNull(categoryIdResult);
        assertEquals(category1, categoryIdResult);
    }

    /**
     * Testing catrgoryService.deleteCategory();
     * @throws ServiceExeption
     */
    @Test
    public void testDeleteUser() throws ServiceExeption {
        Category categoryResult = category3;
        Boolean categoryDeleteResult = false;
        categoryService = CategoryServiceImpl.getCategoryService();
        try {
            session.beginTransaction();
            categoryDeleteResult = categoryService.deleteCategory(category3);
            categoryResult = categoryService.getCategoryById(category3.getId());
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertTrue(categoryDeleteResult);
        //assertNull(category3);
    }
}