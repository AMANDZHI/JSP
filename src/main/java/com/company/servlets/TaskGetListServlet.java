package com.company.servlets;

import com.company.api.Repository;
import com.company.model.Task;
import com.company.repository.TaskRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/task")
public class TaskGetListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Task> taskRepository = TaskRepositoryImpl.getTaskRepository();
        Map<String, Task> map = taskRepository.getMap();
        List<Task> tasks = new ArrayList<>();
        for (Map.Entry<String, Task> pair: map.entrySet()) {
            tasks.add(pair.getValue());
        }
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("WEB-INF/jsp/task.jsp").forward(req, resp);
    }
}
