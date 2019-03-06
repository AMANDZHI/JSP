package com.company.servlets;

import com.company.api.Repository;
import com.company.model.Project;
import com.company.repository.ProjectRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeProject")
public class ProjectRemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();
        String name = req.getParameter("name");
        projectRepository.removeByName(name);
        resp.sendRedirect("/project");
    }
}
