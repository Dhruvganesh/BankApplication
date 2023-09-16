package persistence;

import model.BankAccount;
import model.BankServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BankAccount ba = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyServer.json");
        try {
            BankAccount ba = reader.read();
            assertEquals("Dhruv", ba.getAccountName());
            assertEquals(90793209, ba.getAccountId());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccount() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralServer.json");
        try {
            BankAccount ba = reader.read();
            assertEquals("Dhruv",  ba.getAccountName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
