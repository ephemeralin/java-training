package ru.job4j.users.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.users.model.User;
import ru.job4j.users.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The Main servlet.
 */
public final class MainController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> allUsers = UserStore.getInstance().getAll();
        request.setAttribute("usersList", allUsers);
        request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
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
