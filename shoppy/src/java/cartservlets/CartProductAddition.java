/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartservlets;

import daosimplementation.CartDaoImpl;
import daosimplementation.ProductDaoImpl;
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
 * @author Usama
 */
@WebServlet(name = "CartProductAddition", urlPatterns = {"/CartProductAddition"})
public class CartProductAddition extends HttpServlet {
    
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
        productsMap = Collections.synchronizedMap((Map<Integer, ProductDto>) servletContext.getAttribute("Products"));
        OnlineCartDto cart = ((OnlineCartDto) session.getAttribute("cart"));
        Integer addedProductId = Integer.parseInt(request.getParameter("addedProduct"));
        Integer addedProductQty = Integer.parseInt(request.getParameter("addedProductQty"));
        Integer cartProductQty = null;
        Integer totalPrice;
        ProductDto temp = null;
        ProductDaoImpl productDao = new ProductDaoImpl();
        
        if (session.getAttribute("cart") != null) {
            
            if (request.getParameter("addedProduct") != null && request.getParameter("addedProductQty") != null) {
                temp = productsMap.get(addedProductId);
                if (temp.getQuantityStock() - temp.getQuantityCart() > addedProductQty) {
                    if (cart.getProductMap().containsKey(addedProductId)) {
                        cartProductQty = cart.getProductMap().get(addedProductId);
                    cartProductQty += addedProductQty;
                    cart.addProduct(temp, addedProductQty);
                    }
                    else
                    {cartProductQty = addedProductQty;
                    cart.addProduct(temp, addedProductQty);}
                    temp.setQuantityCart(temp.getQuantityCart() + addedProductQty);
                } else {
                    
                    addedProductQty = productsMap.get(addedProductId).getQuantityStock() - productsMap.get(addedProductId).getQuantityCart();
                    
                    temp.setQuantityCart(temp.getQuantityCart() + addedProductQty);
                    
                    cart.addProduct(temp, addedProductQty);
                }
            } else {
                response.sendRedirect("Notification.jsp?n=Product+is+not+avaiable");
                return;
                
            }
            
        } else {
            cart = new OnlineCartDto();
            cart.setUserId(((UserDto) session.getAttribute("user")).getUserId());
            cart.setCartId(0); //how to create a unique identifier

            if (request.getParameter("addedProduct") != null && request.getParameter("addedProductQty") != null) {
                
                temp = productsMap.get(addedProductId);
                
                if (temp.getQuantityStock() - temp.getQuantityCart() < addedProductQty) {
                    
                    addedProductQty = temp.getQuantityStock() - temp.getQuantityCart();
                }
                temp.setQuantityCart(temp.getQuantityCart() + addedProductQty);
                cart.addProduct(temp, addedProductQty);
            }
            
        }
        
        new CartDaoImpl().delete(cart);
        new CartDaoImpl().create(cart);
        
        new ProductDaoImpl().updateQty(addedProductId, addedProductQty);
        
        productsMap.put(temp.getProductId(), temp);
        servletContext.setAttribute("Products", productsMap);
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getHeader("referer"));
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
