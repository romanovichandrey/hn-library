package by.romanovich.it.controller;

import by.romanovich.it.pojos.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = "/welcom", method = RequestMethod.GET)
    public String welcom(ModelMap model, @ModelAttribute User user) {
        return "welcom";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String error404() {
        return "404";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String error500() {
        return "500";
    }
}
