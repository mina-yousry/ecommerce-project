/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartservlets;

import daosimplementation.CartHistoryDaoImpl;
import daosimplementation.ProductDaoImpl;
import daosimplementation.UserDaoImpl;
import dtos.OnlineCartDto;
import dtos.ProductDto;
import dtos.UserDto;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Omar
 */
@WebServlet(name = "Purchase", urlPatterns = {"/Purchase"})
public class Purchase extends HttpServlet {

    private Map<Integer, ProductDto> productsMap;

    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(false);

        OnlineCartDto cart = (OnlineCartDto) session.getAttribute("cart");

        if (cart != null) {
            Map<Integer, Integer> productMap = cart.getProductMap();
            productsMap = Collections.synchronizedMap((Map<Integer, ProductDto>) servletContext.getAttribute("Products"));

            for (Integer productId : productMap.keySet()) {
                if (!productsMap.containsKey(productId)) {
                    response.sendRedirect("Notification.jsp?n=Some+Products+Was+Deleted+From+Site");
                    return;
                }
            }

            double totalPrice = cart.getTotalPrice();

            UserDto user = (UserDto) session.getAttribute("user");

            if (user.getBalance() > totalPrice) {
                user.setBalance(user.getBalance() - totalPrice);
            }

            new UserDaoImpl().update(user);

            for (Integer productId : productMap.keySet()) {
                ProductDto tempProduct = new ProductDaoImpl().retrieveById(productId);
                if (tempProduct != null) {
                    int stockQty = tempProduct.getQuantityStock();
                    stockQty -= productMap.get(productId);
                    tempProduct.setQuantityStock(stockQty);
                    int cartQty = tempProduct.getQuantityCart();
                    cartQty -= productMap.get(productId);
                    tempProduct.setQuantityCart(cartQty);
                    new ProductDaoImpl().update(tempProduct);
                }
            }

            OnlineCartDto newCart = new OnlineCartDto();
            newCart.setUserId(user.getUserId());

            new CartHistoryDaoImpl().create(cart);

            session.setAttribute("cart", newCart);

            response.sendRedirect("Home.jsp");

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
