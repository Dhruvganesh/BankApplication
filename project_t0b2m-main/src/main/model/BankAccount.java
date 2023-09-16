package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents each user's bank account
public class BankAccount implements Writable {
    private String name;                //stores the clients name
    private int accountId;              //stores the clients id
    private double checkingBalance;     //stores the balance of the client's checking account
    private double savingsBalance;      //stores the balance of the client's savings account

    /*
     * EFFECTS: id is sent to accountId, accountName on account is sent to name;
     *          checkingBalanceInitial is sent to checking Balance
     *          savingsBalanceInitial is sent to savingsBalance
     */
    public BankAccount(String accountName, double checkingBalanceInitial, double savingsBalanceInitial, int id) {
        name = accountName;
        checkingBalance = checkingBalanceInitial;
        savingsBalance = savingsBalanceInitial;
        accountId = id;
    }

    // EFFECTS: Empty constructor
    public BankAccount() {

    }

    public String getAccountName() {
        return name;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public int getAccountId() {
        return accountId;
    }

    //MODIFIES: this
    //EFFECTS: withdraws money from checking account when required
    public boolean withdrawFromChecking(double amt) {
        if (amt <= checkingBalance) {
            checkingBalance -= amt;
            EventLog.getInstance().logEvent(new Event((amt + " is withdrawn from checking account.")));
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: withdraws money from savings account when required
    public boolean withdrawFromSaving(double amt) {
        if (amt <= savingsBalance) {
            savingsBalance -= amt;
            EventLog.getInstance().logEvent(new Event((amt + " is withdrawn from savings account.")));
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: deposits money into checking account when required
    public boolean depositIntoChecking(double amt) {
        if (amt > 0.0) {
            checkingBalance += amt;
            EventLog.getInstance().logEvent(new Event((amt + " is deposited into checking account.")));
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: deposits money into savings account when required
    public boolean depositIntoSaving(double amt) {
        if (amt > 0.0) {
            savingsBalance += amt;
            EventLog.getInstance().logEvent(new Event((amt + " is deposited into savings account.")));
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: transfers money from checking to savings account when required
    public boolean transferFromCheckingToSavings(double amt) {
        if (amt <= checkingBalance) {
            savingsBalance += amt;
            checkingBalance -= amt;
            EventLog.getInstance().logEvent(new Event((amt + " is transferred from checking to savings account.")));
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: transfers money from savings to checking account when required
    public boolean transferFromSavingsToChecking(double amt) {
        if (amt <= savingsBalance) {
            checkingBalance += amt;
            savingsBalance -= amt;
            EventLog.getInstance().logEvent(new Event((amt + " is transferred from savings to checking account.")));
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: returns the interest rate of a checking account
    public double interestPredictorInChecking(double amt, double time) {
        for (int i = 1; i <= (int)time; i++) {
            amt = amt + (0.03 * amt);
        }
        EventLog.getInstance().logEvent(new Event(("Interest rates of checking account were checked")));
        return amt;
    }

    //EFFECTS: returns the interest rate of a savings account
    public double interestPredictorInSavings(double amt, double time) {
        for (int i = 1; i <= (int)time; i++) {
            amt = amt + (0.06 * amt);
        }
        EventLog.getInstance().logEvent(new Event(("Interest rates of savings account were checked")));
        return amt;
    }

    //EFFECTS: provides good stock options to invest in
    public String bestInvestmentIdeas() {
        return "Best stocks to invest in right now are:"
                +
                "\n1. Ford\n2. Alphabet\n3. Qualcomm\n4. Salesforce\n5. Walt Disney";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("accountId", accountId);
        json.put("checkingBalance", checkingBalance);
        json.put("savingsBalance", savingsBalance);
        return json;
    }


}
