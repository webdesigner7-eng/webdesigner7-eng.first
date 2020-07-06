/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pk1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC
 */
public class AddUser extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             
            int check=0;
            String msg=null;
            String name=(String) request.getParameter("uname");
            String email=request.getParameter("email");
            String pass=request.getParameter("pass");
            String category=request.getParameter("category");
            String hno=request.getParameter("hno");
            String barea=request.getParameter("area");
            String bcity=request.getParameter("city");
            String bstate=request.getParameter("state");
            String mobno=request.getParameter("mobno");
            String id[]=email.split("@");
            id[0]=id[0]+pass;
            Connection con=(Connection) request.getServletContext().getAttribute("mycon");
              out.println(con);
              out.println("hiii");
              
            if(category.equals("seller")){
                  out.println("hiii");
             String sname=request.getParameter("sname");
             String state=request.getParameter("state");
             String city=request.getParameter("city");
             String area=request.getParameter("area");
             String number=request.getParameter("number");  
                
            String qry="Insert into sellers values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(qry);
               out.println("hiii");
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,pass);
            ps.setString(4,sname);
            ps.setString(5,state);
            ps.setString(6,city);
            ps.setString(7,area);
            ps.setString(8,number);
            ps.setString(9, id[0]);
             ps.setInt(10, 0);
            // ps.setString(10,"yes");
            // out.println("done");
            check=ps.executeUpdate();
            
            out.println(check);
            }
            else{
                  out.println("hiii");
                 String qry="Insert into buyers values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(qry);
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,pass);
            ps.setString(4,id[0]);
            ps.setString(5,hno);
            ps.setString(6,barea);
            ps.setString(7,bcity);
            ps.setString(8,bstate);
            ps.setString(9,mobno);
            check=ps.executeUpdate();
            }
           if(check==0){
               msg="Not Registered...";
                RequestDispatcher rd=request.getRequestDispatcher("signup.jsp");
                rd.forward(request,response);
           }
            request.getSession().setAttribute("msg",msg);
              request.getSession().setAttribute("uid",id[0]);
           RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
          rd.forward(request,response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
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
