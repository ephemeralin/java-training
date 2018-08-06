package com.ephemeralin.carplace.controller;

import com.ephemeralin.carplace.model.Car;
import com.ephemeralin.carplace.service.IService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Log4j2
public class CarController {

    @Autowired
    private IService<Car> carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
//    @GetMapping("/")
    public String showAll(ModelMap model) {
        List<Car> carsList = carService.findAll();
        model.addAttribute("carsList", carsList);
//        request.setAttribute("login", request.getSession().getAttribute("login"));
//        request.setAttribute("role", request.getSession().getAttribute("role"));

        return "cars";
    }

//    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    public String add(@ModelAttribute User user) {
//        this.users.add(user);
//        return "redirect:users.do";
//    }
}
