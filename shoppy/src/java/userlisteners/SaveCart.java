/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userlisteners;

import daosimplementation.CartDaoImpl;
import daosimplementation.ProductDaoImpl;
import dtos.OnlineCartDto;
import dtos.ProductDto;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Omar
 */
@WebListener
public class SaveCart implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        HttpSession session = se.getSession();
        OnlineCartDto cart = (OnlineCartDto) session.getAttribute("cart");
        if (cart != null) //save cart in data base
        {
            new CartDaoImpl().delete(cart);

            new CartDaoImpl().create(cart);

            System.out.println("cart & user: " + cart.getCartId() + cart.getUserId());

            //restore the quantiny of the products in  the context & database
            ServletContext context = session.getServletContext();
            Map<Integer, ProductDto> contextProductMap = (Map<Integer, ProductDto>) context.getAttribute("Products");

            if (cart != null & contextProductMap != null) {
                Map<Integer, Integer> productMap = cart.getProductMap();
                for (int productId : productMap.keySet()) {
//                    contextProductMap.get(productId).setQuantityCart(contextProductMap.get(productId).getQuantityCart() + productMap.get(productId));
                    new ProductDaoImpl().updateQty(productId, productMap.get(productId) * -1);
                }
            }

        }

    }
}
