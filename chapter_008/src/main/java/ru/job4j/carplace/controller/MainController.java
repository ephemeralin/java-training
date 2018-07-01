package ru.job4j.carplace.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@WebServlet(
        name = "MainController",
        description = "Main cars place servlet",
        urlPatterns = {"/cars"}
)
public final class MainController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

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
        this.log = LogManager.getLogger(this.getClass());
    }

    @Override
    public void destroy() {
    }
}
