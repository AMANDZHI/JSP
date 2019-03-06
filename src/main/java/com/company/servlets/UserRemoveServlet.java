package com.company.servlets;

import com.company.api.UserRepository;
import com.company.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeUser")
public class UserRemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        UserRepository userRepository = UserRepositoryImpl.getUserRepository();
        String name = req.getParameter("name");
        userRepository.removeByLogin(name);
        resp.sendRedirect("/task");
    }
}
