/*
 * Bank Project - Andrew Willhoit
 * Account.java
 * Created: 4/8/14
 * Last Updated: 4/12/14
 */

package com.bank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;


public class Account implements Serializable {
    
    private int userID;
    private int acctID;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email; 
    private Calendar date;
    private Timestamp lastDate;
    // amount can never be a negative value
    private BigDecimal amount;
    private final BigDecimal maxDeposit = BigDecimal.valueOf(10000.0);
    
 
 //   private final BigDecimal maxDeposit1 = new BigDecimal(100000.0);

    public Account() {
        userID = 0;
        acctID = 0;
        userName = null;
        firstName = null;
        lastName = null;
        password = null;
        email = null;
        date = null;
        amount = BigDecimal.ZERO;
    }
    
    public Account(int userID, int acctID, String userName, String firstName, String lastName, String password, String email, Calendar date, BigDecimal amount) {
        this.userID = userID;
        this.acctID = acctID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.date = date;
        this.amount = amount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getMaxDeposit() {
        return maxDeposit;
    }

    public int getAcctID() {
        return acctID;
    }

    public void setAcctID(int acctID) {
        this.acctID = acctID;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Timestamp getLastDate() {
        return lastDate;
    }

    public void setLastDate(Timestamp lastDate) {
        this.lastDate = lastDate;
    }
    
    
    
    
 
    // is greater than 0
    // cannot be greater than 10,000
    public void deposit (BigDecimal dep) {
        int signum = dep.signum();
        if (signum == 0) {
            throw new IllegalArgumentException("Deposit Amount cannot be Zero.");
        } else  if (signum == -1) {
            throw new IllegalArgumentException("Deposit Amount cannot a Negative Amount.");
        } else if (dep.compareTo(maxDeposit) == 1) {
            throw new IllegalArgumentException("Deposit Amount cannot be greater than: " + maxDeposit + "." );           
        } else {

            this.setAmount(amount.add(dep));        
        }
                     
    }
    
    // is greater than 0
    // cannot be greater that current amount
    public void withdraw (BigDecimal wd) {
                int signum = wd.signum();
        if (signum == 0) {
            throw new IllegalArgumentException("Withdraw Amount cannot be Zero.");
        } else  if (signum == -1) {
            throw new IllegalArgumentException("Withdraw Amount cannot a Negative Amount.");
        } else if (wd.compareTo(amount) == 1) {
            // if (withdraw > than amount)
            throw new IllegalArgumentException("Withdraw Amount cannot greater than Account Balance.");           
        } else {

            this.setAmount(amount.subtract(wd));
        }
        
  
    }
 
    
    @Override
    public String toString() {
        return "Account{" + "userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email=" + email + ", amount=" + amount + '}';
    }
    
    
}
