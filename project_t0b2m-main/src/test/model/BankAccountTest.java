package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    BankAccount ba;

    @BeforeEach
    public void setUp(){
        ba = new BankAccount("Dhruv Ganesh", 25000.0, 75000.0, 90793209);
    }

    @Test
    public void testConstructor() {
        assertEquals(ba.getAccountName(),"Dhruv Ganesh");
        assertEquals(ba.getCheckingBalance(),25000.0);
        assertEquals(ba.getSavingsBalance(), 75000.0);
        assertEquals(ba.getAccountId(), 90793209);
    }

    @Test
    public void withdrawFromChecking1() {
        assertFalse(ba.withdrawFromChecking(50000.0));
    }

    @Test
    public void withdrawFromChecking2() {
        assertTrue(ba.withdrawFromChecking(5000.0));
        assertEquals(ba.getCheckingBalance(),20000.0);
    }

    @Test
    public void withdrawFromChecking3() {
        assertTrue(ba.withdrawFromChecking(5000.0));
        assertTrue(ba.withdrawFromChecking(10000.0));
        assertEquals(ba.getCheckingBalance(),10000.0);
    }

    @Test
    public void withdrawFromChecking4() {
        assertTrue(ba.withdrawFromChecking(5000.0));
        assertTrue(ba.withdrawFromChecking(10000.0));
        assertFalse(ba.withdrawFromChecking(20000.0));
        assertEquals(ba.getCheckingBalance(),10000.0);
    }

    @Test
    public void withdrawFromSavings1() {
        assertFalse(ba.withdrawFromSaving(100000.0));
    }

    @Test
    public void withdrawFromSavings2() {
        assertTrue(ba.withdrawFromSaving(50000.0));
        assertEquals(ba.getSavingsBalance(),25000.0);
    }

    @Test
    public void withdrawFromSavings3() {
        assertTrue(ba.withdrawFromSaving(50000.0));
        assertTrue(ba.withdrawFromSaving(10000.0));
        assertEquals(ba.getSavingsBalance(),15000.0);
    }

    @Test
    public void withdrawFromSavings4() {
        assertTrue(ba.withdrawFromSaving(50000.0));
        assertTrue(ba.withdrawFromSaving(10000.0));
        assertFalse(ba.withdrawFromSaving(20000.0));
        assertEquals(ba.getSavingsBalance(),15000.0);
    }

    @Test
    public void depositIntoChecking1() {
        assertTrue(ba.depositIntoChecking(15000.0));
        assertEquals(ba.getCheckingBalance(),40000.0);
    }

    @Test
    public void depositIntoChecking2() {
        assertFalse(ba.depositIntoChecking(-5000.0));
        assertEquals(ba.getCheckingBalance(),25000.0);
    }

    @Test
    public void depositIntoChecking3() {
        assertTrue(ba.depositIntoChecking(5000.0));
        assertTrue(ba.depositIntoChecking(15000.0));
        assertEquals(ba.getCheckingBalance(),45000.0);
    }

    @Test
    public void depositIntoChecking4() {
        assertTrue(ba.depositIntoChecking(5000.0));
        assertTrue(ba.depositIntoChecking(15000.0));
        assertFalse(ba.depositIntoChecking(-5000.0));
        assertEquals(ba.getCheckingBalance(),45000.0);
    }

    @Test
    public void depositIntoSavings1() {
        assertTrue(ba.depositIntoSaving(150000.0));
        assertEquals(ba.getSavingsBalance(),225000.0);
    }

    @Test
    public void depositIntoSavings2() {
        assertFalse(ba.depositIntoSaving(-50000.0));
        assertEquals(ba.getSavingsBalance(),75000.0);
    }

    @Test
    public void depositIntoSavings3() {
        assertTrue(ba.depositIntoSaving(50000.0));
        assertTrue(ba.depositIntoSaving(150000.0));
        assertEquals(ba.getSavingsBalance(),275000.0);
    }

    @Test
    public void depositIntoSavings4() {
        assertTrue(ba.depositIntoSaving(50000.0));
        assertTrue(ba.depositIntoSaving(150000.0));
        assertFalse(ba.depositIntoSaving(-50000.0));
        assertEquals(ba.getSavingsBalance(),275000.0);
    }

    @Test
    public void transferToSavings1() {
        assertTrue(ba.transferFromCheckingToSavings(20000));
        assertEquals(ba.getSavingsBalance(),95000.0);
        assertEquals(ba.getCheckingBalance(),5000.0);
    }

    @Test
    public void transferToSavings2() {
        assertFalse(ba.transferFromCheckingToSavings(30000));
        assertEquals(ba.getSavingsBalance(),75000.0);
        assertEquals(ba.getCheckingBalance(),25000.0);

    }

    @Test
    public void transferToSavings3() {
        assertTrue(ba.transferFromCheckingToSavings(10000));
        assertTrue(ba.transferFromCheckingToSavings(10000));
        assertEquals(ba.getSavingsBalance(),95000.0);
        assertEquals(ba.getCheckingBalance(),5000.0);

    }

    @Test
    public void transferToSavings4() {
        assertTrue(ba.transferFromCheckingToSavings(10000));
        assertTrue(ba.transferFromCheckingToSavings(10000));
        assertEquals(ba.getCheckingBalance(),5000);
        assertFalse(ba.transferFromCheckingToSavings(10000));
        assertEquals(ba.getSavingsBalance(),95000.0);
        assertEquals(ba.getCheckingBalance(),5000.0);

    }

    @Test
    public void transferToChecking1() {
        assertTrue(ba.transferFromSavingsToChecking(50000));
        assertEquals(ba.getCheckingBalance(),75000.0);
        assertEquals(ba.getSavingsBalance(),25000.0);
    }

    @Test
    public void transferToChecking2() {
        assertFalse(ba.transferFromSavingsToChecking(85000));
        assertEquals(ba.getSavingsBalance(),75000.0);
        assertEquals(ba.getCheckingBalance(),25000.0);
    }

    @Test
    public void transferToChecking3() {
        assertTrue(ba.transferFromSavingsToChecking(30000));
        assertTrue(ba.transferFromSavingsToChecking(30000));
        assertEquals(ba.getSavingsBalance(),15000.0);
        assertEquals(ba.getCheckingBalance(),85000.0);
    }

    @Test
    public void transferToChecking4() {
        assertTrue(ba.transferFromSavingsToChecking(30000));
        assertTrue(ba.transferFromSavingsToChecking(30000));
        assertEquals(ba.getCheckingBalance(),85000);
        assertFalse(ba.transferFromSavingsToChecking(100000));
        assertEquals(ba.getSavingsBalance(),15000.0);
        assertEquals(ba.getCheckingBalance(),85000.0);
    }

    @Test
    public void interestInCheckingCalculator1() {
        assertEquals(ba.interestPredictorInChecking(50000,1),51500);
    }

    @Test
    public void interestInCheckingCalculator2() {
        assertEquals(ba.interestPredictorInChecking(50000,2),53045);
    }

    @Test
    public void interestInSavingCalculator1() {
        assertEquals(ba.interestPredictorInSavings(50000,1),53000);
    }

    @Test
    public void interestInSavingCalculator2() {
        assertEquals(ba.interestPredictorInSavings(50000,2),56180);
    }

    @Test
    public void investmentChecker() {
        assertEquals(ba.bestInvestmentIdeas(),"");
    }

}