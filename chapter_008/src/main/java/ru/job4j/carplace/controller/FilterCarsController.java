package ru.job4j.carplace.controller;

import com.google.gson.Gson;
import ru.job4j.carplace.model.dao.CarDAO;
import ru.job4j.carplace.model.entity.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToLongFunction;

/**
 * The Main servlet.
 */
@lombok.extern.log4j.Log4j2
@WebServlet(
        name = "FilterCarsController",
        description = "Filter cars servlet",
        urlPatterns = {"/filtercars"}
)
public final class FilterCarsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filterName = request.getParameter("filterName");
        List<Car> carsList = new ArrayList<>();
        if (filterName == null || filterName.equals("All")) {
            carsList = CarDAO.getInstance().findAll();
        } else if (filterName.equals("Only with photo")) {
            carsList = CarDAO.getInstance().findWithPhotoOnly();
        } else if (filterName.equals("Only today")) {
            carsList = CarDAO.getInstance().findToday();
        }
        carsList.sort(Comparator.comparingLong((ToLongFunction<Car>) car -> car.getDate()).reversed());

        String toJson = new Gson().toJson(carsList);
        response.getWriter().print(toJson);
        response.getWriter().flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }
}
