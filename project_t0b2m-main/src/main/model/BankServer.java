package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Represents the bank and all the accounts it contains
public class BankServer extends BankAccount implements Writable {

    private ArrayList<BankAccount> accounts;  //stores a list of bank accounts

    //EFFECTS: constructor to initialise accounts and call the super class
    public BankServer() {
        super();
        accounts = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a number of initial accounts to start the bank
    public void initialAccounts() {
        BankAccount ba1 = new BankAccount("Dhruv",5000.0,25000.0,90793209);
        BankAccount ba2 = new BankAccount("David", 0.0, 60000.0,87651220);
        BankAccount ba3 = new BankAccount("Jennifer", 3000.0, 2000.0,81273698);
        accounts.add(ba1);
        accounts.add(ba2);
        accounts.add(ba3);
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    //MODIFIES: this
    //EFFECTS: adds a new BankAccount to the list of existing accounts
    public void addAccount(BankAccount ba) {
        accounts.add(ba);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("accounts", accountsToJson());
        return json;
    }

    // EFFECTS: returns accounts in this server as a JSON array
    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (BankAccount b : accounts) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }

}
