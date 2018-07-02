package ru.job4j.carplace.controller;

import ru.job4j.carplace.model.dao.CarDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The user Delete servlet.
 */
@lombok.extern.log4j.Log4j2
@WebServlet(
        name = "DeleteController",
        description = "Delete car by id",
        urlPatterns = {"/delete_car"}
)
public final class DeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String carId = request.getParameter("id");
        if (!carId.isEmpty()) {
            if (CarDAO.getInstance().delete(Integer.parseInt(carId))) {
                response.sendRedirect("cars");
            } else {
                log.error(String.format("Car was not deleted by id <%s>", carId));
            }
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }
}
