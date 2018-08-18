package com.ephemeralin.carplace.controller;

import com.ephemeralin.carplace.model.*;
import com.ephemeralin.carplace.service.*;
import com.ephemeralin.utils.FileUtility;
import com.ephemeralin.utils.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.function.ToLongFunction;

/**
 * The type Car controller.
 */
@Controller
@Log4j2
public class CarController {

    @Autowired
    private ICarService carService;
    @Autowired
    private IModelService modelService;
    @Autowired
    private IBodyService bodyService;
    @Autowired
    private IEngineService engineService;
    @Autowired
    private IMakeService makeService;
    @Autowired
    private ITransmissionService transmissionService;
    @Autowired
    private IUserService userService;

    /**
     * Show all string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping(value = "/cars")
    public String showAll(ModelMap model) {
        List<Car> carsList = carService.findAll();
        model.addAttribute("carsList", carsList);
        model.addAttribute("filter", "All");
        return "cars";
    }

    /**
     * Filter cars string.
     *
     * @param filterName the filter name
     * @return the string
     */
    @PostMapping(value = "/filterCars", params = {"filter_name"})
    @ResponseBody
    public String filterCars(@RequestParam(name = "filter_name") String filterName) {
        List<Car> carsList = new ArrayList<>();
        if (filterName.equals("Only with photo")) {
            carsList = carService.findWithPhoto();
        } else if (filterName.equals("Only today")) {
            carsList = carService.findTodayCars();
        } else if (filterName == null || filterName.equals("All")) {
            carsList = carService.findAll();
        }
        carsList.sort(Comparator.comparingLong((ToLongFunction<Car>) car -> car.getDate()).reversed());
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = gb.create();
        String s = gson.toJson(carsList);
        return s;
    }

    /**
     * Add model and view.
     *
     * @param filterName the filter name
     * @return the model and view
     */
    @GetMapping(value = "/add_car", params = {"filter_name"})
    public ModelAndView add(
            @RequestParam(name = "filter_name") String filterName) {
        ModelAndView mv = new ModelAndView("add_car");
        mv.addObject("car", new Car());
        mv.addObject("filter_name", filterName);
        mv.addObject("makesList", makeService.findAll());
        mv.addObject("enginesList", engineService.findAll());
        mv.addObject("bodiesList", bodyService.findAll());
        mv.addObject("transmissionsList", transmissionService.findAll());
        return mv;
    }

    /**
     * Add car model and view.
     *
     * @param model          the model
     * @param file           the file
     * @param carId          the car id
     * @param modelId        the model id
     * @param makeId         the make id
     * @param carName        the car name
     * @param engineId       the engine id
     * @param bodyId         the body id
     * @param transmissionId the transmission id
     * @param sold           the sold
     * @return the model and view
     */
    @PostMapping(value = "/add_car")
    @SuppressWarnings("checkstyle:parameternumber")
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
        car.setOwner(userService.findByLogin("admin"));
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
        mv.addObject("filter", "All");
        List<Car> carsList = carService.findAll();
        mv.addObject("carsList", carsList);
        return mv;
    }

    /**
     * Add car model and view.
     *
     * @param model the model
     * @param carId the car id
     * @return the model and view
     */
    @PostMapping(value = "/delete_car")
    public ModelAndView addCar(org.springframework.ui.Model model,
                               @RequestParam(name = "car_id") String carId
    ) {
        carService.delete(Integer.parseInt(carId));

        ModelAndView mv = new ModelAndView("cars");
        mv.addObject("filter", "All");
        List<Car> carsList = carService.findAll();
        mv.addObject("carsList", carsList);
        return mv;
    }


    /**
     * Update car model and view.
     *
     * @param carId the car id
     * @return the model and view
     */
    @GetMapping(value = "/update_car", params = {"car_id"})
    public ModelAndView updateCar(
            @RequestParam(name = "car_id") String carId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("filter", "All");
        if (!carId.isEmpty()) {
            Car car = carService.findById(Integer.parseInt(carId));
            mv.addObject("car", car);
            mv.addObject("makesList", makeService.findAll());
            mv.addObject("modelsList", modelService.findByMake(car.getMake()));
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
