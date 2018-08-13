package com.ephemeralin.carplace.controller;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.service.IService;
import com.ephemeralin.utils.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@Log4j2
public class ModelController {

    @Autowired
    private IService<Make> makeService;
    @Autowired
    private IService<com.ephemeralin.carplace.model.Model> modelService;

    @PostMapping(value = "/models", params = {"make_id"})
    @ResponseBody
    public String getModels(
            @RequestParam String make_id) {
        HashMap<String, Object> criterias = new HashMap<>();
        criterias.put("make", makeService.findById(Integer.parseInt(make_id)));
        List<com.ephemeralin.carplace.model.Model> modelsList = modelService.findByCriteria(criterias);

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = gb.create();
        return gson.toJson(modelsList);
    }
}
