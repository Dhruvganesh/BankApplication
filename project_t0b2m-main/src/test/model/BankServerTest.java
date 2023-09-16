package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankServerTest {

    private BankServer bs;

    @BeforeEach
    public void runBefore() {
        bs = new BankServer();
    }

    @Test
    public void testConstructor() {
        assertEquals(bs.getAccounts().size(),0);
    }

    @Test
    public void testInitialAccounts() {
        bs.initialAccounts();
        assertEquals(bs.getAccounts().size(),3);
    }

    @Test
    public void testGetAccounts() {
        bs.initialAccounts();
        assertEquals(bs.getAccounts().size(),3);
    }

    @Test
    public void testAddAccounts() {
        BankAccount ba2 = new BankAccount("Aryan Bhandari", 3000.0, 50000.0, 8328740);
        bs.addAccount(ba2);
        assertEquals(bs.getAccounts().size(),1);
    }

    @Test
    public void testAddAccounts2() {
        BankAccount ba2 = new BankAccount("Aryan Bhandari", 3000.0, 50000.0, 8328740);
        BankAccount ba3 = new BankAccount("Gurjas Singh", 35000.0, 56000.0, 73262344);
        bs.addAccount(ba2);
        bs.addAccount(ba3);
        assertEquals(bs.getAccounts().size(),2);
    }

}
