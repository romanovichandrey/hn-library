package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Category;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.util.HibernateUtil;
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

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initAutorDao() {
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
        HibernateUtil.getUtil().sessionClose();
        categoryService = null;
        category1 = null;
        category2 = null;
        category3 = null;
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
     * Testing category serviceCategory.deleteCategory() and serviceCategory.getUserById() and
     * serviceCategory.saveCategory() and categoryService.getAllCategories().
     * @throws ServiceExeption
     */
    @Test
    public void testSaveGetAndDeleteUser() throws ServiceExeption {
        Category categoryResult = category2;
        Boolean categoryDeleteResult = false;
        Category categoryIdResult = category1;
        Boolean categorySaveResult1 = false;
        Boolean categorySaveResult2 = false;
        Boolean categorySaveResult3 = false;
        Boolean categoryUpdateResult = false;
        List<Category> categories = null;
        categoryService = CategoryServiceImpl.getCategoryService();
        category1.setName("New name");
        categoryService = CategoryServiceImpl.getCategoryService();
        try {
            categorySaveResult1 = categoryService.saveCategory(category1);
            categorySaveResult2 = categoryService.saveCategory(category2);
            categorySaveResult3 = categoryService.saveCategory(category3);
            categoryIdResult = categoryService.getCategoryById(category1.getId());
            categoryUpdateResult = categoryService.updateCategory(category3);
            categories = categoryService.getAllCategories();
            categoryResult = categoryService.getCategoryById(category2.getId());
            categoryDeleteResult = categoryService.deleteCategory(category3);
            category3 = categoryService.getCategoryById(category3.getId());
        } catch (ServiceExeption e) {
            e.printStackTrace();
        }
        assertTrue(categorySaveResult1);
        assertTrue(categorySaveResult2);
        assertTrue(categorySaveResult3);
        assertNotNull(categoryIdResult);
        assertEquals(category1, categoryIdResult);
        assertTrue(categoryUpdateResult);
        assertNotNull(categories);
        assertNotNull(categoryResult);
        assertEquals(categoryResult, category2);
        List<Category> categoryTest = Arrays.asList(category1, category2, category3);
        assertEquals(categoryTest.size(), categories.size());
        assertTrue(categoryDeleteResult);
        assertNull(category3);
    }
}