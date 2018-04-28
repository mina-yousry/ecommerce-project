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
 * Add product list to request
 *
 * @author Usama
 */
@WebServlet("/ProductsServlet")
public class ProductsServlet extends HttpServlet {

    private Map<Integer, ProductDto> productsList;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // until dao implementation is ready
        ProductDaoImpl products = new ProductDaoImpl();
        productsList = Collections.synchronizedMap(products.getAllProducts());
        servletContext.setAttribute("Products", productsList);

    }
}
