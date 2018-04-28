package imageservlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import daosimplementation.UserDaoImpl;
import dtos.ProductDto;
import dtos.UserDto;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;
import javax.imageio.ImageIO;
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
@WebServlet(urlPatterns = {"/GetImage"})
public class GetImage extends HttpServlet {

    private ServletContext servletContext;
    private String noProductImg = "\\images\\NoProduct.png";

    private String noProfileImg = "\\images\\NoProfilePic.png";
    private Map<Integer, ProductDto> productsList;

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
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpg");
        if (request.getParameter("id") != null) {
            Integer productID = Integer.parseInt(request.getParameter("id"));
            if (servletContext.getAttribute("Products") != null) {
                productsList = Collections.synchronizedMap((Map<Integer, ProductDto>) servletContext.getAttribute("Products"));
            }

            if (productsList.containsKey(productID)) {

                if (productsList.get(productID).getProductImage() != null) {
                    response.setContentLength(productsList.get(productID).getProductImage().length);
                    response.getOutputStream().write(productsList.get(productID).getProductImage());
                    response.getOutputStream().close();
                } else {
                    File f = new File(request.getServletContext().getRealPath("") + noProductImg);
                    BufferedImage bi = ImageIO.read(f);
                    OutputStream out = response.getOutputStream();
                    ImageIO.write(bi, "jpg", out);
                    out.close();
                }

            } else {
                File f = new File(request.getServletContext().getRealPath("") + noProductImg);
                BufferedImage bi = ImageIO.read(f);
                try (OutputStream out = response.getOutputStream()) {
                    ImageIO.write(bi, "jpg", out);
                }
            }
        } else if (request.getParameter("idU") != null) {
            byte[] img;

            if (request.getParameter("idU").equals("getpp")) {
                img = ((UserDto) request.getSession(false).getAttribute("user")).getProfileImage();

            } else {
                UserDto user = new UserDaoImpl().retrieve(request.getParameter("idU"));
                img = user.getProfileImage();
            }

            if (img != null && img.length > 0) {
                response.setContentLength(img.length);
                response.getOutputStream().write(img);
                response.getOutputStream().close();
            } else {
                File f = new File(request.getServletContext().getRealPath("") + noProfileImg);
                BufferedImage bi = ImageIO.read(f);
                try (OutputStream out = response.getOutputStream()) {
                    ImageIO.write(bi, "jpg", out);
                }

            }
        }
    }

}
