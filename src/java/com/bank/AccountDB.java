/*
 * Bank Project - Andrew Willhoit
 * AccountDB.java
 * Created: 4/12/14
 * Last Updated: 4/12/14
 */

package com.bank;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.apache.log4j.Logger;


public class AccountDB {
    
      private static final Logger logger = Logger.getLogger(AccountDB.class.getName());
      
      private static final String LOGIN_USER = "SELECT u.UserID, u.UserName, u.FirstName," +
                        " u.LastName, u.Password, u.Email, u.Active, a.AccountID, a.Balance, " + 
                        " a.LastModDate\n" +
                        "FROM Users u \n" +
                        "JOIN Account a\n" +
                        "ON u.UserID = a.UserID \n" +
                        "WHERE UserName = ?\n" +
                        "and Password = ?";
      
      
      public static Account login(String username, String password) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();
        Account account = null;
        PreparedStatement s = null;
           
          try {
              
              s = conn.prepareCall(LOGIN_USER);
              s.setString(1, username);
              s.setString(2, password);
              logger.info("login.query: " + LOGIN_USER);
              ResultSet rs = s.executeQuery();
              if(rs.next()) {
                  account = new Account();
                  account.setUserID(rs.getInt(1));
                  account.setUserName(rs.getString(2));
                  account.setFirstName(rs.getString(3));
                  account.setLastName(rs.getString(4));
                  account.setPassword(rs.getString(5));
                  account.setEmail(rs.getString(6));
                  //skipping 7, which should be active, which am not using yet.
                  account.setAcctID(rs.getInt(8));
                  account.setAmount(rs.getBigDecimal(9));
                  account.setLastDate(rs.getTimestamp(10));
                          
              }
                      
              
          } catch (SQLException e) {
              logger.error("Error logging in user: " + username, e);
          } finally {
            pool.closeStatement(s);
            pool.freeConnection(conn);
          }
              
  
          
          
          return account;
      } // end login(..)
      
      public static boolean usernameCheck(String username) 
      {          
          boolean userNameExists = false;
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection conn = pool.getConnection();
          
          int result = 0;          
          CallableStatement s = null;
          String check = "{ ? = call sp_UsernameCheck (?) }";
          
          try {
              s = conn.prepareCall(check);
              s.registerOutParameter(1, Types.INTEGER);
              s.setString(2, username);
            //  s.execute();
              
              ResultSet rs = s.executeQuery();
              while(rs.next())
              {
                  result = rs.getInt(1);
              }
                           
             if (result == 1) {
                 userNameExists = true;
             }
              
              System.out.println("RESULT: " + result);
              
          } catch (SQLException e) {
               logger.error("Error checking username uniqueness", e);
          }  finally  {
            pool.closeStatement(s);
            pool.freeConnection(conn);
          }          
          //return result;
          return userNameExists;
      } // end usernameCheck(.)
      
      public static boolean emailCheck(String email) 
      {          
          boolean emailExists = false;
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection conn = pool.getConnection();
          
          int result = 0;          
          CallableStatement s = null;
          String check = "{ ? = call sp_EmailCheck (?) }";
          
          try {
              s = conn.prepareCall(check);
              s.registerOutParameter(1, Types.INTEGER);
              s.setString(2, email);
            //  s.execute();
              
              ResultSet rs = s.executeQuery();
              while(rs.next())
              {
                  result = rs.getInt(1);
              }
                      
              if (result == 1) {
                  emailExists = true;
              }
             
              System.out.println("RESULT: " + result);
              
          } catch (SQLException e) {
               logger.error("Error checking email uniqueness", e);
          }  finally  {
            pool.closeStatement(s);
            pool.freeConnection(conn);
          }          
          //return result;
          return emailExists;
      } // end emailCheck(.)
      
      public static Account getAccount(int acctID)
      {
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection conn = pool.getConnection();
          
          Account account = null;
          CallableStatement s = null;
          String get = "{ ? = call sp_GetAcct (?) }";
          
          try {
              s = conn.prepareCall(get);
              s.registerOutParameter(1, Types.INTEGER);
              
              s.setInt(2, acctID);
              ResultSet rs = s.executeQuery();
              while(rs.next())
              {
                  account = new Account();
                  account.setUserID(rs.getInt(1));
                  account.setAcctID(rs.getInt(2));
                  account.setUserName(rs.getString(3));
                  account.setFirstName(rs.getString(4));
                  account.setLastName(rs.getString(5));
                  account.setEmail(rs.getString(6));
                  account.setAmount(rs.getBigDecimal(7));
                  account.setLastDate(rs.getTimestamp(8));
              }
              rs.close();
              s.close();
              
          } catch (SQLException e) {
              logger.error("Error getting account ", e);
          } finally {
            pool.closeStatement(s);
            pool.freeConnection(conn);
          }
          return account;
      } //end getAccoutn(.)
      
      
      public static void insertAccount(Account account)
      {
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection conn = pool.getConnection();
          
          CallableStatement s = null;
          String insert = "{ ? = call sp_CreateUser (?, ?, ?, ?, ?) }";
          
          try {
              s = conn.prepareCall(insert);
              s.registerOutParameter(1, Types.INTEGER);
              
              s.setString(2, account.getUserName());
              s.setString(3, account.getFirstName());
              s.setString(4, account.getLastName());
              s.setString(5, account.getPassword());
              s.setString(6, account.getEmail());
              
              s.execute();
              
              int accountID = s.getInt(1);
              account.setUserID(accountID);
              
              
          } catch (SQLException e) {
              logger.error("Error creating account", e);
          }  finally  {
            pool.closeStatement(s);
            pool.freeConnection(conn);
        }
        
     
      } //end insertAccount(.)
    
    public static int deposit(int acctID, BigDecimal amount) {
        int success = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        CallableStatement s = null;
        String insert = "{ ? = call sp_DepositToAccount (?, ?) }";
        try {
            s = conn.prepareCall(insert);
            s.registerOutParameter(1, Types.INTEGER);
            
            s.setInt(2, acctID);
            s.setBigDecimal(3, amount);
            
            s.execute();
            
            success = s.getInt(1);
            
        } catch (SQLException e) {
            logger.error("Error depositing into account", e);
        } finally {
            pool.closeStatement(s);
            pool.freeConnection(conn);
        }
        
        
        return success;
    }

        public static int withdraw(int acctID, BigDecimal amount) {
        int success = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        CallableStatement s = null;
        String insert = "{ ? = call sp_WithdrawFromAccount (?, ?) }";
        try {
            s = conn.prepareCall(insert);
            s.registerOutParameter(1, Types.INTEGER);
            
            s.setInt(2, acctID);
            s.setBigDecimal(3, amount);
            
            s.execute();
            
            success = s.getInt(1);
            
        } catch (SQLException e) {
            logger.error("Error withdrawing from account", e);
        } finally {
            pool.closeStatement(s);
            pool.freeConnection(conn);
        }
        
        
        return success;
    }
    
    
    
    
}
