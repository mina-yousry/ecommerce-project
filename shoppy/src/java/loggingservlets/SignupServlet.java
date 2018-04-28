/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loggingservlets;

import daosimplementation.UserDaoImpl;
import dtos.UserDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;

/**
 *
 * @author hoda.CO
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    private UserDaoImpl userDaoImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDaoImpl = new UserDaoImpl();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDto user = new UserDto();
        user.setFirstName(request.getParameter("fname"));
        user.setLastName(request.getParameter("lname"));
        user.setDob(request.getParameter("birth"));
        user.setPhone(request.getParameter("tele"));
        user.setMail(request.getParameter("email"));
        user.setJob(request.getParameter("job"));
        user.setPassword(request.getParameter("pass"));
        user.setAddress(request.getParameter("add"));
        System.out.println(user);
        System.out.println(userDaoImpl.create(user));

        
        response.sendRedirect("SignIn.jsp");

    }
}
