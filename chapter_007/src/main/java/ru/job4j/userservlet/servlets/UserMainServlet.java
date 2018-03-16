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
import java.util.List;

/**
 * The type Crud servlet.
 */
public final class UserMainServlet extends HttpServlet {
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
        String resultText;
        List<User> allUsers = userStore.getAll();
        if (allUsers.isEmpty()) {
            resp.setStatus(404);
            resultText = "There is no users in DB";
        } else {
            resp.setStatus(200);
            final StringBuilder table = new StringBuilder("<table style='width:70%'>");
            table.append("<tr><th>Name</th><th>Login</th><th>E-mail</th><th>Edit</th><th>Delete</th>");
            for (User user : allUsers) {
                String editFormText = String.format("" +
                        "<form action='%s/update' method = 'get'>" +
                        "<input type='submit' value='Edit'>" +
                        "<input type='hidden' name='email' value='%s'/>" +
                        "</form>", req.getContextPath(), user.getEmail());
                String deleteFormText = String.format("" +
                        "<form action='%s/delete' method = 'post'>" +
                        "<input type='submit' value='Delete'>" +
                        "<input type='hidden' name='email' value='%s'/>" +
                        "</form>", req.getContextPath(), user.getEmail());
                table.append("<tr>");
                table.append("<td>").append(user.getName()).append("</td>");
                table.append("<td>").append(user.getLogin()).append("</td>");
                table.append("<td>").append(user.getEmail()).append("</td>");
                table.append("<td>").append(editFormText).append("</td>");
                table.append("<td>").append(deleteFormText).append("</td>");
                table.append("</tr>");
            }
            table.append("</table>");
            resultText = table.toString();
        }
        String outputText =
                "<!DOCTYPE html>" +
                        "<html lang='en'>" +
                        "<head>" +
                        "   <meta charset='UTF-8'>" +
                        "   <title>Main USERS page</title>" +
                        "   <style>" +
                        "       table, th, td {" +
                        "           border: 1px solid black;" +
                        "           border-collapse: collapse;" +
                        "       }" +
                        "       th, td {" +
                        "           padding: 7px;" +
                        "       }" +
                        "   </style>" +
                        "</head>" +
                        "<body>" +
                        "   <br>" +
                            resultText +
                        "   <br>" +
                        "   <form action='create' method = 'get'>" +
                        "       <input type='submit' value='Create new'>" +
                        "   </form>" +
                        "</body>" +
                        "</html>";
        outputInfo(resp, outputText);
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
