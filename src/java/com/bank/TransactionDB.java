/*
 * Bank Project - Andrew Willhoit
 * TransactionDB.java
 * Created: 4/13/14
 * Last Updated: 4/13/14
 */

package com.bank;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class TransactionDB {
    
    private static final Logger logger = Logger.getLogger(TransactionDB.class.getName());
    
    public static List<Transaction> getTransactions(int userID)
      {
          ConnectionPool pool = ConnectionPool.getInstance();
          Connection conn = pool.getConnection();
          List<Transaction> transactions = new LinkedList<Transaction>();
          CallableStatement s = null;
          String insert = "{ ? = call sp_GetTransactionsFromUserID (?) }";
          
          try {

              s = conn.prepareCall(insert);
              s.registerOutParameter(1, Types.INTEGER);
              
              s.setInt(2, userID);
   
              //s.execute();
              
              ResultSet rs = s.executeQuery();
              while(rs.next())
              {
                  transactions.add(bindResultSet(rs));
              }
              rs.close();
              s.close();
             // int accountID = s.getInt(1);
             
  
          } catch (SQLException e) {
              logger.error("Error getting transaction records", e);
          }  finally  {
            pool.closeStatement(s);
            pool.freeConnection(conn);
          }
          
          return transactions;
     
      } //end insertAccount(.)

    private static Transaction bindResultSet(ResultSet rs) throws SQLException {
       Transaction t = new Transaction();
       t.setTransDate(rs.getTimestamp(1));
       t.setTransType(rs.getString(2));
       t.setTransAmount(rs.getBigDecimal(3));
       t.setTransRemaining(rs.getBigDecimal(4));     
    
       return t;
    }
    
    
    
    
    
    
    
}
