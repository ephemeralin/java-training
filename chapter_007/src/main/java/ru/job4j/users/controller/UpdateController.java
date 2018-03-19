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
        User user = UserStore.getInstance().getByEmail(email);
        if (user != null) {
            request.setAttribute("email", user.getEmail());
            request.setAttribute("name", user.getName());
            request.setAttribute("login", user.getLogin());
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
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        User user = UserStore.getInstance().getByEmail(email);
        if (user != null) {
            user.setName(name);
            user.setLogin(login);
            if (UserStore.getInstance().update(user)) {
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
