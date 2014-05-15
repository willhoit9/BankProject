/*
 * Bank Project - Andrew Willhoit
 * LoginServlett.java
 * Created: 4/12/14
 * Last Updated: 4/29/14
 */

package com.bank;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // added 4/20/14
        HttpSession session = request.getSession(); 
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username is: " + username);
        System.out.println("password is: " + password);

        
            if( (username == null || username.isEmpty()) ||
            (password == null || password.isEmpty()))
        {
            ServletConfig config = getServletConfig();
            String message = config.getInitParameter("failMessage");
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
            return;
        } else {
                Account account = AccountDB.login(username, password);
                if(account == null) {
                    request.setAttribute("message", "Wrong Username or Password");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
                else {
                    
                    
                    request.setAttribute("account", account);
                    request.setAttribute("transactions", TransactionDB.getTransactions(account.getUserID()));
                    
                    // added 4/20/14
                    session.setAttribute("acctSess", account);
                    
                }
            }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/myaccount.jsp");
        dispatcher.forward(request, response);
        
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
