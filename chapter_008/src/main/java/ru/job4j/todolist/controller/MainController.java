package ru.job4j.todolist.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.todolist.model.dao.ItemDAO;
import ru.job4j.todolist.model.entity.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The Main servlet.
 */
@WebServlet(
        name = "MainController",
        description = "Main todo list servlet",
        urlPatterns = {"/main"}
)
public final class MainController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("main get!");
        List<Item> list = ItemDAO.getInstance().findAll();
        list.sort((o1, o2) -> {
            if (o1.getCreated() == o2.getCreated()) {
                return 0;
            } else if (o1.getCreated() < o2.getCreated()) {
                return -1;
            } else {
                return 1;
            }
        });
        request.setAttribute("itemsList", list);
        request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);
    }

    @Override
    public void init() {
        this.log = LogManager.getLogger(this.getClass());
        this.log.info("MainController initiated.");
    }

    @Override
    public void destroy() {
        this.log.info("MainController destroyed.");
    }
}
