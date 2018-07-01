package ru.job4j.carplace.controller;

import com.google.gson.Gson;
import ru.job4j.carplace.model.dao.CarDAO;
import ru.job4j.carplace.model.dao.ModelDAO;
import ru.job4j.carplace.model.entity.Car;
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
 * The Engine controller.
 */
@WebServlet(
        name = "EngineController",
        description = "Engine servlet",
        urlPatterns = {"/engines"}
)
public class EngineController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> carsList = CarDAO.getInstance().findAll();
        request.setAttribute("carsList", carsList);
        request.getRequestDispatcher("/WEB-INF/view/cars.jsp").forward(request, response);
        int engineId = Integer.parseInt(request.getParameter("engine_id"));
        List<Model> modelsList = ModelDAO.getInstance().getModelsByMakeId(engineId);
        List<HashMap<String, String>> models = new ArrayList<>();
        for (Model model : modelsList) {
            final HashMap<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(model.getId()));
            map.put("name", model.getName());
            models.add(map);
        }
        response.setContentType("text/json");
        String s = new Gson().toJson(modelsList);
        System.out.println(s);
        response.getWriter().print(s);
        response.getWriter().flush();
    }
}
