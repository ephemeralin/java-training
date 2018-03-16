package ru.job4j.userservlet.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.userservlet.User;
import ru.job4j.userservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The type Crud servlet.
 */
public final class UserUpdateServlet extends HttpServlet {
    /**
     * User store.
     */
    private UserStore userStore;
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = "";
        String login = "";
        User user = userStore.getByEmail(email);
        if (user != null) {
            name = user.getName();
            login = user.getLogin();
        }
        String outputText = "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "    <meta charset='UTF-8'>" +
                "    <title>Title</title>" +
                "</head>" +
                "<body>" +
                "   <p>Please, edit fields and submit...</p>" +
                "   " +
                "   <form action='update' method = 'post'>" +
                "       E-mail:<br>" +
                "       <input type='text' name='email' value='" + email + "' readonly>" +
                "       <br>" +
                "       Name:<br>" +
                "       <input type='text' name='name' value='" + name + "'>" +
                "       <br>" +
                "       Login:<br>" +
                "       <input type='text' name='login' value='" + login + "'>" +
                "       <br><br>" +
                "       <input type='submit' value='Submit'>" +
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String resultText;
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        User user = userStore.getByEmail(email);
        if (user != null) {
            user.setName(name);
            user.setLogin(login);
            if (userStore.update(user)) {
                resultText = String.format("User with email %s updated successfully.", email);
            } else {
                resultText = String.format("User with email %s was not updated!", email);
            }
        } else {
            resultText = String.format("Could NOT find user by email %s for updating", email);
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
