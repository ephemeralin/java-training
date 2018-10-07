package com.ephemeralin.carplace.controller;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.model.Model;
import com.ephemeralin.carplace.service.IMakeService;
import com.ephemeralin.carplace.service.IModelService;
import com.ephemeralin.utils.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * The type Model controller.
 */
@Controller
@Log4j2
public class ModelController {

    @Autowired
    private IMakeService makeService;
    @Autowired
    private IModelService modelService;

    /**
     * Gets models.
     *
     * @param makeIdString the make id string
     * @return the models
     */
    @PostMapping(value = "/models", params = {"make_id"})
    @ResponseBody
    public String getModels(
            @RequestParam(name = "make_id") String makeIdString) {
        String modelsJson = "[]";
        try {
            int makeId = Integer.parseInt(makeIdString);
            Make make = makeService.findById(makeId);
            List<Model> modelsList = modelService.findByMake(make);
            GsonBuilder gb = new GsonBuilder();
            gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
            Gson gson = gb.create();
            modelsJson = gson.toJson(modelsList);
        } catch (NumberFormatException ignored) {
        }
        return modelsJson;
    }
}
