package ru.job4j.carplace.controller;

import com.google.gson.Gson;
import ru.job4j.carplace.model.dao.ModelDAO;
import ru.job4j.carplace.model.entity.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Model controller.
 */
@lombok.extern.log4j.Log4j2
@WebServlet(
        name = "ModelController",
        description = "Add model servlet",
        urlPatterns = {"/models"}
)
public class ModelController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int makeId = Integer.parseInt(request.getParameter("make_id"));
        List<Model> modelsList = ModelDAO.getInstance().getModelsByMakeId(makeId);
        List<HashMap<String, String>> models = new ArrayList<>();
        for (Model model : modelsList) {
            final HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(model.getId()));
            map.put("name", model.getName());
            models.add(map);
        }
        response.setContentType("text/json");
        String s = new Gson().toJson(modelsList);
        response.getWriter().print(s);
        response.getWriter().flush();
    }
}
