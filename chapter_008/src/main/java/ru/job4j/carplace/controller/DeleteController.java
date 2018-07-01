package ru.job4j.carplace.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
@WebServlet(
        name = "DeleteController",
        description = "Delete car by id",
        urlPatterns = {"/delete_car"}
)
public final class DeleteController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String carId = request.getParameter("id");
        if (!carId.isEmpty()) {
            if (CarDAO.getInstance().delete(Integer.parseInt(carId))) {
                response.sendRedirect("cars");
            } else {
                String err = String.format("Car was not deleted by id <%s>", carId);
                this.log.info("UserServlet initiated.");
            }
        }
    }

    @Override
    public void init() {
        this.log = LogManager.getLogger(this.getClass());
    }

    @Override
    public void destroy() {
    }
}
