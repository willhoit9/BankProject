/*
 * Bank Project - Andrew Willhoit
 * WithdrawServlet.java
 * Created: 4/13/14
 * Last Updated: 4/13/14
 */

package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class WithdrawServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();

        boolean goodData = false;
        response.setContentType("text/html;charset=UTF-8");

        Account acct = (Account)session.getAttribute("acctSess");
        
        int acctID = acct.getAcctID();
        int userId = acct.getUserID();
        

      //  String acctWdString = request.getParameter("withdrawAmount");
        String acctWdString = request.getParameter("depAmount");
        acctWdString = acctWdString.replaceAll("[^\\d.-]", "");

        if ( acctWdString == null || acctWdString.isEmpty() ){
            response.setHeader("X-Message", "Withdraw amount is required and must be numeric.");
        }        

        else {
            
            BigDecimal withdraw = new BigDecimal(acctWdString);            
            Account account = AccountDB.getAccount(acctID);

            try {

                
                account.withdraw(withdraw);
                goodData = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                response.setHeader("X-Message", e.getMessage());

            }

            if (goodData) {

                AccountDB.withdraw(acctID, withdraw);

                request.setAttribute("transactions", TransactionDB.getTransactions(account.getUserID()));
                response.setHeader("X-Message", "success");

                request.getRequestDispatcher("/WEB-INF/transactionRows.jsp").forward(request, response);
              
            }

        }
        

    } //end processRequest(..)

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
