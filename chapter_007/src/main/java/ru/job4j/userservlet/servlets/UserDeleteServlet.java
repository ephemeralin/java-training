package ru.job4j.userservlet.servlets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public final class UserDeleteServlet extends HttpServlet {
    /**
     * User store.
     */
    private UserStore userStore;
    /**
     * Logger instance.
     */
    private Logger log;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String resultText;
        String email = request.getParameter("email");
        if (userStore.deleteByEmail(email)) {
            resultText = String.format("User deleted by email: %s", email);
        } else {
            resultText = String.format("User was not deleted by email: %s", email);
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
