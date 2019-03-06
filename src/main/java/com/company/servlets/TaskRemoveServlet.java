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

@WebServlet("/removeTask")
public class TaskRemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Task> taskRepository = TaskRepositoryImpl.getTaskRepository();
        String name = req.getParameter("name");
        taskRepository.removeByName(name);
        resp.sendRedirect("/task");
    }
}
