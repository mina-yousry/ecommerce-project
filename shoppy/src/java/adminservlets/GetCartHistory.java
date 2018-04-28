/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminservlets;

import daosimplementation.CartHistoryDaoImpl;
import daosimplementation.UserDaoImpl;
import dtos.CartDto;
import dtos.CartProductsDto;
import dtos.OnlineCartDto;
import dtos.UserDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "GetCartHistory", urlPatterns = {"/GetCartHistory"})
public class GetCartHistory extends HttpServlet {

      private CartHistoryDaoImpl cartDaoImpl;
      private UserDaoImpl userDaoImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        cartDaoImpl = new CartHistoryDaoImpl();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            List<OnlineCartDto> carts = cartDaoImpl.retreiveUserHistory(Integer.parseInt(request.getParameter("id")));    
           request.setAttribute("carts_list", carts);
           request.setAttribute("username", request.getParameter("n"));
    }
}
