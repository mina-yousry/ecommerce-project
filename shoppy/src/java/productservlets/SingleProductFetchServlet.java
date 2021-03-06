/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productservlets;

import daosimplementation.ProductDaoImpl;
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

/**
 *
 * @author Usama
 */
@WebServlet(name = "SingleProductFetchServlet", urlPatterns = {"/SingleProductFetchServlet"})
public class SingleProductFetchServlet extends HttpServlet {

    private ProductDto product;
    private Map<Integer, ProductDto> productsList;
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
        Integer productID = null;
        productsList = Collections.synchronizedMap((Map<Integer, ProductDto>) servletContext.getAttribute("Products"));
        if (productsList == null) {
            request.getRequestDispatcher("/ProductsServlet").include(request, response);
            productsList = Collections.synchronizedMap((Map<Integer, ProductDto>) servletContext.getAttribute("Products"));
        }
        if (request.getParameter("id") != null) {
            productID = Integer.parseInt(request.getParameter("id"));
            if (productsList.containsKey(productID)) {
                product = productsList.get(productID);
                request.setAttribute("singleProduct", product);
            }
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

        ProductDto product = new ProductDto();
        ProductDaoImpl productDao = new ProductDaoImpl();
        product = productDao.retrieveById(Integer.parseInt(request.getParameter("editedProduct")));
        request.setAttribute("editedProduct", product);

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
