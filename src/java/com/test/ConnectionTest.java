/*
 * Bank Project - Andrew Willhoit
 * ConnectionTest.java
 * Created: 4/12/14
 * Last Updated: 4/12/14
 */


package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionTest {

    public static void main(String[] args) {
        Connection conn = null;
        try {

            String database = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=BankDB";
            conn = DriverManager.getConnection(database, "demo", "demo123");
            System.out.println("connected!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(conn != null
            
                )
{
try {
                    conn.close();
                    System.out.println("disconnected!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
