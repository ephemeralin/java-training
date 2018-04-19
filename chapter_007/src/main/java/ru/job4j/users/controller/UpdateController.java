package ru.job4j.users.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.users.model.User;
import ru.job4j.users.model.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Crud servlet.
 */
public final class UpdateController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UserStore userStore = UserStore.getInstance();
        User user = userStore.getByEmail(email);
        if (user != null) {
            request.setAttribute("email", user.getEmail());
            request.setAttribute("name", user.getName());
            request.setAttribute("login", user.getLogin());
            request.setAttribute("role", user.getRole().getName());
            request.setAttribute("rolesList", userStore.getAllRoles());
            request.setAttribute("currentRole", request.getSession().getAttribute("role"));
            request.setAttribute("countriesList", userStore.getAllCountries());
            request.setAttribute("country", user.getCountry().getName());
            request.setAttribute("cityId", user.getCity().getId());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/update.jsp");
            dispatcher.forward(request, response);
        } else {
            String err = String.format("User with email <%s> was not found!", email);
            request.setAttribute("errorMsg", err);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/error.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String err = "";
        final UserStore userStore = UserStore.getInstance();
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String roleName = request.getParameter("role");
        String cityId = request.getParameter("city");
        User user = userStore.getByEmail(email);
        if (user != null) {
            user.setName(name);
            user.setLogin(login);
            user.setRole(userStore.getRoleByRoleName(roleName));
            user.setCity(userStore.getCityById(cityId));
            if (password != null && !password.isEmpty()) {
                user.setPassword(password);
            }
            if (userStore.update(user)) {
                response.sendRedirect("main");
            } else {
                err = String.format("User with email <%s> was not updated!", email);
            }
        } else {
            err = String.format("Could NOT find user by email <%s> for updating", email);
        }
        if (!err.isEmpty()) {
            request.setAttribute("errorMsg", err);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/error.jsp");
            dispatcher.forward(request, response);
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
