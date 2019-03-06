package com.company.servlets;

import com.company.api.Repository;
import com.company.model.Project;
import com.company.model.Task;
import com.company.repository.ProjectRepositoryImpl;
import com.company.repository.TaskRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createTask")
public class TaskCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Task> taskRepository = TaskRepositoryImpl.getTaskRepository();
        Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();
        String name = req.getParameter("name");
        String descr = req.getParameter("description");
        String nameProject = req.getParameter("nameProject");
        Project project = projectRepository.findByName(nameProject);
        Task task = new Task(name, descr, project);
        taskRepository.save(task);
        resp.sendRedirect("/task");
    }
}
