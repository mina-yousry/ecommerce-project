/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userservlets;

import daosimplementation.UserDaoImpl;
import dtos.UserDto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoda.CO
 */
@WebServlet(name = "balance", urlPatterns = {"/balance"})
public class balance extends HttpServlet {

    private UserDaoImpl userDaoImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDaoImpl = new UserDaoImpl();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDto user = new UserDto();
        HttpSession session = request.getSession(false);
        user = (UserDto) session.getAttribute("user");
        System.out.println(user);
        String code = request.getParameter("code");
        if (code.equals("123-456-789")) {
            double oldBalance = user.getBalance();
            user.setBalance(oldBalance + 500);
            new UserDaoImpl().update(user);
            response.sendRedirect("Cart.jsp?ch=true&balance=500");
        } else if (code.equals("789-456-123")) {
            double oldBalance = user.getBalance();
            user.setBalance(oldBalance + 1000);
            new UserDaoImpl().update(user);
            response.sendRedirect("Cart.jsp?ch=true&balance=1000");
        } else {
            response.sendRedirect("Cart.jsp?ch=false");
        }

    }
}
