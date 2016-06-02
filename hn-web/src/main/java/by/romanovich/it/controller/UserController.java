package by.romanovich.it.controller;

import by.romanovich.it.exceptions.WebErrorCode;
import by.romanovich.it.exceptions.WebException;
import by.romanovich.it.pojos.Adress;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exceptions.ServiceException;
import by.romanovich.it.service.service.interfases.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(@ModelAttribute User user) {
        return "addUser";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveUser(ModelMap model, @Valid User user, BindingResult result,
                           HttpSession httpSession, @RequestParam String street,
                           @RequestParam String city, @RequestParam String state,
                           @RequestParam String country) throws WebException {
        if(result.hasErrors()) {
            log.info("Some errors in validation");
            return "addUser";
        }
        try {
            Adress adress = new Adress(street, city, state, country);
            user.setAdress(adress);
            adress.setUser(user);
            userService.saveUser(user);
        } catch (ServiceException e) {
            log.info("Cannot save user");
            throw new WebException(e, WebErrorCode.NC_WEB_004);
        }
        log.info("Created new user successfully" + user);
        httpSession.setAttribute("user", user);
        return "redirect:/book/list";
    }

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public String login(HttpSession httpSession, Principal principal) throws WebException {
        String userName = principal.getName();
        User user = null;
        if(userName != null) {
            try {
                user = userService.getUserByUserName(userName);
                log.info("Getting user by login " + user);

            } catch (ServiceException e) {
                log.info("Cannot get user by login");
                throw new WebException(e, WebErrorCode.NC_WEB_005);
            }
        }
        httpSession.setAttribute("user", user);
        return "redirect:/book/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRedirect(){
        return "redirect:/user/list/1";
    }

    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public String listUser(ModelMap model, @PathVariable Long page) throws WebException {
        List<User> userList;
        int pages = 1;
        if(page != null) {
            pages = page.intValue();
        }
        Integer recordsPage = 5;
        try {
            Long totalCount = userService.getRowCountBooks();
            userList = userService.findBooks(((pages - 1) * recordsPage), recordsPage);
            log.info("Getting totalCount" + totalCount);
            int noOfPages = (int) Math.ceil(totalCount * 1.0 / recordsPage);
            model.addAttribute("userList", userList);
            model.addAttribute("noOfPages", noOfPages);
            model.addAttribute("currentPage", page);
        } catch (ServiceException e) {
            log.error("Cannot get all users" + e);
            throw new WebException(e, WebErrorCode.NC_WEB_006);
        }
        return "allUsers";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profileUser() {
        return "profileUser";
    }




}
