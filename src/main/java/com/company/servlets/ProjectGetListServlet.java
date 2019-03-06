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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/project")
public class ProjectGetListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();

        Map<String, Project> map = projectRepository.getMap();
        List<Project> projects = new ArrayList<>();
        for (Map.Entry<String, Project> pair: map.entrySet()) {
            projects.add(pair.getValue());
        }
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("WEB-INF/jsp/project.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Repository<String, Project> projectRepository = ProjectRepositoryImpl.getProjectRepository();

        Map<String, Project> map = projectRepository.getMap();
        List<Project> projects = new ArrayList<>();
        for (Map.Entry<String, Project> pair: map.entrySet()) {
            projects.add(pair.getValue());
        }
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("WEB-INF/jsp/project.jsp").forward(req, resp);
    }
}
