package ui;

import model.BankAccount;
import model.BankServer;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UICode {
    private BankServer bankServerObject;
    private BankAccount baObj;
    private static final String JSON_STORE = "./data/accounts.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the UICode application
    public UICode() {
        bankServerObject = new BankServer();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runUICode();
    }

    //MODIFIES: this
    private void runUICode() {
        bankServerObject.initialAccounts();
        mainAccount();
        displayMenu();
    }

    //MODIFIES: this
    //EFFECTS: New account is being created
    //REQUIRES: Each id to be unique
    private void mainAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String tempName = sc.nextLine();
        System.out.println("Enter a 8 digit account id of your choice");
        int tempID = sc.nextInt();
        int ctr = 0;
        for (BankAccount ba: bankServerObject.getAccounts()) {
            if (ba.getAccountId() == tempID) {
                baObj = ba;
                ctr = 1;
            }
        }
        if (ctr == 0) {
            baObj = new BankAccount(tempName, 0, 0, tempID);
            bankServerObject.addAccount(baObj);
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void displayMenu() {
        Scanner sc = new Scanner(System.in);
        options();
        System.out.println("Enter a number between 1-14 for your choice.");
        int choice = sc.nextInt();
        if (choice == 1) {
            option1();
        } else if (choice == 2) {
            option2();
        } else if (choice == 3) {
            option3();
        } else if (choice == 4) {
            option4();
        } else {
            options5to12(choice);
        }
    }

    private void options5to12(int opt) {
        if (opt == 5) {
            option5();
        } else if (opt == 6) {
            option6();
        } else if (opt == 7) {
            option7();
        } else if (opt == 8) {
            option8();
        } else if (opt == 9) {
            option9();
        } else if (opt == 10) {
            option10();
        } else if (opt == 11) {
            option11();
        } else if (opt == 12) {
            option12();
        } else if (opt == 13) {
            option13();
        } else if (opt == 14) {
            option14();
        } else {
            options();
        }
    }

    //EFFECTS: displays the options available in the system
    private void options() {
        System.out.println("The bank has the following applications:");
        System.out.println("1. Check the balance in your checking account");
        System.out.println("2. Check the balance in your savings account");
        System.out.println("3. Deposit money in your checking account");
        System.out.println("4. Deposit money in your savings account");
        System.out.println("5. Withdraw money from your checking account");
        System.out.println("6. Withdraw money from your savings account");
        System.out.println("7. Transfer money from your savings account to your checking account");
        System.out.println("8. Transfer money from your checking account to your savings account");
        System.out.println("9. Check the interest rates of your checking account");
        System.out.println("10. Check the interest rate of your savings account");
        System.out.println("11. Get some investment ideas");
        System.out.println("12. Save checking and saving balance to bank server");
        System.out.println("13. Load checking and saving balance to bank server");
        System.out.println("14. Quit");
        System.out.println();
    }

    //EFFECTS: returns the money in checking account
    private void option1() {
        System.out.println("The amount of money in your checking account is " + baObj.getCheckingBalance());
        System.out.println();
        displayMenu();
    }

    //EFFECTS: returns the money in savings account
    private void option2() {
        System.out.println("The amount of money in your savings account is " + baObj.getSavingsBalance());
        System.out.println();
        displayMenu();
    }

    //EFFECTS: deposits the money in checking account
    private void option3() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount of money you wish to deposit into your checking account: ");
        double amount = sc.nextDouble();
        if (baObj.depositIntoChecking(amount)) {
            System.out.println("The amount has been added your checking account.");
        } else {
            System.out.println("You have not entered the correct amount.");
        }
        System.out.println();
        displayMenu();
    }

    //EFFECTS: deposits the money in savings account
    private void option4() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount of money you wish to deposit into your savings account: ");
        double amount = sc.nextDouble();
        if (baObj.depositIntoSaving(amount)) {
            System.out.println("The amount has been added to your savings account.");
        } else {
            System.out.println("You have not entered the correct amount.");
        }
        System.out.println();
        displayMenu();
    }

    //EFFECTS: withdraws the money from checking account
    private void option5() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount of money you wish to withdraw from your checking account: ");
        double amount = sc.nextDouble();
        if (baObj.withdrawFromChecking(amount)) {
            System.out.println("The amount has been deducted from your checking account.");
        } else {
            System.out.println("You have not entered the correct amount.");
        }
        System.out.println();
        displayMenu();
    }

    //EFFECTS: withdraws the money from savings account
    private void option6() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount of money you wish to withdraw from your savings account: ");
        double amount = sc.nextDouble();
        if (baObj.withdrawFromSaving(amount)) {
            System.out.println("The amount has been deducted from your savings account.");
        } else {
            System.out.println("You have not entered the correct amount.");
        }
        System.out.println();
        displayMenu();
    }

    //EFFECTS: transfers the money to checking account
    private void option7() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount of money you wish to transfer to your checking account: ");
        double amount = sc.nextDouble();
        if (baObj.depositIntoChecking(amount)) {
            System.out.println("The amount has been added to your checking account and deducted from your saving.");
        } else {
            System.out.println("You have not entered the correct amount.");
        }
        System.out.println();
        displayMenu();
    }

    //EFFECTS: transfers the money to savings account
    private void option8() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount of money you wish to transfer to your saving account: ");
        double amount = sc.nextDouble();
        if (baObj.depositIntoSaving(amount)) {
            System.out.println("The amount has been added to your savings account and deducted from your checking.");
        } else {
            System.out.println("You have not entered the correct amount.");
        }
        System.out.println();
        displayMenu();
    }

    //EFFECTS: returns interest rate in checking account
    private void option9() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount of money you wish to check interest rates on : ");
        double amount = sc.nextDouble();
        System.out.println("Enter the amount of time in years you wish to check interest rates on : ");
        int time = sc.nextInt();
        double calculatedAmount = baObj.interestPredictorInChecking(amount, time);
        System.out.println();
        System.out.println("The amount of money you will have is: " + calculatedAmount);
        System.out.println();
        displayMenu();
    }

    //EFFECTS: returns interest rate in savings account
    private void option10() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the amount of money you wish to check interest rates on : ");
        double amount = sc.nextDouble();
        System.out.println("Enter the amount of time in years you wish to check interest rates on : ");
        int time = sc.nextInt();
        double calculatedAmount = baObj.interestPredictorInSavings(amount, time);
        System.out.println();
        System.out.println("The amount of money you will have is: " + calculatedAmount);
        System.out.println();
        displayMenu();
    }

    //EFFECTS: returns good investment opportunities
    private void option11() {
        System.out.println(baObj.bestInvestmentIdeas());
        System.out.println();
        displayMenu();
    }

     //EFFECTS: saves balances to server
    private void option12() {
        try {
            jsonWriter.open();
            jsonWriter.write(baObj);
            jsonWriter.close();
            System.out.println("Saved balances to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        displayMenu();
    }

    // EFFECTS: loads balances to server
    private void option13() {
        try {
            baObj = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        displayMenu();
    }

    //EFFECTS: quits the application
    private void option14() {
    }

}
