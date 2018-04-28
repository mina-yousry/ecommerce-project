/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userfilters;

import dtos.UserDto;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoda.CO
 * @author Usama
 */
public class SignInFilter implements Filter {

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
        Cookie[] cookies = req.getCookies();
        String userEmail = null;
        String userPass = null;
        UserDto user = null;
        if (session != null) {
            user = (UserDto) req.getSession().getAttribute("user");
        }
        if (user == null) {
            if (cookies != null && cookies.length > 1) {
                for (Cookie c : cookies) {
                    if (c.getName().equalsIgnoreCase("userEmail")) {
                        userEmail = c.getValue();
                    }
                    if (c.getName().equalsIgnoreCase("userPass")) {
                        userPass = c.getValue();
                    }
                }
                if (userEmail != null && userPass != null) {

                    req.setAttribute("email", userEmail);
                    req.setAttribute("password", userPass);
                    System.out.println("Sign in filter 1");
                    req.getRequestDispatcher("/SigninServlet").forward(request, response);

                }
            }
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("Home.jsp");
        }
    }

    public void destroy() {
        System.out.println("Filter destroyed");
        this.filterConfig = null;
    }
}
