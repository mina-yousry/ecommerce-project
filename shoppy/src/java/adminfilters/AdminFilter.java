/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminfilters;

import dtos.UserDto;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usama
 */
// filter for user functions and to insure there is a session
@WebFilter(
        urlPatterns = {"/AddProduct", "/AdminEditUsersProfile", "/ChangeUserType", "/EditProduct", "/GetUserData", "/UpdateProduct",
           "/DeleteUser", "/EditProduct.jsp", "/Users.jsp", "/AddProduct.jsp"})

public class AdminFilter implements Filter {
    
    private FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter initialized");
        this.filterConfig = filterConfig;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        
        if (user == null) {
            res.sendRedirect("SignIn.jsp");
        } else {
            if (user.getType().equalsIgnoreCase("admin")) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect("Notification.jsp?n=Unauthorized Access");
            }
        }
    }
    
    public void destroy() {
        System.out.println("Filter destroyed");
        this.filterConfig = null;
    }
}
