package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.DAO;
import ru.javawebinar.topjava.dao.DAOImpl1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet{
    private static final Logger LOG = getLogger(MealServlet.class);
    private DAO dao = new DAOImpl1();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        LOG.debug("forming meal list...size=" + dao.getAllExceed().size());

        if(action.equalsIgnoreCase("list_meal")) {
            req.setAttribute("mealList", dao.getAllExceed());
            req.getRequestDispatcher("mealList.jsp").forward(req, resp);
        }
        else if(action.equalsIgnoreCase("delete")){
            Integer id = Integer.parseInt(req.getParameter("id"));
            dao.delete(dao.getById(id));
            LOG.debug("Запрос обработан! Удален meal#"+id);

            req.setAttribute("mealList", dao.getAllExceed());
            req.getRequestDispatcher("mealList.jsp").forward(req, resp);
        }
        else resp.setStatus(400);
    }
}
