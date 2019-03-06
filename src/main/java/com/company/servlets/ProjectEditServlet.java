package com.company.servlets;

import com.company.api.Repository;
import com.company.api.UserRepository;
import com.company.model.Project;
import com.company.model.User;
import com.company.repository.ProjectRepositoryImpl;
import com.company.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editProject")
public class ProjectEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();
        String name = req.getParameter("name");
        Project project = projectRepository.findByName(name);
        req.setAttribute("project", project);
        req.getRequestDispatcher("WEB-INF/jsp/updateProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();
        UserRepository userRepository = UserRepositoryImpl.getUserRepository();
        String oldName = req.getParameter("oldName");
        String newName = req.getParameter("newName");
        String newDescr = req.getParameter("newDescr");
        String newLogin = req.getParameter("newLogin");
        User user = userRepository.findByLogin(newLogin);
        if (oldName.isEmpty()) {
            Project project = new Project(newName, newDescr, user);
            projectRepository.save(project);
        } else {
            Project project = projectRepository.findByName(oldName);
            project.setName(newName);
            project.setDescription(newDescr);
            project.setUser(user);
            projectRepository.update(project);
        }

        resp.sendRedirect("/project");
    }
}
