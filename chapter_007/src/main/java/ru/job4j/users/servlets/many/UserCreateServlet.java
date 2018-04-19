package ru.job4j.users.servlets.many;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.users.model.*;

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
public final class UserCreateServlet extends HttpServlet {
    /**
     * User store.
     */
    private UserStore userStore;
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String outputText = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "   <p>Please, fill all fields and submit...</p>" +
                "   " +
                "   <form action='create' method = 'post'>" +
                "       E-mail:<br>" +
                "       <input type='text' name='email' value=''>" +
                "       <br>" +
                "       Name:<br>" +
                "       <input type='text' name='name' value=''>" +
                "       <br>" +
                "       Login:<br>" +
                "       <input type='text' name='login' value=''>" +
                "       <br><br>" +
                "       <input type='submit' value='Create'>" +
                "   </form>" +
                "   <form action='main' method = 'get'>" +
                "       <input type='submit' value='Cancel'>" +
                "   </form>" +
                "" +
                "</body>" +
                "</html>";
        outputInfo(response, outputText);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resultText = "";
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        Long created = Calendar.getInstance().getTimeInMillis();
        Role role = new Role(1, "user");
        Country country = new Country(1, "Russia");
        City city = new City(1, "Moscow", country);
        User user = new User(name, login, email, created, null, role, city);
        if (userStore.add(user)) {
            resultText = String.format("User with email %s created successfully.", email);
        } else {
            resultText = String.format("User with email %s was not created!", email);
        }
        String outputText = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "   <table>" +
                "       <tr><td>" + resultText + "</tr></td>" +
                "       <tr><td>" +
                "           <form action='main' method = 'get'>" +
                "           <input type='submit' value='Go back'>" +
                "           </form>" +
                "       </tr></td>" +
                "   </table>" +
                "" +
                "" +
                "</body>" +
                "</html>";
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
        this.userStore.destroy();
        this.log.info("UserServlet destroyed.");
    }
}
