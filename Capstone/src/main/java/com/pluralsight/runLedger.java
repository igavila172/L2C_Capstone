package com.pluralsight;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class runLedger {

    public static void displayLedgerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean stayInMenu = true;

        while (stayInMenu){
            System.out.println("\n***** Ledger Menu *****");
            System.out.println("[1] All entries");
            System.out.println("[2] Deposits");
            System.out.println("[3] Payments");
            System.out.println("[4] Reports");
            System.out.println("[5] Home");
            System.out.print("Choose an option [1-5]: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("\n[Showing all Entries]");
                    showAllEntries();
                    break;
                case 2:
                    System.out.println("\n[Showing all DEPOSITS]");
                    showDeposits();
                    break;
                case 3:
                    System.out.println("\n[Showing all PAYMENTS]");
                    showPayments();
                    break;
                case 4:
                    System.out.println("\n[Showing all REPORTS]");
                    showReports(scanner);
                    break;
                case 5:
                    System.out.println("\n[Returning to HOME]");
                    // insert method here :))
                    stayInMenu = false;
                    break;
                default:
                    System.out.println("\nInvalid choice, Please try again.");
            }
        }
    }
    private static void showAllEntries(){
            List<String> transactions = readTransactionsFromCSV();
            for (String transaction : transactions){
                System.out.println(transaction);
            }
    }
    private static void showPayments(){
            List<String> transactions = readTransactionsFromCSV();
            for (String transaction : transactions){
                if (transaction.startsWith("-")){
                    System.out.println(transaction);
                }
            }
    }
    private static void showDeposits(){
        List<String> transactions = readTransactionsFromCSV();
        for (String transaction : transactions){
            if (!transaction.startsWith("-")){
                System.out.println(transaction);
            }
        }
    }
    private static void showReports(Scanner scanner){
        boolean stayInReportsMenu = true;

        while (stayInReportsMenu){
            System.out.println("\n***** Reports *****");
            System.out.println("[1] Month To Date");
            System.out.println("[2] Previous Month");
            System.out.println("[3] Year To Date");
            System.out.println("[4] Previous Year");
            System.out.println("[5] Search by Vendor");
            System.out.println("[0] Back");
            System.out.print("Choose an option [0-5]: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("\n[Month to Date]");
                    //method
                    break;
                case 2:
                    System.out.println("\n[Previous Month]");
                    //method
                    break;
                case 3:
                    System.out.println("\n[Year to Date]");
                    //method
                    break;
                case 4:
                    System.out.println("\n[Previous Year]");
                    //method
                    break;
                case 5:
                    System.out.println("\n[Search by Vendor]");
                    System.out.print("Enter Vendor Name: ");
                    String vendorName = scanner.nextLine();
                    showTransactionVendor(vendorName);
                    break;
                case 0:
                    stayInReportsMenu = false;
                    break;
                default:
                    System.out.println("\n Invalid choice, Please try again.");
            }

        }
    }
    private static void showTransactionVendor(String vendorName){
        List<String> transactions = readTransactionsFromCSV();
        for (String transaction : transactions){
            if (transaction.contains(vendorName)){
                System.out.println(transaction);
            }
        }
    }
    private static List<String> readTransactionsFromCSV(){
        List<String> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e){
                System.out.println("Error reading file: " + e.getMessage());

        }return transactions;
    }
}
