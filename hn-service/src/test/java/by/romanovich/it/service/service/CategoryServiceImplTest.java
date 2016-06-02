package by.romanovich.it.service.service;

import by.romanovich.it.dao.exceptions.DaoException;
import by.romanovich.it.dao.interfaces.Dao;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.service.exceptions.ServiceException;
import by.romanovich.it.service.service.interfases.CategoryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testing class CategoryServiceImpl.
 * @author Romanovich Andrei
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest extends Assert {

    @Mock
    private Dao<Category, Long> categoryDao;

    @InjectMocks
    private CategoryService categoryService = new CategoryServiceImpl();

    private Category category1;

    private Category category2;

    private Category category3;

    private List<Category> categoryList;

    /**
     * Initialize custom parameter for tests.
     */
    @Before
    public void initAutorDao() {
        String name_num1 = "Программирование";
        String name_num2 = "Фантрмтика";
        String name_num3 = "РОман";
        category1 = new Category(name_num1);
        category2 = new Category(name_num2);
        category3 = new Category(name_num3);

        categoryList = Arrays.asList(category1, category2, category3);
    }

    /**
     * Testing categoryService.updateCategory()
     */
    @Test
    public void testUpdateCategory() {
        category1.setName(category2.getName());
        try {
            categoryService.updateCategory(category1);
            verify(categoryDao).update(category1);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    /**
     * Testing categoryService.getCategoryById()
     */
    @Test
    public void testGetCategoryById() {
        try {
            when(categoryDao.get(category1.getId())).thenReturn(category1);

            assertEquals(category1, categoryService.getCategoryById(category1.getId()));
        }catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing categoryService.getAllCategories()
     */
    @Test
    public void testGetAllCategories() {
        try{
            when(categoryDao.getAll()).thenReturn(categoryList);

            assertNotNull(categoryService.getAllCategories());
            assertEquals(categoryList, categoryService.getAllCategories());
        }catch (ServiceException e){
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    /**
     * Testing categoryService.deleteCategory
     */
    @Test
    public void testDeleteCategory() {
        try {
            doNothing().when(categoryDao).delete(category2);

            assertTrue(categoryService.deleteCategory(category2));
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    /**
     * Testing categoryService.saveCategory()
     */
    @Test
    public void testSaveCategory() throws Exception {
        try {
            categoryService.saveCategory(category3);

            verify(categoryDao).add(category3);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}