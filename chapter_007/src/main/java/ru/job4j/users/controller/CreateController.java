package ru.job4j.users.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.users.model.City;
import ru.job4j.users.model.Role;
import ru.job4j.users.model.User;
import ru.job4j.users.model.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

/**
 * The user Create servlet.
 */
public final class  CreateController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final UserStore userStore = UserStore.getInstance();
        request.setAttribute("rolesList", userStore.getAllRoles());
        request.setAttribute("countriesList", userStore.getAllCountries());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final UserStore userStore = UserStore.getInstance();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Long created = Calendar.getInstance().getTimeInMillis();
        Role role = userStore.getRoleByRoleName(request.getParameter("role"));
        City city = userStore.getCityById(request.getParameter("city"));
        User user = new User(name, login, email, created, password, role, city);
        if (userStore.add(user)) {
            response.sendRedirect("main");
        } else {
            String err = String.format("User with email <%s> was not created!", email);
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
