/*
 * Bank Project - Andrew Willhoit
 * ConnectionPool.java
 * Created: 4/12/14
 * Last Updated: 4/12/14
 */

package com.bank;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * Taken from Page 467.
 * 
 */
public class ConnectionPool 
{
    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    private ConnectionPool()
    {
        try
        {
            //Get a reference to JDNI, this is done using the InitialContext class
            InitialContext ic = new InitialContext();
            
            //Obtain the javax.sql.DataSource Tomcat created using the <Resource> tag.
            //JDNI can be thought of as a folder structure.
            //Tomcat puts everything under "java:/comp/env"
            //The datasource we created was named "jdbc/userdemo"
            //The full path would then be "java:/comp/env/jdbc/userdemo"
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/bank");
            
        } catch (Exception e)
        {
            logger.error("Error retrieving DataSource from JDNI.", e);
        }
    }
        
    public static ConnectionPool getInstance()
    {
        if(pool == null)
        {
            pool = new ConnectionPool();
        }
        return pool;
    }
    
    public Connection getConnection()
    {
        try
        {
            return dataSource.getConnection();
        } catch (SQLException e)
        {
            logger.error("Error getting connection from DataSource", e);
            return null;
        }
    }
    
    public void freeConnection(Connection c)
    {
        try
        {
            c.close();
        } catch (SQLException e)
        {
            logger.error("Error closing connection", e);
        }
    }
    
    public void closeStatement(Statement s)
    {
        if(s != null)
        {
            try
            {
                s.close();
            }
            catch(SQLException e)
            {
                logger.error("Error closing statement", e);
            }
        }
    }
}