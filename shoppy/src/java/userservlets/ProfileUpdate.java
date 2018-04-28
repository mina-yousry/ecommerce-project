package userservlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import daosimplementation.UserDaoImpl;
import dtos.UserDto;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Omar
 */
@WebServlet(urlPatterns = {"/ProfileUpdate"})
public class ProfileUpdate extends HttpServlet {

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

        UserDto user = (UserDto) session.getAttribute("user");
        if (user != null
                && request.getParameter("delPP") == null) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            List<FileItem> items = null;
            File file = null;
            ArrayList<String> parameters = new ArrayList<>();
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException ex) {
                Logger.getLogger(ProfileUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            Iterator<FileItem> iter = items.iterator();
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
                            Logger.getLogger(ProfileUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }

            user.setFirstName(parameters.get(0));
            user.setLastName(parameters.get(1));
            user.setPhone(parameters.get(3));
            user.setDob(parameters.get(5));
            user.setJob(parameters.get(6));
            user.setMail(parameters.get(2));
            user.setPassword(parameters.get(7));
            user.setAddress(parameters.get(4));
            if (!file.toPath().toString().equalsIgnoreCase(request.getServletContext().getRealPath("").toString() + "\\images")) {
                try {
                    byte[] image = Files.readAllBytes(file.toPath());
                    user.setProfileImage(image);
                    file.delete();
                } catch (NoSuchFileException | AccessDeniedException e) {
                    user.setProfileImage(new UserDaoImpl().retrieve(user.getUserId()).getProfileImage());
                    file.delete();
                }
            } else {
                user.setProfileImage(new UserDaoImpl().retrieve(user.getUserId()).getProfileImage());
            }

            new UserDaoImpl().update(user);

            response.sendRedirect(request.getHeader("referer"));

        } else if (user != null
                && Integer.parseInt(request.getParameter("delPP")) == user.getUserId()) {
            user.setProfileImage(null);

            new UserDaoImpl().update(user);

            response.sendRedirect(request.getHeader("referer"));
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
