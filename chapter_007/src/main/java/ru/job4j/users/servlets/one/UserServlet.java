package ru.job4j.users.servlets.one;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.users.model.Role;
import ru.job4j.users.model.User;
import ru.job4j.users.model.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * The type Crud servlet.
 */
public final class UserServlet extends HttpServlet {
    /**
     * User store.
     */
    private UserStore userStore;
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String outputText;
        String email = req.getParameter("email");
        User user = userStore.getByEmail(email);
        if (user == null) {
            resp.setStatus(404);
            outputText = String.format("No user with the e-mail: %s", email);
        } else {
            resp.setStatus(200);
            outputText = String.format("User found: %s", user);
        }
        outputInfo(resp, outputText);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String outputText;
        String email = request.getParameter("email");
        if (userStore.deleteByEmail(email)) {
            outputText = String.format("User deleted by email: %s", email);
        } else {
            outputText = String.format("User was not deleted by email: %s", email);
        }
        outputInfo(response, outputText);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String outputText;
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        Long created = Calendar.getInstance().getTimeInMillis();
        User user = userStore.getByEmail(email);
        if (user == null) {
            //Create user
            user = new User(name, login, email, created, null, new Role(1, "user"));
            if (userStore.add(user)) {
                response.setStatus(201);
                outputText = String.format("New user added to the DB: %s", user);
            } else {
                response.setStatus(500);
                outputText = "User was not added to the DB";
            }
        } else {
            //Update user
            user.setLogin(login);
            user.setName(name);
            if (userStore.update(user)) {
                response.setStatus(200);
                outputText = String.format("User updated: %s", user);
            } else {
                response.setStatus(500);
                outputText = "User was not updated";
            }
        }
        outputInfo(response, outputText);
    }

    /**
     * Show output.
     * @param resp the Responce
     * @param outputText the text
     * @throws IOException the exception
     */
    private void outputInfo(HttpServletResponse resp, String outputText) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(outputText);
        writer.flush();
    }

    @Override
    public void init() throws ServletException {
        this.userStore = UserStore.getInstance();
        this.log = LogManager.getLogger(this.getClass());
        this.log.info("UserServlet initiated.");
    }

    @Override
    public void destroy() {
        this.log.info("UserServlet destroyed.");
    }
}
