package persistence;

import model.BankAccount;
import model.BankServer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            BankAccount ba = new BankAccount("Dhruv", 0.0, 0.0, 90793209);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyServer() {
        try {
            BankAccount ba = new BankAccount("Dhruv",0.0, 0.0, 90793209);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyServer.json");
            writer.open();
            writer.write(ba);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyServer.json");
            ba = reader.read();
            assertEquals("Dhruv", ba.getAccountName());
            assertEquals(90793209, ba.getAccountId());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

//    @Test
//    void testWriterGeneralWorkroom() {
//        try {
//            BankAccount ba = new BankAccount("Dhruv",0,0,90793209);
//            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary.json");
//            writer.open();
//            writer.write(ba);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
//            ba = reader.read();
//            assertEquals("Dhruv", lib.getName());
//            List<Book> books = lib.getBooks();
//            assertEquals(2, books.size());
//
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }

}
