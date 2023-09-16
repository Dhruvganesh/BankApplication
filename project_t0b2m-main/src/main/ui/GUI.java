package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    private BankServer bankServerObject;
    private BankAccount baObj;
    private static final String JSON_STORE = "./data/accounts.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JFrame frame;
    private JLabel label;
    private JTextArea textArea;
    private JLabel imageLabel;
    private Border border;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JFrame popupSignIn;
    private JButton popupSignInButton;
    private JTextField popupTextSignIn;
    private JTextField popupTextSignInV2;
    private JFrame popupDeposit;
    private JButton popupDepositButton;
    private JTextField popupTextDeposit;
    private JTextField popupTextDeposit2;
    private JFrame popupWithdraw;
    private JButton popupWithdrawButton;
    private JTextField popupTextWithdraw;
    private JTextField popupTextWithdraw2;
    private JFrame popupTransfer;
    private JButton popupTransferButton;
    private JTextField popupTextTransfer;
    private JTextField popupTextTransfer2;
    private JFrame popupCheckRate;
    private JButton popupCheckRateButton;
    private JTextField popupTextCheckRate;
    private JTextField popupTextCheckRate2;
    private JTextField popupTextCheckRate3;
    private JLabel bankOfUBC;


    //EFFECTS: constructor which calls the main method
    public GUI() {
        guiMethod();
    }

    //EFFECTS: contains main control flow of program
    public  void guiMethod() {
        initialiseMethod();
        textAreaAndLabelSetter();
        setButtons1();
        setButtons2();
        setFrame1();
        frame.setVisible(true);
        label.setVisible(true);
        bankServerObject.initialAccounts();
        popupButton1();
        popupButton3();
        popupButton4();
        popupButton5();
        popupButton6();
    }

    //MODIFIES: frame
    //EFFECTS: sets the frame of the entire program
    public void setFrame1() {
        frame = new JFrame();   //creates frame
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog();
            }
        });
        frame.setTitle("Bank of University of British Columbia");
        frame.setVisible(true);
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //exits app on command
        frame.setResizable(false);  // prevents resizing of window
        frame.getContentPane().setBackground(new Color(0x123456));
        frame.add(label);
        frame.setLayout(null);
        setFrame2();
    }

    public void setFrame2() {
        frame.add(textArea);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.add(button7);
        frame.add(button8);
        frame.add(button9);
        frame.add(imageLabel);
    }

    //MODIFIES: textArea, imageLabel and label
    //EFFECTS: makes the textArea, image and heading appear
    public void textAreaAndLabelSetter() {
        textArea.setBounds(0, 100, 400, 300);
        textArea.setBackground(Color.WHITE);
        imageLabel = new JLabel();
        imageLabel.setBackground(Color.BLACK);
        ImageIcon image = new ImageIcon("/Users/dhruvganesh/Desktop/project_t0b2m/src/main/ui/bankOfUBC.png");
        imageLabel.setBounds(400, 100, 300, 300);
        imageLabel.setIcon(image);
        label.setText("Bank of UBC");
        label.setForeground(new Color(255,223,0));
        label.setFont(new Font("MV Boli", Font.BOLD, 50));
        label.setBackground(new Color(0x123456));
        label.setOpaque(true);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(0, 0, 700, 100);
        bankOfUBC = new JLabel();
    }

    //EFFECTS: initialises objects
    public void initialiseMethod() {
        bankServerObject = new BankServer();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        textArea = new JTextArea();
        border = BorderFactory.createLineBorder(Color.BLACK);
        label = new JLabel();
    }

    //MODIFIES: button1, button2, button3 and button4
    //EFFECTS: makes the buttons 1-4 appear
    public void setButtons1() {
        button1 = new JButton("Sign in");
        button1.setBounds(200, 400, 100, 90);
        button1.setFont(new Font("MV Boli", Font.PLAIN, 10));
        button1.addActionListener(this);
        button2 = new JButton("Check balances");
        button2.setBounds(300, 400, 100, 90);
        button2.setFont(new Font("MV Boli", Font.PLAIN, 10));
        button2.addActionListener(this);
        button3 = new JButton("Deposit money");
        button3.setBounds(400, 400, 100, 90);
        button3.setFont(new Font("MV Boli", Font.PLAIN, 10));
        button3.addActionListener(this);
        button4 = new JButton("Withdraw money");
        button4.setBounds(200, 490, 100, 90);
        button4.setFont(new Font("MV Boli", Font.PLAIN, 10));
        button4.addActionListener(this);
        button5 = new JButton("Transfer money");
        button5.setBounds(300, 490, 100, 90);
        button5.setFont(new Font("MV Boli", Font.PLAIN, 10));
        button5.addActionListener(this);
    }

    //MODIFIES: button5, button6, button7 and button8
    //EFFECTS: makes the buttons 5-8 appear
    public void setButtons2() {
        button6 = new JButton("Check interest rates");
        button6.setBounds(400, 490, 100, 90);
        button6.setFont(new Font("MV Boli", Font.PLAIN, 8));
        button6.addActionListener(this);
        button7 = new JButton("Save balances");
        button7.setBounds(200, 580, 100, 90);
        button7.addActionListener(this);
        button8 = new JButton("Load balances");
        button8.setBounds(300, 580, 100, 90);
        button8.addActionListener(this);
        button9 = new JButton("Check accounts");
        button9.setBounds(400, 580, 100, 90);
        button9.setFont(new Font("MV Boli", Font.PLAIN, 10));
        button9.addActionListener(this);
    }

    //MODIFIES: popupButton1
    //EFFECTS: makes the popupButton1 function
    public void popupButton1() {
        popupSignIn = new JFrame();
        popupSignInButton = new JButton("Enter");
        popupTextSignIn = new JTextField("<Replace with name>");
        popupTextSignInV2 = new JTextField("<Replace with student number>");

        popupSignIn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupSignIn.setVisible(false);

        popupSignIn.setLayout(new FlowLayout());
        popupSignIn.add(popupTextSignIn);
        popupSignIn.add(popupTextSignInV2);
        popupSignIn.add(popupSignInButton);
        popupSignIn.setSize(new Dimension(300,200));
        popupSignInButton.addActionListener(this);
    }

    //MODIFIES: popupButton3
    //EFFECTS: makes the popupButton3 function
    public void popupButton3() {
        popupDeposit = new JFrame();
        popupDepositButton = new JButton("Enter");
        popupTextDeposit = new JTextField("<Replace with 1 for checking account or 2 for savings account.>");
        popupTextDeposit2 = new JTextField("<Replace with amount you wish to deposit>");
        popupDeposit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupDeposit.setVisible(false);

        popupDeposit.setLayout(new FlowLayout());
        popupDeposit.add(popupTextDeposit);
        popupDeposit.add(popupTextDeposit2);
        popupDeposit.add(popupDepositButton);
        popupDeposit.setSize(new Dimension(500,200));
        popupDepositButton.addActionListener(this);
    }

    //MODIFIES: popupButton4
    //EFFECTS: makes the popupButton4 function
    public void popupButton4() {
        popupWithdraw = new JFrame();
        popupWithdrawButton = new JButton("Enter");
        popupTextWithdraw = new JTextField("<Replace with 1 for checking account or 2 for savings account.>");
        popupTextWithdraw2 = new JTextField("<Replace with amount you wish to withdraw>");
        popupWithdraw.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupWithdraw.setVisible(false);

        popupWithdraw.setLayout(new FlowLayout());
        popupWithdraw.add(popupTextWithdraw);
        popupWithdraw.add(popupTextWithdraw2);
        popupWithdraw.add(popupWithdrawButton);
        popupWithdraw.setSize(new Dimension(500,200));
        popupWithdrawButton.addActionListener(this);
    }

    //MODIFIES: popupButton5
    //EFFECTS: makes the popupButton5 function
    public void popupButton5() {
        popupTransfer = new JFrame();
        popupTransferButton = new JButton("Enter");
        popupTextTransfer = new JTextField("<Replace with 1 to transfer from checking to saving and 2 for vice versa>");
        popupTextTransfer2 = new JTextField("<Replace with amount you wish to transfer>");
        popupTransfer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupTransfer.setVisible(false);

        popupTransfer.setLayout(new FlowLayout());
        popupTransfer.add(popupTextTransfer);
        popupTransfer.add(popupTextTransfer2);
        popupTransfer.add(popupTransferButton);
        popupTransfer.setSize(new Dimension(500,200));
        popupTransferButton.addActionListener(this);
    }

    //MODIFIES: popupButton6
    //EFFECTS: makes the popupButton6 function
    public void popupButton6() {
        popupCheckRate = new JFrame();
        popupCheckRateButton = new JButton("Enter");
        popupTextCheckRate = new JTextField("<Replace with 1 to check rates in checking and 2 for savings>");
        popupTextCheckRate2 = new JTextField("<Replace with amount you wish to check rate for>");
        popupTextCheckRate3 = new JTextField("<Replace with time (in years) you wish to check rate for>");
        popupCheckRate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupCheckRate.setVisible(false);

        popupCheckRate.setLayout(new FlowLayout());
        popupCheckRate.add(popupTextCheckRate);
        popupCheckRate.add(popupTextCheckRate2);
        popupCheckRate.add(popupTextCheckRate3);
        popupCheckRate.add(popupCheckRateButton);
        popupCheckRate.setSize(new Dimension(500,200));
        popupCheckRateButton.addActionListener(this);
    }

    //EFFECTS: performs actions when buttons are pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            button1Function();
        }
        if (e.getSource() == popupSignInButton) {
            popupButton1Function();
        }
        if (e.getSource() == button2) {
            button2Function();
        }
        if (e.getSource() == button3) {
            button3Function();
        }
        if (e.getSource() == popupDepositButton) {
            popupButton3Function();
        }
        if (e.getSource() == button4) {
            button4Function();
        }
        if (e.getSource() == popupWithdrawButton) {
            popupButton4Function();
        }
        buttonCondition(e);
    }

    //EFFECTS: performs actions when buttons are pressed
    public void buttonCondition(ActionEvent e) {
        if (e.getSource() == button5) {
            button5Function();
        }
        if (e.getSource() == popupTransferButton) {
            popupButton5Function();
        }
        if (e.getSource() == button6) {
            button6Function();
        }
        if (e.getSource() == popupCheckRateButton) {
            popupButton6Function();
        }
        if (e.getSource() == button7) {
            button7Function();
        }
        if (e.getSource() == button8) {
            button8Function();
        }
        if (e.getSource() == button9) {
            button9Function();
        }
    }

    //EFFECTS: contains sign in function
    public void button1Function() {
        textArea.setText("");
        popupSignIn.setVisible(true);
    }

    //EFFECTS: stores input for account details
    public void popupButton1Function() {
        String tempName = popupTextSignIn.getText();
        String tempId = popupTextSignInV2.getText();
        popupSignIn.setVisible(false);
        int tempId2 = Integer.parseInt(tempId);
        int ctr = 0;
        for (BankAccount ba: bankServerObject.getAccounts()) {
            if (ba.getAccountId() == tempId2) {
                baObj = ba;
                ctr = 1;
            }
        }
        if (ctr == 0) {
            baObj = new BankAccount(tempName, 0, 0, tempId2);
            bankServerObject.addAccount(baObj);
        }
        textArea.append("You have signed in successfully.");
        EventLog.getInstance().logEvent(new Event(("Account was signed into.")));


    }

    //EFFECTS: displays balances in bank account
    public void button2Function() {
        textArea.setText("");
        textArea.append("The amount of money in your checking account is " + baObj.getCheckingBalance());
        textArea.append("\n");
        textArea.append("\nThe amount of money in your savings account is " + baObj.getSavingsBalance());
        EventLog.getInstance().logEvent(new Event(("Balances were checked.")));
    }

    //EFFECTS: deposits money into accounts
    public void button3Function() {
        textArea.setText("");
        popupDeposit.setVisible(true);
    }

    //EFFECTS: stores input and function for depositing money into account
    public void popupButton3Function() {
        String choice = popupTextDeposit.getText();
        String amountTemp = popupTextDeposit2.getText();
        popupDeposit.setVisible(false);
        double amount = Double.parseDouble(amountTemp);
        int choice2 = Integer.parseInt(choice);
        if (choice2 == 1) {
            if (baObj.depositIntoChecking(amount)) {
                textArea.append("The amount has been added your checking account.");
            } else {
                textArea.append("You have not entered the correct amount.");
            }
            textArea.append("\n");
        }
        if (choice2 == 2) {
            if (baObj.depositIntoSaving(amount)) {
                textArea.append("The amount has been added your savings account.");
            } else {
                textArea.append("You have not entered the correct amount.");
            }
            textArea.append("\n");
        }
    }

    //EFFECTS: withdraws money from account
    public void button4Function() {
        textArea.setText("");
        popupWithdraw.setVisible(true);
    }

    //EFFECTS: stores input and function for withdrawing from account
    public void popupButton4Function() {
        String choice = popupTextWithdraw.getText();
        String amountTemp = popupTextWithdraw2.getText();
        popupWithdraw.setVisible(false);
        double amount = Double.parseDouble(amountTemp);
        int choice2 = Integer.parseInt(choice);
        if (choice2 == 1) {
            if (baObj.withdrawFromChecking(amount)) {
                textArea.append("The amount has been deducted from your checking account.");
            } else {
                textArea.append("You have not entered the correct amount.");
            }
            textArea.append("\n");
        }
        if (choice2 == 2) {
            if (baObj.withdrawFromSaving(amount)) {
                textArea.append("The amount has been deducted from your savings account.");
            } else {
                textArea.append("You have not entered the correct amount.");
            }
            textArea.append("\n");
        }
    }

    //EFFECTS: transfers money between checking and savings account
    public void button5Function() {
        textArea.setText("");
        popupTransfer.setVisible(true);
    }

    //EFFECTS: stores input and function for transferring between accounts
    public void popupButton5Function() {
        String choice = popupTextTransfer.getText();
        String amountTemp = popupTextTransfer2.getText();
        popupTransfer.setVisible(false);
        double amount = Double.parseDouble(amountTemp);
        int choice2 = Integer.parseInt(choice);
        if (choice2 == 1) {
            if (baObj.transferFromCheckingToSavings(amount)) {
                textArea.append("The amount has been added to your savings account and deducted from your checking.");
            } else {
                textArea.append("You have not entered the correct amount.");
            }
            textArea.append("\n");
        }
        if (choice2 == 2) {
            if (baObj.transferFromSavingsToChecking(amount)) {
                textArea.append("The amount has been added to your checking account and deducted from your savings.");
            } else {
                textArea.append("You have not entered the correct amount.");
            }
            textArea.append("\n");
        }
    }

    //EFFECTS: checks interest rates
    public void button6Function() {
        textArea.setText("");
        popupCheckRate.setVisible(true);
    }

    //EFFECTS: stores input and function for checking interest rates
    public void popupButton6Function() {
        String choice = popupTextCheckRate.getText();
        String amountTemp = popupTextCheckRate2.getText();
        String yearsTemp = popupTextCheckRate3.getText();
        popupCheckRate.setVisible(false);
        double amount = Double.parseDouble(amountTemp);
        int choice2 = Integer.parseInt(choice);
        int years = Integer.parseInt(yearsTemp);
        if (choice2 == 1) {
            double calculatedAmount = baObj.interestPredictorInChecking(amount, years);
            textArea.append("The amount of money you will have is: " + calculatedAmount);
        }
        if (choice2 == 2) {
            double calculatedAmount = baObj.interestPredictorInSavings(amount, years);
            textArea.append("The amount of money you will have is: " + calculatedAmount);
        }
    }

    //EFFECTS: saves balances to server
    public void button7Function() {
        textArea.setText("");
        EventLog.getInstance().logEvent(new Event(("Balances were saved.")));
        try {
            jsonWriter.open();
            jsonWriter.write(baObj);
            jsonWriter.close();
            textArea.append("Saved balances to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            textArea.append("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: loads balances to server
    public void button8Function() {
        textArea.setText("");
        EventLog.getInstance().logEvent(new Event(("Balances were loaded.")));
        try {
            baObj = jsonReader.read();
            textArea.append("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            textArea.append("Unable to read from file: " + JSON_STORE);
        }
    }

//    //EFFECTS: Quits the application
//    public void button9Function() {
//        textArea.setText("");
//        textArea.append("The application has ended.");
//        frame.setVisible(false);
//    }

    public void button9Function() {
        textArea.setText("");
        ArrayList<BankAccount> accountsTemp = bankServerObject.getAccounts();
        for (BankAccount ba: accountsTemp) {
            textArea.append(ba.getAccountName());
            textArea.append("\n");
        }
        EventLog.getInstance().logEvent(new Event(("Accounts in the bank server were checked.")));
    }

    public void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n\n");
        }
        System.exit(0);
    }



}
