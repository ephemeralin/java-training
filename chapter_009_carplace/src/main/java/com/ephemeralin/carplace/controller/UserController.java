package com.ephemeralin.carplace.controller;

import com.ephemeralin.carplace.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@Log4j2
public class UserController {
    final List<User> users = new CopyOnWriteArrayList<>();

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAll(ModelMap model) {
        User user = new User();
        //user.setName("test user");
        users.add(user);
        model.addAttribute("users", this.users);
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String add(@ModelAttribute User user) {
        this.users.add(user);
        return "redirect:users.do";
    }
}
