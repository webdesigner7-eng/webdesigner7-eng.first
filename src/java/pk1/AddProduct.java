/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pk1;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author PC
 */
@WebServlet("/FileUpload")
@MultipartConfig(fileSizeThreshold=1024*1024*10,maxFileSize=1024*1024*50,maxRequestSize=1024*1024*100)
public class AddProduct extends HttpServlet  {
      AddProduct ap=null;
   int count;
    int flag=0;
  void count(int count){
      this.count=count;
  }
   int setCount(){
      
       return count++;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           String cate=request.getParameter("cate");
           String style=request.getParameter("style");
           String size=request.getParameter("size");
           String brand=request.getParameter("brand");
           String desc=request.getParameter("desc");
           String price=request.getParameter("price");
           String dis=request.getParameter("discount");
          
           String uid=(String) request.getSession().getAttribute("uid");
           if(flag==0){
           int count=(int) request.getSession().getAttribute("cnt");
           count(count);
           flag=1;
           }
         
         setCount();
           String pid=uid+count;
          
            
           
             InputStream is=null;
            Part filePart=request.getPart("img");
            if(filePart!=null){
                out.println(filePart.getName());
                out.println(filePart.getSize());
                  System.out.println(filePart.getContentType());
                  is=filePart.getInputStream();
                 if(is==null)
                     out.println("Yes i m null");
            }
           Connection con=(Connection) request.getServletContext().getAttribute("mycon");
           out.println(con);
           String qry="insert into products values(?,?,?,?,?,?,?,?,?,?)";
          
           PreparedStatement ps=con.prepareStatement(qry);
           out.println(ps);
           ps.setString(1,uid);
           ps.setString(2,pid);
           ps.setString(3,cate);
           ps.setString(4,style);
           ps.setString(5,size);
           ps.setString(6,brand);
           ps.setString(7,desc);
           ps.setString(8,price);
           ps.setString(9,dis);
         
          
             if(is!=null){
                   out.println("in if");
                ps.setBlob(10, is);
                out.println("in if");
            }
    out.println(ps);
           request.getSession().setAttribute("cnt", count);
           int r= ps.executeUpdate();
        
            request.getSession().setAttribute("idu",uid);
           out.println("hii");
           if(r==0){
               request.setAttribute("m","error");
                RequestDispatcher rd=request.getRequestDispatcher("result.jsp");
          rd.forward(request,response);
           }
           else{
              request.setAttribute("m","succesfull"); 
                RequestDispatcher rd=request.getRequestDispatcher("result.jsp");
          rd.forward(request,response);
           }
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
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
              Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
              Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
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
