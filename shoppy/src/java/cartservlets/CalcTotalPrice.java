/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartservlets;

import dtos.OnlineCartDto;
import dtos.ProductDto;
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
 * @author Usama
 */
@WebServlet(name = "CalcTotalPrice", urlPatterns = {"/CalcTotalPrice"})
public class CalcTotalPrice extends HttpServlet {

    private Map<Integer, ProductDto> productsMap;

    private Map<Integer, Integer> cartProductsMap;
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
        boolean flag = false;
        if (session.getAttribute("cart") != null) {
            double total = 0;
            productsMap = Collections.synchronizedMap((Map<Integer, ProductDto>) servletContext.getAttribute("Products"));
            OnlineCartDto cart = ((OnlineCartDto) session.getAttribute("cart"));
            cartProductsMap =  Collections.synchronizedMap(cart.getProductMap());
            for (Integer productId : cartProductsMap.keySet()) {
                if (productsMap.containsKey(productId)) {
                    total += cartProductsMap.get(productId) * productsMap.get(productId).getPrice();
                }
                else
                {
                   cart.removeProduct(productId);
                }

            }

            cart.setTotalPrice(total);
            session.setAttribute("cart", cart);
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
