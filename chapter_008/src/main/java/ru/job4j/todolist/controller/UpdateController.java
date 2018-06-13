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

/**
 * The type Update controller.
 */
@WebServlet(
        name = "UpdateController",
        description = "Update task servlet",
        urlPatterns = {"/update"}
)
public class UpdateController extends HttpServlet {
    /**
     * Logger instance.
     */
    private Logger log;

    /**
     * The type Wrapper.
     */
    public class Wrapper {
        private String id;
        private String description;
        private String done;

        /**
         * Gets description.
         *
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Gets id.
         *
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * Gets done.
         *
         * @return the done
         */
        public String getDone() {
            return done;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("update post!");
        response.setContentType("application/json");
        Gson gson = new Gson();
        try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = request.getReader().readLine()) != null) {
                sb.append(s);
            }
            Wrapper wrapper = gson.fromJson(sb.toString(), Wrapper.class);
            final int id = Integer.parseInt(wrapper.getId());
            final boolean done = Boolean.parseBoolean(wrapper.getDone());
            Item item = ItemDAO.getInstance().findById(id);
            item.setDone(done);
            ItemDAO.getInstance().update(item);
            response.setContentType("text/json");
            response.getOutputStream().print(done);
            response.getOutputStream().flush();

        } catch (Exception ex) {
            ex.printStackTrace();
            response.getOutputStream().print(ex.getMessage());
            response.getOutputStream().flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("update get!");
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
