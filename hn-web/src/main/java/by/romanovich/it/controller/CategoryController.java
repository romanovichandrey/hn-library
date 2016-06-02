package by.romanovich.it.controller;

import by.romanovich.it.exceptions.WebErrorCode;
import by.romanovich.it.exceptions.WebException;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.service.exceptions.ServiceException;
import by.romanovich.it.service.service.interfases.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private static Logger log = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {
        Category category = new Category();
        log.info("Creating new book");
        model.addAttribute("category", category);
        return "addCategory";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveBook(@Valid Category category, BindingResult result, Model model,
                           final RedirectAttributes redirectAttributes) throws WebException {
        if(result.hasErrors()) {
            log.info("Some errors in validation");
            return "addCategory";
        }
        try {
            categoryService.saveCategory(category);
        } catch (ServiceException e) {
            log.info("Cannot save category");
            throw new WebException(e, WebErrorCode.NC_WEB_003);
        }
        redirectAttributes.addFlashAttribute("success", "Category" + category.getName()
                + "add successfully!");
        log.info("Created new category successfully");
        return "redirect:/book/list";
    }
}
