package ru.job4j.users.controller;

import com.google.gson.Gson;
import ru.job4j.users.model.City;
import ru.job4j.users.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type City controller.
 */
public class CityController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int countryId = Integer.parseInt(request.getParameter("country_id"));
        List<City> citiesList = UserStore.getInstance().getCitiesByCountryId(countryId);
        List<HashMap<String, String>> cities = new ArrayList<>();
        for (City city : citiesList) {
            final HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(city.getId()));
            map.put("name", city.getName());
            cities.add(map);
        }
        response.setContentType("text/json");
        String s = new Gson().toJson(citiesList);
        System.out.println(s);
        response.getWriter().print(s);
        response.getWriter().flush();
    }
}
