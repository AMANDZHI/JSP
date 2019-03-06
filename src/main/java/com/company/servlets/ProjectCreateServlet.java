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

@WebServlet("/createProject")
public class ProjectCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();
        UserRepository userRepository = UserRepositoryImpl.getUserRepository();
        String name = req.getParameter("name");
        String descr = req.getParameter("description");
        String login = req.getParameter("login");
        User user = userRepository.findByLogin(login);
        Project project = new Project(name, descr, user);
        projectRepository.save(project);
        resp.sendRedirect("/project");
    }
}
