import java.util.Scanner;

class BankAccount {
    
    String accHolderName;
    String loginUsername;
    String loginPassword;
    String accNumber;
    float accBalance = 100000f;
    int transactionCount = 0;
    String transactionLog = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Full Name - ");
        this.accHolderName = sc.nextLine();
        System.out.print("\nSet Your Username - ");
        this.loginUsername = sc.nextLine();
        System.out.print("\nSet Your Password - ");
        this.loginPassword = sc.nextLine();
        System.out.print("\nEnter Your Account Number - ");
        this.accNumber = sc.nextLine();
        System.out.println("\nRegistration successful! Please log in.");
    }

    public boolean login() {
        boolean isAuthenticated = false;
        Scanner sc = new Scanner(System.in);
        while (!isAuthenticated) {
            System.out.print("\nEnter Your Username - ");
            String usernameInput = sc.nextLine();
            if (usernameInput.equals(loginUsername)) {
                while (!isAuthenticated) {
                    System.out.print("\nEnter Your Password - ");
                    String passwordInput = sc.nextLine();
                    if (passwordInput.equals(loginPassword)) {
                        System.out.println("\nLogin successful! Welcome, " + accHolderName + "!");
                        isAuthenticated = true;
                    } else {
                        System.out.println("\nIncorrect Password. Try again.");
                    }
                }
            } else {
                System.out.println("\nUsername not found. Try again.");
            }
        }
        return isAuthenticated;
    }

    public void withdraw() {
        System.out.print("\nEnter amount to withdraw - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        if (accBalance >= amount) {
            transactionCount++;
            accBalance -= amount;
            System.out.println("\nWithdrawal Successful! Remaining Balance: " + accBalance + " Rs");
            transactionLog += amount + " Rs Withdrawn\n";
        } else {
            System.out.println("\nInsufficient Balance.");
        }
    }

    public void deposit() {
        System.out.print("\nEnter amount to deposit - ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        if (amount <= 100000f) {
            transactionCount++;
            accBalance += amount;
            System.out.println("\nDeposit Successful! Updated Balance: " + accBalance + " Rs");
            transactionLog += amount + " Rs Deposited\n";
        } else {
            System.out.println("\nDeposit Limit Exceeded! Maximum allowed is 100000 Rs");
        }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Recipient's Name - ");
        String recipient = sc.nextLine();
        System.out.print("\nEnter amount to transfer - ");
        float amount = sc.nextFloat();
        if (accBalance >= amount) {
            if (amount <= 50000f) {
                transactionCount++;
                accBalance -= amount;
                System.out.println("\nTransfer Successful! " + amount + " Rs sent to " + recipient);
                transactionLog += amount + " Rs Transferred to " + recipient + "\n";
            } else {
                System.out.println("\nTransfer Limit Exceeded! Maximum allowed is 50000 Rs");
            }
        } else {
            System.out.println("\nInsufficient Balance.");
        }
    }

    public void checkBalance() {
        System.out.println("\nCurrent Balance: " + accBalance + " Rs");
    }

    public void showTransactionHistory() {
        if (transactionCount == 0) {
            System.out.println("\nNo Transactions Yet");
        } else {
            System.out.println("\nTransaction History:\n" + transactionLog);
        }
    }
}

public class ATMInterface {

    public static int getUserChoice(int limit) {
        int input = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                if (input >= 1 && input <= limit) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice! Please select between 1 and " + limit);
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter an integer.");
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("\n********** WELCOME TO SECURE ATM INTERFACE **********\n");
        System.out.println("1. Register \n2. Exit");
        System.out.print("Enter Your Choice - ");
        int choice = getUserChoice(2);

        if (choice == 1) {
            BankAccount account = new BankAccount();
            account.register();
            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.print("Enter Your Choice - ");
                int loginChoice = getUserChoice(2);
                if (loginChoice == 1) {
                    if (account.login()) {
                        boolean sessionActive = true;
                        while (sessionActive) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            System.out.print("\nEnter Your Choice - ");
                            int action = getUserChoice(6);
                            switch (action) {
                                case 1:
                                    account.withdraw();
                                    break;
                                case 2:
                                    account.deposit();
                                    break;
                                case 3:
                                    account.transfer();
                                    break;
                                case 4:
                                    account.checkBalance();
                                    break;
                                case 5:
                                    account.showTransactionHistory();
                                    break;
                                case 6:
                                    sessionActive = false;
                                    System.out.println("\nThank you for using Secure ATM Interface!\n");
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.out.println("\nThank you! Visit again.");
            System.exit(0);
        }
    }
}