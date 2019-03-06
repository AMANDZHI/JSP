package com.company.servlets;

import com.company.api.UserRepository;
import com.company.model.User;
import com.company.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserRepository userRepository = UserRepositoryImpl.getUserRepository();
        User user = userRepository.findByLogin(login);
        if (user != null) {
            resp.sendRedirect("/");
        } else {
            User newUser = new User(name, login, password);
            userRepository.save(newUser);

            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            resp.sendRedirect("/project");
        }
    }
}
