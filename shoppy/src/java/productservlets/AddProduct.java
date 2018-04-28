/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productservlets;

import daosimplementation.ProductDaoImpl;
import dtos.ProductDto;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import imageservlets.ImageUpload;
import java.nio.file.AccessDeniedException;

/**
 *
 * @author Mina
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/addproduct"})
public class AddProduct extends HttpServlet {

    ServletConfig myConfig;

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
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        myConfig = config;
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

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        List<FileItem> items = null;
        File file = null;
        try {
            items = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            Logger.getLogger(ImageUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator<FileItem> iter = items.iterator();
        ArrayList<String> parameters = new ArrayList<>();
        while (iter.hasNext()) {
            FileItem item = iter.next();
            if (item.isFormField()) {
                String name = item.getFieldName();
                String value = item.getString();
                parameters.add(value);
            } else {

                if (!item.isFormField()) {
                    try {

                        file = new File(request.getServletContext().getRealPath("") + "/images/" + item.getName());
                        if (!item.getName().trim().equalsIgnoreCase("")) {
                            item.write(file);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ImageUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }

        Map<Integer, ProductDto> contextProducts = Collections.synchronizedMap((Map<Integer, ProductDto>) myConfig.getServletContext().getAttribute("Products"));
        if (!file.toPath().toString().equalsIgnoreCase(request.getServletContext().getRealPath("") + "\\images")) {
            try {
                byte[] image = Files.readAllBytes(file.toPath());

                product.setProductImage(image);
                file.delete();
            } catch (NoSuchFileException | AccessDeniedException e) {
                product.setProductImage(null);
            }
        } else {
            product.setProductImage(null);
        }

        product.setProductName(parameters.get(0));
        product.setDescription(parameters.get(1));
        product.setPrice(Double.parseDouble(parameters.get(2)));
        product.setQuantityStock(Integer.parseInt(parameters.get(3)));
        product.setVendor(parameters.get(4));
        product.setCategory(parameters.get(5));

        ProductDaoImpl productDao = new ProductDaoImpl();

        product.setProductId(productDao.getNewId());
        boolean addProductResult;
        addProductResult = productDao.create(product);
        String notification;
        if (addProductResult) {
            notification = "Product has been added succesfully";
            contextProducts.put(product.getProductId(), product);
        } else {

            notification = "Failed to add the product";
        }
        response.sendRedirect("Notification.jsp?n=" + notification);
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
