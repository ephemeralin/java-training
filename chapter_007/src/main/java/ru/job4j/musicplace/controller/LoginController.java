package ru.job4j.musicplace.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.musicplace.model.dao.MainRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Login controller.
 */
public class LoginController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/mp-login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logoff = req.getParameter("logoff");
        if (logoff != null && logoff.contains("logoff")) {
            req.getSession().invalidate();
            doGet(req, resp);
        } else {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (MainRepository.getInstance().isIdentified(login, password)) {
                final HttpSession session = req.getSession();
                session.setAttribute("login", login);
//                session.setAttribute("role", UserStore.getInstance().getRoleByLogin(login));
                resp.sendRedirect("mp-main");
            } else {
                req.setAttribute("error", "Login failed!");
                doGet(req, resp);
            }
        }
    }

    @Override
    public void init() {
        this.log = LogManager.getLogger(this.getClass());
        this.log.info("LoginController MP initiated.");
    }

    @Override
    public void destroy() {
        this.log.info("UserServlet destroyed.");
    }
}
