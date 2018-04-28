/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminservlets;

import daosimplementation.UserDaoImpl;
import dtos.UserDto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hoda.CO
 */
@WebServlet(name = "GetUserData", urlPatterns = {"/GetUserData"})
public class GetUserData extends HttpServlet {

    private UserDaoImpl userDaoImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDaoImpl = new UserDaoImpl();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            List<UserDto> users = userDaoImpl.retrieveall();
            request.setAttribute("users_list", users);
        } else {
            UserDto user = userDaoImpl.retrieve(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("editedUser", user);
        }

//        RequestDispatcher diss = request.getRequestDispatcher("Users.jsp");
//        diss.forward(request, response);
    }
}
