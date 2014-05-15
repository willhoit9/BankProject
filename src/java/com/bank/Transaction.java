/*
 * Bank Project - Andrew Willhoit
 * Transaction.java
 * Created: 4/13/14
 * Last Updated: 4/13/14
 */
package com.bank;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class Transaction {
    
    private int transactionID;
    private int transUserID;
    private int transAccountID;    
    private BigDecimal transAmount;
    private BigDecimal transRemaining;
    private String transType;   
    private Timestamp transDate;

    public Transaction() {
    }

    
    
    
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getTransUserID() {
        return transUserID;
    }

    public void setTransUserID(int transUserID) {
        this.transUserID = transUserID;
    }

    public int getTransAccountID() {
        return transAccountID;
    }

    public void setTransAccountID(int transAccountID) {
        this.transAccountID = transAccountID;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public BigDecimal getTransRemaining() {
        return transRemaining;
    }

    public void setTransRemaining(BigDecimal transRemaining) {
        this.transRemaining = transRemaining;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Timestamp getTransDate() {
        return transDate;
    }

    public void setTransDate(Timestamp transDate) {
        this.transDate = transDate;
    }
    
    
    
    
}
