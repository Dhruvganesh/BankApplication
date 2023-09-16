package persistence;

import model.BankAccount;
import model.BankServer;

import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads bank account from JSON data stored in file
// Based on the example given
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads bank account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BankAccount read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBankAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses BankAccount from JSON object and returns it
    private BankAccount parseBankAccount(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double checkingBalance = jsonObject.getDouble("checkingBalance");
        double savingsBalance = jsonObject.getDouble("savingsBalance");
        int accountId = jsonObject.getInt("accountId");
        BankAccount act = new BankAccount(name,checkingBalance,savingsBalance,accountId);
        return act;
    }

    // MODIFIES: bs
    // EFFECTS: parses bankAccount from JSON object and adds them to bankServer
    private void addAccounts(BankAccount ba, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Account");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount2(ba, nextAccount);
        }
    }

    // MODIFIES: bs
    // EFFECTS: parses ba from JSON object and adds it to bank server
    private void addAccount2(BankAccount ba, JSONObject jsonObject) {
        BankServer bs = new BankServer();
        bs.addAccount(ba);
    }

}
