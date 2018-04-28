/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggingservlets;

import daosimplementation.UserDaoImpl;
import dtos.UserDto;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoda.CO
 * @author Usama
 */
@WebServlet(name = "SigninServlet", urlPatterns = {"/SigninServlet"})
public class SigninServlet extends HttpServlet {

    private UserDaoImpl userDaoImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDaoImpl = new UserDaoImpl();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = null;
        String password = null;
        UserDto user = null;
        // if request comming from form 
        if (request.getParameter("email") != null && request.getParameter("password") != null) {
            userEmail = request.getParameter("email");
            password = request.getParameter("password");
            user = userDaoImpl.retrieve(userEmail);
            if (user != null) {
                if (password.equals(user.getPassword())) {
                    Cookie userEmailCookie = new Cookie("userEmail", userEmail);
                    Cookie userPassCookie = new Cookie("userPass", password);
                    userEmailCookie.setMaxAge(60 * 60 * 24 * 30);
                    userPassCookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(userEmailCookie);
                    response.addCookie(userPassCookie);
                    HttpSession session = request.getSession(true);
                    session.setMaxInactiveInterval(60 * 60 * 24 * 30);
                    session.setAttribute("user", user);
                    response.sendRedirect("Home.jsp");

                } else {
                    response.sendRedirect("SignIn.jsp?n=Wrong+Password");
                }
            } else {
                response.sendRedirect("SignIn.jsp?n=User+Not+Exist");
            }
        } // if request comming from Sign in filter
        else if (request.getAttribute("email") != null && request.getAttribute("password") != null) {
            System.out.println("Signin from filter");
            userEmail = (String) request.getAttribute("email");
            password = (String) request.getAttribute("password");
            user = userDaoImpl.retrieve(userEmail);
            if (user != null) {
                if (password.equals(user.getPassword())) {
                    Cookie userEmailCookie = new Cookie("userEmail", userEmail);
                    Cookie userPassCookie = new Cookie("userPass", password);
                    userEmailCookie.setMaxAge(60 * 60 * 24 * 30);
                    userPassCookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(userEmailCookie);
                    response.addCookie(userPassCookie);
                    HttpSession session = request.getSession(true);
                    session.setMaxInactiveInterval(60 * 60 * 24 * 30);
                    session.setAttribute("user", user);
                    response.sendRedirect("Home.jsp");

                } else {
                    response.sendRedirect("SignIn.jsp?n=Wrong+Password");
                }
            } else {
                response.sendRedirect("SignIn.jsp?n=User+Not+Exist");
            }

        }

    }
}
