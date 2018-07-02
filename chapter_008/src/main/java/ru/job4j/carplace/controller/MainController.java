package ru.job4j.carplace.controller;

import ru.job4j.carplace.model.dao.CarDAO;
import ru.job4j.carplace.model.entity.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * The Main servlet.
 */
@lombok.extern.log4j.Log4j2
@WebServlet(
        name = "MainController",
        description = "Main cars place servlet",
        urlPatterns = {"/cars"}
)
public final class MainController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Car> carsList = CarDAO.getInstance().findAll();
        carsList.sort(Comparator.comparingLong(Car::getId));
        request.setAttribute("carsList", carsList);
        request.setAttribute("login", request.getSession().getAttribute("login"));
        request.setAttribute("role", request.getSession().getAttribute("role"));
        request.getRequestDispatcher("/WEB-INF/view/cars.jsp").forward(request, response);
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }
}
