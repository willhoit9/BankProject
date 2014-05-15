/*
 * Bank Project - Andrew Willhoit
 * CreateServlet.java
 * Created: 4/12/14
 * Last Updated: 4/12/14
 */

package com.bank;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        
        
        if( (username == null || username.isEmpty()) ||
            (password == null || password.isEmpty()) ||
            (firstName == null || firstName.isEmpty()) ||
            (lastName == null || lastName.isEmpty()) ||
            (email == null || email.isEmpty() ))
        {
            ServletConfig config = getServletConfig();
            String message = config.getInitParameter("createMessage");
          //  request.setAttribute("createMessage", message);
            response.setHeader("X-Message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        } else if ( AccountDB.usernameCheck(username) == true ) {   
            // if username exists already:
            
            String message = "Sorry, the Username: " + username + " is already taken";
          //  request.setAttribute("createMessage", message);
            response.setHeader("X-Message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
            
        } else if ( AccountDB.emailCheck(email) == true ) {   

            String message = "Sorry, the Email: " + email + " is already associated with an account";
         //   request.setAttribute("createMessage", message);
            response.setHeader("X-Message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
            
        }  else {
            Account account = new Account();
            account.setUserName(username);
            account.setPassword(password);
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setEmail(email);
            
            try {
                AccountDB.insertAccount(account);
            } catch (Exception e) {
                System.out.println(e.getCause() + "\n" + e.getStackTrace());
            }
            
            
            //int success = AccountDB.insertAccount(null)
        }
        
        response.setHeader("X-Message", "success");
        String message = "Your account was created. Please Log in.";
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    
    } // end ProcessRequest(..)

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
