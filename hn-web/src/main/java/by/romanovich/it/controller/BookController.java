package by.romanovich.it.controller;

import by.romanovich.it.exceptions.WebErrorCode;
import by.romanovich.it.exceptions.WebException;
import by.romanovich.it.pojos.Autor;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exceptions.ServiceException;
import by.romanovich.it.service.service.interfases.BookService;
import by.romanovich.it.service.service.interfases.CategoryService;
import by.romanovich.it.service.service.interfases.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * This is book controller.
 * @author Romanovich Andrei
 * @version 1.0
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private static Logger log = Logger.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRedirect(){
        return "redirect:/book/list/1";
    }

    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public String listBooks(ModelMap model, @PathVariable Long page) throws WebException {
        List<Book> bookList;
        int pages = 1;
        if(page != null) {
            pages = page.intValue();
        }
        Integer recordsPage = 5;
        try {
            Long totalCount = bookService.getRowCountBooks();
            bookList = bookService.findBooks(((pages - 1) * recordsPage), recordsPage);
            log.info("Getting totalCount" + totalCount);
            int noOfPages = (int) Math.ceil(totalCount * 1.0 / recordsPage);
            model.addAttribute("bookList", bookList);
            model.addAttribute("noOfPages", noOfPages);
            model.addAttribute("currentPage", page);
        } catch (ServiceException e) {
            log.error("Cannot get all books" + e);
            throw new WebException(e, WebErrorCode.NC_WEB_000);
        }
        return "allBooks";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBook(Model model, @ModelAttribute Book book) throws WebException {
        List<Category> categories = null;
        try {
            categories = categoryService.getAllCategories();
        } catch (ServiceException e) {
            log.info("Cannot get all categories");
            throw new WebException(e, WebErrorCode.NC_WEB_001);
        }
        log.info("Creating new book");
        model.addAttribute("categories", categories);
        return "addBook";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveBook(@Valid Book book, ModelMap model, BindingResult result,
                           @RequestParam String firstname, @RequestParam String lastname,
                           @RequestParam String id, @RequestParam String id_user) throws WebException {
        if(result.hasErrors()) {
            List<Category> categories;
            try {
                categories = categoryService.getAllCategories();
            } catch (ServiceException e) {
                log.info("Cannot get all categories");
                throw new WebException(e, WebErrorCode.NC_WEB_001);
            }
            log.info("Some errors in validation");
            model.addAttribute("categories", categories);
            return "addBook";
        }
        Long id_cat = Long.parseLong(id);
        Long id_u = Long.parseLong(id_user);
        Autor autor = new Autor(firstname, lastname);
        Category category = new Category();
        User user = new User();
        category.setId(id_cat);
        user.setId(id_u);
        try {
            book.setCategory(category);
            book.getAutors().add(autor);
            book.setUser(user);
            bookService.saveBook(book);
        } catch (ServiceException e) {
            log.info("Cannot save book");
            throw new WebException(e, WebErrorCode.NC_WEB_002);
        }
        log.info("Created new book successfully");
        return "redirect:/book/list";
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.GET)
    public String updateBook(ModelMap modelMap, @RequestParam String id_book) throws WebException {
        Long idResult = Long.parseLong(id_book);
        try {
            List<Category> categoryList = categoryService.getAllCategories();
            Book book = bookService.getBookById(idResult);
            Set<Autor> autorSet = book.getAutors();
            modelMap.addAttribute("categoryList", categoryList);
            modelMap.addAttribute("autorSet", autorSet);
            modelMap.addAttribute("book", book);
        } catch (ServiceException e) {
            log.error("Cannot get all categories and book" + e);
            throw new WebException(e, WebErrorCode.NC_WEB_001);
        }
        return "updateBook";
    }

    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String saveUpdateBook(@RequestParam String id_book, @RequestParam String name,
                                 @RequestParam String description, @RequestParam String firstname,
                                 @RequestParam String lastname, @RequestParam String yearPublishing,
                                 @RequestParam String id_cat, @RequestParam String id_user) throws WebException {
        Long idCat = Long.parseLong(id_cat);
        Long idBook = Long.parseLong(id_book);
        Long idUser = Long.parseLong(id_user);
        Book book = new Book();
        Category category = null;
        Autor autor = new Autor();
        User user = null;
        try {
            book.setId(idBook);
            book.setName(name);
            book.setDescription(description);
            book.setYearPublishing(yearPublishing);
            autor.setFirstname(firstname);
            autor.setLastname(lastname);
            book.getAutors().add(autor);
            category = categoryService.getCategoryById(idCat);
            book.setCategory(category);
            user = userService.getUserById(idUser);
            book.setUser(user);
            bookService.updateBook(book);
        } catch (ServiceException e) {
            log.error("Cannot get all categories and book" + e);
            throw new WebException(e, WebErrorCode.NC_WEB_001);
        }
        return "redirect:/book/list";
    }


    @RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
    public String deleteBook(@RequestParam String id_book) throws WebException {
        Long idResult = Long.parseLong(id_book);
        try {
            bookService.deleteBook(idResult);
        } catch (ServiceException e) {
            log.info("Cannot deleting book by id" + e);
            throw new WebException(e, WebErrorCode.NC_WEB_007);
        }
        return "redirect:/book/list";
    }

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public String findBook(@RequestParam("search") String search,
                              Model model) throws WebException {
        List<Book> bookList;
        try {
                bookList = bookService.findBook(search);
            if (bookList.size() == 0) {
                log.info("Cannot find book");
                throw new WebException(WebErrorCode.NC_WEB_008);
            }
        } catch (ServiceException e) {
            log.info("Error with find books");
            throw new WebException(e, WebErrorCode.NC_WEB_001);
        }
        log.info("Finded books successfully" + bookList);
        model.addAttribute("");
        return "allBook";
    }

}
