package com.company.servlets;

import com.company.api.UserRepository;
import com.company.model.User;
import com.company.repository.UserRepositoryImpl;
import com.company.util.Encryption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserRepository userRepository = UserRepositoryImpl.getUserRepository();
        User user = userRepository.findByLogin(login);
        if (user == null) {
            resp.sendRedirect("/");
        } else {
            if (user.getPassword().equals(Encryption.md5Custom(password))) {
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                session.setAttribute("password", password);
                resp.sendRedirect("/project");
            } else {
                resp.sendRedirect("/");
            }
        }
    }
}
