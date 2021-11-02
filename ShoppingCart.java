/*
 * LWANGA CONRAD ARTHUR 1800721116 18/U/21116/PS
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.util.*;

/**
 *
 * @author conrad
 */
public class ShoppingCart extends HttpServlet {

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
        int loo = 0, visit = 0, i = 1;
        Enumeration e = request.getParameterNames();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingCart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>ShoppingCart</h1>");
            Cookie list[] = request.getCookies();
            while(loo < list.length){
                if (list[loo].getName().equals("counter")){
                    visit = Integer.parseInt(list[loo].getValue());
                }
                loo++;
            }
            if (visit < 9){
                out.println("<h1>Guest</h1>");
            }
            else if(visit >= 9 && visit < 15){
                out.println("<h1>Bronze</h1>");
            }
            else if(visit >= 15 && visit < 30){
                out.println("<h1>Silver</h1>");
            }
            else
                out.println("<h1>Gold</h1>");
            out.println("<h2>Items selected</h2>");
            out.println("<ul>");
            while(e.hasMoreElements()){
                String items = (String)e.nextElement();
                if(items.equals("c1") || items.equals("c2") || items.equals("c3") || items.equals("c4") || items.equals("c0")){
                    out.println("<li>"+(String)request.getParameter(items)+"</li>");
                }
                i++;
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
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
