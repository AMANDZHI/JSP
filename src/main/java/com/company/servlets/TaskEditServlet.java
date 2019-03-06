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

@WebServlet("/editTask")
public class TaskEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Task> taskRepository = TaskRepositoryImpl.getTaskRepository();
        String name = req.getParameter("name");
        Task task = taskRepository.findByName(name);
        req.setAttribute("task", task);
        req.getRequestDispatcher("WEB-INF/jsp/updateTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();
        Repository<String, Task> taskRepository = TaskRepositoryImpl.getTaskRepository();
        String oldName = req.getParameter("oldName");
        String newName = req.getParameter("newName");
        String newDescr = req.getParameter("newDescr");
        String newProject = req.getParameter("newProject");
        Project project = projectRepository.findByName(newProject);

        if (oldName.isEmpty()) {
            Task task = new Task(newName, newDescr, project);
            taskRepository.save(task);
        } else {
            Task task = taskRepository.findByName(oldName);
            task.setName(newName);
            task.setDescription(newDescr);
            task.setProject(project);
            taskRepository.update(task);
        }

        resp.sendRedirect("/task");
    }
}
