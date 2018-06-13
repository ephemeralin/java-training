package ru.job4j.todolist.controller;

import com.google.gson.Gson;
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
import java.util.ArrayList;

/**
 * The type Show all controller.
 */
@WebServlet(
        name = "ShowAllController",
        description = "Show all or only active tasks servlet",
        urlPatterns = {"/showAll"}
)
public class ShowAllController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("show all get!");
        response.setContentType("application/json");
        final boolean showAll = Boolean.parseBoolean(request.getParameter("showAll"));
        ArrayList<Item> itemsList;
        if (showAll) {
            itemsList = (ArrayList<Item>) ItemDAO.getInstance().findAll();
        } else {
            itemsList = (ArrayList<Item>) ItemDAO.getInstance().findOnlyActive();
        }
        itemsList.sort((o1, o2) -> {
            if (o1.getCreated() == o2.getCreated()) {
                return 0;
            } else if (o1.getCreated() < o2.getCreated()) {
                return -1;
            } else {
                return 1;
            }
        });
        String toJson = new Gson().toJson(itemsList);
        response.getWriter().print(toJson);
        response.getWriter().flush();
    }

    @Override
    public void init() {
        this.log = LogManager.getLogger(this.getClass());
        this.log.info("UpdateController initiated.");
    }

    @Override
    public void destroy() {
        this.log.info("UpdateController destroyed.");
    }
}
