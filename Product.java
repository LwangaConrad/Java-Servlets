/*
 LWANGA CONRAD ARTHUR 1800721116 18/U/21116/PS
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
public class Product extends HttpServlet {

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
        int i = 0;
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Product</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Available Products</h1>");
            out.println("<form action = /Eshop/ShoppingCart method = POST>");
            out.println("<table width=500>");
            out.println("<tr> <td>Item No</td> <td>Item Name</td> <td>Buy</td> </tr>");
            Cookie cookies[] = request.getCookies();
            int visits = 0;
            for(Cookie cookie:cookies){
                if (cookie.getName().equals("counter")){
                    visits = Integer.parseInt(cookie.getValue());
                    visits++;
                    Cookie count = new Cookie("counter", visits+"");
                    response.addCookie(count);
                }
            }
            out.println(visits);
            if(visits == 0){
                Cookie count = new Cookie("counter", "0");
                count.setMaxAge(3600*10*24);
                response.addCookie(count);
            }
            int max = Integer.parseInt(getInitParameter("max"));
            int p = 0;
            ArrayList<String> hold = new ArrayList<>();
            if(visits % 8 == 0 || visits == 1 || visits == 0){
                Random rand = new Random();
                while(p < max){
                    String r = Integer.toString(rand.nextInt(20));
                    if(hold.contains(r)){
                        continue;
                    }
                    hold.add(r);
                    out.println("<tr> <td>"+(p+1)+"</td> <td>"+getInitParameter(r)+"</td> <td><input type=checkbox name=c"+p+" value="+getInitParameter(r)+"></td> </tr>");
                    session.setAttribute("s"+(p+1), getInitParameter(r));
                    p++;
                }
            }
            else
                while(p < max){
                    out.println("<tr> <td>"+(p+1)+"</td> <td>"+(String)session.getAttribute("s"+(p+1))+"</td> <td><input type=checkbox name=c"+p+" value="+(String)session.getAttribute("s"+(p+1))+"></td> </tr>");
                    p++;
                }
            out.println("</table>");
            out.println("<input type=submit value=submit>");
            out.println("</form>");
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
