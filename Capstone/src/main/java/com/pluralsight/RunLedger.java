package com.pluralsight;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class RunLedger {

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
                    showReports();
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
            List<Transaction> transactions = readTransactionsFromCSV();
            for (Transaction transaction : transactions){
                System.out.println(transaction);
            }
    }
    private static void showPayments(){
            List<Transaction> transactions = readTransactionsFromCSV();
            for (Transaction transaction : transactions){
                if (transaction.getAmount()<0){
                    System.out.println(transaction);
                }
            }
    }
    private static void showDeposits(){
        List<Transaction> transactions = readTransactionsFromCSV();
        for (Transaction transaction : transactions){
            if (transaction.getAmount()>0){
                System.out.println(transaction);
            }
        }
    }
    private static void showReports(){
        Scanner scanner = new Scanner(System.in);
        boolean stayInReportsMenu = true;

        while (stayInReportsMenu){
            System.out.println("\n***** Reports *****");
            System.out.println("[1] Month To Date");
            System.out.println("[2] Previous Month");
            System.out.println("[3] Year To Date");
            System.out.println("[4] Previous Year");
            System.out.println("[5] Search by Vendor");
            System.out.println("[6] Custom Search");
            System.out.println("[0] Back");
            System.out.print("Choose an option [0-6]: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("\n[Month to Date]");
                    showMonthToDate();
                    break;

                case 2:
                    System.out.println("\n[Previous Month]");
                    showPreviousMonth();
                    break;
                case 3:
                    System.out.println("\n[Year to Date]");
                    showYearToDate();
                    break;
                case 4:
                    System.out.println("\n[Previous Year]");
                    showPreviousYear();
                    break;
                case 5:
                    System.out.println("\n[Search by Vendor]");
                    System.out.print("Enter Vendor Name: ");
                    String vendorName = scanner.nextLine();
                    showTransactionVendor(vendorName);
                    break;
                case 6:
                    System.out.println("\n[Custom Search]");
                    List<Transaction> transactions = readTransactionsFromCSV();
                    customSearch(transactions);
                    break;
                case 0:
                    stayInReportsMenu = false;
                    break;
                default:
                    System.out.println("\n Invalid choice, Please try again.");
            }

        }
    }
    private static void customSearch(List<Transaction> transactions){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter start date (yyyy-MM-dd) or press Enter to skip: ");
        String startDateInput = scanner.nextLine().trim();

        System.out.print("Enter end date (yyyy-MM-dd) or press Enter to skip: ");
        String endDateInput = scanner.nextLine().trim();

        System.out.print("Enter description or press Enter to skip: ");
        String descriptionInput = scanner.nextLine().trim();

        System.out.print("Enter vendor name or press Enter to skip: ");
        String vendorInput = scanner.nextLine().trim();

        System.out.print("Enter amount or press Enter to skip: ");
        String amountInput = scanner.nextLine().trim();

        double amount = 0;
        boolean filterByAmount = false;
        if (!amountInput.isEmpty()) {
            amount = Double.parseDouble(amountInput);
            filterByAmount = true;
        }
        System.out.println("\nResults");
        for (Transaction transaction : transactions){
            boolean matches = true;

            if(!startDateInput.isEmpty() && transaction.getDate().compareTo(startDateInput) < 0){
                matches = false;
            }

            if(!endDateInput.isEmpty() && transaction.getDate().compareTo(endDateInput)>0){
                matches = false;
            }
            if(!descriptionInput.isEmpty() && !transaction.getDescription().toLowerCase().contains(descriptionInput.toLowerCase())){
                matches = false;
            }
            if(!vendorInput.isEmpty() && !transaction.getVendor().toLowerCase().contains(vendorInput.toLowerCase())){
                matches = false;
            }
            if (filterByAmount && transaction.getAmount() != amount){
                matches = false;
            }
            if(matches){
                System.out.println(transaction);
            }

        }
    }

    private static void showMonthToDate(){
        List<Transaction> transactions = readTransactionsFromCSV();
        LocalDate today = LocalDate.now();
        String monthPrefix = today.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        for (Transaction transaction : transactions){
            if (transaction.getMonth().equals(monthPrefix)){
                System.out.println(transaction);
            }
        }
    }
    private static void showYearToDate(){
        List<Transaction> transactions = readTransactionsFromCSV();
        String currentYear = String.valueOf(LocalDate.now().getYear());

        for (Transaction transaction : transactions){
            if (transaction.getYear().equals(currentYear)){
                System.out.println(transaction);
            }
        }
    }
    private static void showPreviousMonth(){
        List<Transaction> transactions = readTransactionsFromCSV();
        LocalDate today = LocalDate.now();
        LocalDate previousMonth = today.minusMonths(1);
        String previousMonthPrefix = previousMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        for (Transaction transaction : transactions){
            if (transaction.getMonth().equals(previousMonthPrefix)){
                System.out.println(transaction);
            }
        }
    }
    private static void showPreviousYear(){
        List<Transaction> transactions = readTransactionsFromCSV();
        String previousYear = String.valueOf(LocalDate.now().getYear()-1);

        for (Transaction transaction : transactions){
            if (transaction.getYear().equals(previousYear)){
                System.out.println(transaction);
            }
        }
    }
    private static void showTransactionVendor(String vendorName){
        List<Transaction> transactions = readTransactionsFromCSV();
        for (Transaction transaction : transactions){
            if (transaction.getVendor().equalsIgnoreCase(vendorName)){
                System.out.println(transaction);
            }
        }
    }
    private static List<Transaction> readTransactionsFromCSV(){
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine){
                    firstLine = false;
                    continue;
                }
                String[] transactionParts = line.split("\\|");

                if(transactionParts.length < 5)continue;

                String date = transactionParts[0];
                String time = transactionParts[1];
                String description = transactionParts[2];
                String vendor = transactionParts[3];
                String amount = transactionParts[4];

                transactions.add(new Transaction(date, time, description,vendor,Double.parseDouble(amount)));
            }
        } catch (IOException e){
                System.out.println("Error reading file: " + e.getMessage());

        }Collections.reverse(transactions);
        return transactions;
    }
}
