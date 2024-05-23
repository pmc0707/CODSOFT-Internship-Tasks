
import java.util.HashMap;
import java.util.Scanner;

class ATM {
    private final HashMap<String, Double> accounts;
    private final Scanner scanner;

    public ATM() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
        // Adding some dummy accounts
        accounts.put("user1", 1000.0);
        accounts.put("user2", 1500.0);
        accounts.put("user3", 2000.0);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }

    public void start() {
        while (true) {
            System.out.println("Welcome to the ATM Interface");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void checkBalance() {
        String account = getAccount();
        if (account != null) {
            System.out.println("Your balance is: " + accounts.get(account));
        }
    }

    private void deposit() {
        String account = getAccount();
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            if (amount > 0) {
                accounts.put(account, accounts.get(account) + amount);
                System.out.println("Deposit successful. New balance: " + accounts.get(account));
            } else {
                System.out.println("Invalid amount. Please try again.");
            }
        }
    }

    private void withdraw() {
        String account = getAccount();
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            if (amount > 0 && accounts.get(account) >= amount) {
                accounts.put(account, accounts.get(account) - amount);
                System.out.println("Withdrawal successful. New balance: " + accounts.get(account));
            } else {
                System.out.println("Invalid amount or insufficient funds. Please try again.");
            }
        }
    }

    private String getAccount() {
        System.out.print("Enter your account name: ");
        String account = scanner.nextLine();
        if (accounts.containsKey(account)) {
            return account;
        } else {
            System.out.println("Account not found. Please try again.");
            return null;
        }
    }
}