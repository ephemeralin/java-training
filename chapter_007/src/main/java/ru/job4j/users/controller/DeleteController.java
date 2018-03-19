package ru.job4j.users.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.users.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The user Delete servlet.
 */
public final class DeleteController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        if (UserStore.getInstance().deleteByEmail(email)) {
            response.sendRedirect("main");
        } else {
            String err = String.format("User was not deleted by email <%s>", email);
            request.setAttribute("errorMsg", err);
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    @Override
    public void init() {
        this.log = LogManager.getLogger(this.getClass());
        this.log.info("UserServlet initiated.");
    }

    @Override
    public void destroy() {
        this.log.info("UserServlet destroyed.");
    }
}
