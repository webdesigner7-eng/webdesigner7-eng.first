/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pk1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
/**
 *
 * @author PC
 */
public class CopyCart extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
          int flag=0;
          String myCookieValue=null;
          Cookie cookie=null;
          HttpSession session=request.getSession();
        ArrayList ar=(ArrayList) session.getAttribute("mycart");
        out.println("newly added elements"+ar);
        String uid=(String)session.getAttribute("uid");
        String pid=request.getParameter("pid");
        String spids=null;
        Cookie ck1 = null;
        
if(ar==null){
   ar=new ArrayList();
    session.setAttribute("mycart", ar);
}
ar.add(pid);

for(int i=0;i<ar.size();i++){
  spids=spids+","+ar.get(i);
}
out.println(spids);
 Cookie ck[]=request.getCookies();

if(ck!=null){
    for(int i=0;i<ck.length;i++){
         ck1=ck[i];
        if(ck1.getName().equals(uid)){
             myCookieValue=ck1.getValue();
            flag=1;
        }
        }
    }  

if(flag==0){
       ck1= new Cookie(uid," ");
       ck1.setMaxAge(86400);
       response.addCookie(ck1);
}   

    ck1.setValue(myCookieValue+","+spids);
    ck1.setMaxAge(86400);
    response.addCookie(ck1);
out.println(ck1.getValue()+uid);
response.sendRedirect("trial.jsp");
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
