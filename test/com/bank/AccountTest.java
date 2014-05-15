/*
 * Bank Project - Andrew Willhoit
 * AccountTest.java
 * Created: 4/8/14
 * Last Updated: 4/8/14
 */

package com.bank;

import com.bank.Account;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class AccountTest {
    
    public AccountTest() {
    }
    
    // WITHDRAW TESTS
    @Test
    public void testWithdrawValidAmount() {
        // withdraw $200.00 from acct with $200.01 - should be valid
        System.out.println("testWithdrawValidAmount");
        BigDecimal validWithdraw = BigDecimal.valueOf(200.0);
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(200.01));
        acct.withdraw(validWithdraw);
        assertEquals(BigDecimal.valueOf(0.01), acct.getAmount());
    }
    
    @Test
    public void testWithdrawValidAmountAll() {
        // withdraw $200.00 from acct with $200.0 - should be valid
        System.out.println("testWithdrawValidAmountAll");
        BigDecimal validWithdraw = BigDecimal.valueOf(200.0);
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(200.00));
        acct.withdraw(validWithdraw);
        assertEquals(BigDecimal.valueOf(0.00), acct.getAmount());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testWithdrawOverWithdraw() {
        // withdrawing $200.00 from acct with $199.99 - should throw exception
        System.out.println("testWithdrawOverWithdraw");
        BigDecimal overWithdraw = BigDecimal.valueOf(200.0);
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(199.99));
        acct.withdraw(overWithdraw);        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testWithdrawOfZero() {
        // withdrawing $0 from acct with $200.00 - should throw exception
        System.out.println("testWithdrawOfZero");
        BigDecimal zeroWithdraw = BigDecimal.ZERO;
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(200.00));
        acct.withdraw(zeroWithdraw);        
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testWithdrawLessThanZero() {
        // withdrawing -$1.00 from acct with $200.00 - should throw exception
        System.out.println("testWithdrawLessThanZero");
        BigDecimal zeroWithdraw = BigDecimal.valueOf(-1.00);
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(200.00));
        acct.withdraw(zeroWithdraw);        
    }
    
    
    // DEPOSIT TESTS
    
    @Test
    public void testDepositValidAmount() {
        // deposit $10.00 into acct with $200.00 - should be valid
        System.out.println("testDepositValidAmount");
        BigDecimal validDeposit = BigDecimal.valueOf(200.0);
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(10.00));
        acct.deposit(validDeposit);
        assertEquals(BigDecimal.valueOf(210.0), acct.getAmount());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDepositLessThanZero() {
        // deposit -$1.00 into acct with $10.00 - should throw exception
        System.out.println("testDepositLessThanZero");
        BigDecimal negDeposit = BigDecimal.valueOf(-1.0);
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(10.00));
        acct.deposit(negDeposit);
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDepositOfZero() {
        // deposit $0.00 into acct with $10.00 - should throw exception
        System.out.println("testDepositOfZero");
        BigDecimal zeroDeposit = BigDecimal.ZERO;
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(10.00));
        acct.deposit(zeroDeposit);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDepositOver10000() {
        // deposit $10000.10 into acct with $10.00 - should throw exception
        System.out.println("testDepositOver10000");
        BigDecimal tenThousandDeposit = BigDecimal.valueOf(10000.10);
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(10.00));
        acct.deposit(tenThousandDeposit);
    }
    
    @Test
    public void testDepositOf10000() {
        // deposit $10000.00 into acct with $10.00 - should be valid
        System.out.println("testDepositOf10000");
        BigDecimal tenThousandDeposit = BigDecimal.valueOf(10000.00);
        Account acct = new Account();
        acct.setAmount(BigDecimal.valueOf(10.00));
        acct.deposit(tenThousandDeposit);
        
        assertEquals(BigDecimal.valueOf(10010.0), acct.getAmount());
    }
    

    
}
