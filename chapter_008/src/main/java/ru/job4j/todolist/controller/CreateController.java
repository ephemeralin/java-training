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
import java.util.List;

/**
 * The type Create controller.
 */
@WebServlet(
        name = "CreateController",
        description = "Add task servlet",
        urlPatterns = {"/create"}
)
public class CreateController extends HttpServlet {
    /**
     * Logger instance.
     */
    private final Logger log = LogManager.getLogger(this.getClass());

    /**
     * The type Wrapper.
     */
    public class Wrapper {
        private String description;

        /**
         * Gets description.
         *
         * @return the description
         */
        public String getDescription() {
            return description;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("create post!");
        response.setContentType("application/json");
        Gson gson = new Gson();
        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = request.getReader().readLine()) != null) {
                sb.append(s);
            }
            Wrapper wrapper = gson.fromJson(sb.toString(), Wrapper.class);
            final String description = wrapper.getDescription();
            final Item item = new Item(System.currentTimeMillis(), description, false);
            int id = ItemDAO.getInstance().create(item);
            item.setId(id);

            final ArrayList<Item> itemsList = new ArrayList<>();
            itemsList.add(item);
            String toJson = new Gson().toJson(itemsList);
            System.out.println(toJson);
            response.getWriter().print(toJson);
            response.getWriter().flush();

        } catch (Exception ex) {
            ex.printStackTrace();
            response.getOutputStream().print(ex.getMessage());
            response.getOutputStream().flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("create get!");
        final List<Item> itemsList = ItemDAO.getInstance().findAll();
        response.setContentType("text/json");
        String s = new Gson().toJson(itemsList);
        System.out.println(s);
        response.getWriter().print(s);
        response.getWriter().flush();
    }

    @Override
    public void init() {
        this.log.info("CreateController initiated.");
    }

    @Override
    public void destroy() {
        this.log.info("CreateController destroyed.");
    }
}
