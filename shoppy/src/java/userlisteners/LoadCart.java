/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userlisteners;

import daosimplementation.CartDaoImpl;
import daosimplementation.CartHistoryDaoImpl;
import daosimplementation.ProductDaoImpl;
import daosinterfaces.CartDaoInt;
import dtos.OnlineCartDto;
import dtos.ProductDto;
import dtos.UserDto;
import java.util.Collections;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Web application lifecycle listener.
 *
 * @author Omar
 */
@WebListener
public class LoadCart implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        String name = event.getName();
        HttpSession session = event.getSession();
        ServletContext context = session.getServletContext();

        if (name.equals("user")) {
            //retreive & set cart from database
            UserDto user = (UserDto) session.getAttribute("user");
            System.out.println("user add: " + user.getFirstName());
            OnlineCartDto cart = new CartDaoImpl().retrieve(user.getUserId());
            if (new CartDaoImpl().retrieve(user.getUserId()) != null) {
                if (cart.getUserId() != 0) {
                    System.out.println("cart: " + cart.getCartId() + cart.getTotalItems());
                    session.setAttribute("cart", cart);
                    //new CartDaoImpl().delete(cart);

                    //set quantity in context
                    if (context.getAttribute("Products") != null) {
                        Map<Integer, ProductDto> contextProductMap = Collections.synchronizedMap((Map<Integer, ProductDto>) context.getAttribute("Products"));
                        Map<Integer, Integer> productMap = cart.getProductMap();
                        for (int productId : productMap.keySet()) {
                            contextProductMap.get(productId).setQuantityCart(contextProductMap.get(productId).getQuantityCart() + productMap.get(productId));
                            new ProductDaoImpl().updateQty(productId, productMap.get(productId));
                        }

                        context.setAttribute("Products", contextProductMap);
                    }
                }
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
//        String name = event.getName();
//        HttpSession session = event.getSession();
//        ServletContext context = session.getServletContext();
//        
//        
//        if (name.equals("user")) {
//            //reset quantity in context
//            OnlineCartDto cart = (OnlineCartDto) session.getAttribute("cart");
//            Map<Integer, ProductDto> contextProductMap = Collections.synchronizedMap((Map<Integer, ProductDto>) context.getAttribute("Products"));
//            Map<Integer, Integer> productMap = cart.getProductMap();
//            
//            for (int productId : productMap.keySet()) {
//                contextProductMap.get(productId).setQuantityCart(contextProductMap.get(productId).getQuantityCart() + productMap.get(productId));
//                new ProductDaoImpl().updateQty(productId, productMap.get(productId) * -1);
//            }
//            
//            context.setAttribute("Products", contextProductMap);
//            
//            //save in date base
//            new CartHistoryDaoImpl().delete(cart);
//            new CartHistoryDaoImpl().create(cart);
//        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

    }
}
