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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserGetListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        UserRepository userRepository = UserRepositoryImpl.getUserRepository();
        Map<String, User> map = userRepository.getMap();
        List<User> users = new ArrayList<>();
        for (Map.Entry<String, User> pair: map.entrySet()) {
            users.add(pair.getValue());
        }
        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/jsp/user.jsp").forward(req, resp);
    }
}
