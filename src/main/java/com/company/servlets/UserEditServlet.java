package com.company.servlets;

import com.company.api.UserRepository;
import com.company.model.User;
import com.company.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editUser")
public class UserEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        UserRepository userRepository = UserRepositoryImpl.getUserRepository();
        String login = req.getParameter("name");
        User user = userRepository.findByLogin(login);
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/jsp/updateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        UserRepository userRepository = UserRepositoryImpl.getUserRepository();
        String oldLogin = req.getParameter("oldLogin");
        String newName = req.getParameter("newName");
        String newLogin = req.getParameter("newLogin");
        String newPassword = req.getParameter("newPassword");

        if (oldLogin.isEmpty()) {
            User user = new User(newName, newLogin, newPassword);
            userRepository.save(user);
        } else {
            User user = userRepository.findByLogin(oldLogin);
            user.setName(newName);
            user.setLogin(newLogin);
            user.setPassword(newPassword);
            userRepository.update(user);
        }

        resp.sendRedirect("/user");
    }
}
