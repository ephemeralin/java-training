package com.ephemeralin.carplace.controller;

import com.ephemeralin.carplace.model.*;
import com.ephemeralin.carplace.service.IService;
import com.ephemeralin.utils.FileUtility;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Controller
@Log4j2
public class CarController {

    @Autowired
    private IService<Car> carService;
    @Autowired
    private IService<Make> makeService;
    @Autowired
    private IService<com.ephemeralin.carplace.model.Model> modelService;
    @Autowired
    private IService<Engine> engineService;
    @Autowired
    private IService<Body> bodyService;
    @Autowired
    private IService<Transmission> transmissionService;
    @Autowired
    private IService<User> userService;

    @GetMapping(value = "/cars")
    public String showAll(ModelMap model) {
        List<Car> carsList = carService.findAll();
        model.addAttribute("carsList", carsList);
        return "cars";
    }

    @GetMapping(value = "/add_car", params = {"filter_name"})
    public ModelAndView add(
            @RequestParam String filter_name) {
        ModelAndView mv = new ModelAndView("add_car");
        mv.addObject("car", new Car());
        mv.addObject("filter_name", filter_name);
        mv.addObject("makesList", makeService.findAll());
        mv.addObject("enginesList", engineService.findAll());
        mv.addObject("bodiesList", bodyService.findAll());
        mv.addObject("transmissionsList", transmissionService.findAll());
        return mv;
    }

    @PostMapping(value = "/add_car")
    public ModelAndView addCar(org.springframework.ui.Model model,
                         @RequestPart("file") MultipartFile file,
                         @RequestParam("carId") String carId,
                         @RequestParam("model") String modelId,
                         @RequestParam("make") String makeId,
                         @RequestParam("name") String carName,
                         @RequestParam("engine") String engineId,
                         @RequestParam("body") String bodyId,
                         @RequestParam("transmission") String transmissionId,
                         @RequestParam(name = "sold", required = false, defaultValue = "false") String sold
    ) {

        Long date = Calendar.getInstance().getTimeInMillis();

        Car car = new Car();
        car.setName(carName);
        car.setSold((sold != null && sold.equals("on")));
        car.setMake(new Make(Integer.parseInt(makeId)));
        car.setModel(new Model(Integer.parseInt(modelId)));
        car.setBody(new Body(Integer.parseInt(bodyId)));
        car.setEngine(new Engine(Integer.parseInt(engineId)));
        car.setTransmission(new Transmission(Integer.parseInt(transmissionId)));

        HashMap<String, Object> criterias = new HashMap<>();
        criterias.put("login", "admin"); //todo
        List<User> userList = userService.findByCriteria(criterias);
        car.setOwner(userList.isEmpty() ? new User() : userList.get(0));
        car.setImage(FileUtility.getFileFromMultipart(file));
        car.setBase64imageFile(Base64.getEncoder().encodeToString(car.getImage()));
        car.setDate(date);
        if (carId.isEmpty()) {
            int id = carService.create(car);
            car.setId(id);
        } else {
            car.setId(Integer.parseInt(carId));
            carService.update(car);
        }

        ModelAndView mv = new ModelAndView("cars");
        List<Car> carsList = carService.findAll();
        mv.addObject("carsList", carsList);
        return mv;
    }


    @GetMapping(value = "/update_car", params = {"car_id"})
    public ModelAndView updateCar(
            @RequestParam String car_id) {
        ModelAndView mv = new ModelAndView();
        if (!car_id.isEmpty()) {
            Car car = carService.findById(Integer.parseInt(car_id));
            mv.addObject("car", car);
            mv.addObject("makesList", makeService.findAll());
            HashMap<String, Object> criterias = new HashMap<>();
            criterias.put("make", car.getMake());
            mv.addObject("modelsList", modelService.findByCriteria(criterias));
            mv.addObject("enginesList", engineService.findAll());
            mv.addObject("bodiesList", bodyService.findAll());
            mv.addObject("transmissionsList", transmissionService.findAll());

            mv.setViewName("add_car");
        } else {
            //mv.addObject("car", new Car());
            mv.setViewName("cars");
        }
        return mv;
    }
}
