package com.pluralsight;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        //break;//JUST KIDDING I'M NOT BROKEN
        System.out.println("\n***** WELCOME TO PIGGY BANKY *****");
        System.out.println(" ^  ^\n(oo)   <- oink oink!\n(____)\n^^ ^^\n");


        while (running){
            System.out.printf("CURRENT BALANCE | $%.2f\n",SavingsGoalTracker.getNetFundsFromTransactions());
            System.out.println("----------------");
            System.out.println("[1] Add Deposit");
            System.out.println("[2] Make Payment (Debit)");
            System.out.println("[3] View Ledger");
            System.out.println("[4] Savings Account");
            System.out.println("[5] Exit");
            System.out.print("\nPlease select an option (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> runDeposit(scanner);
                case 2 -> runPayment(scanner);
                case 3 -> RunLedger.displayLedgerMenu();
                case 4 -> SavingsGoalTracker.savingsMenu();
                case 5 -> {
                    running = false;
                    System.out.println("Thank you for OINKING with PIGGY BANKY. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please enter 1–4.");
            }
        }
        scanner.close();
    }
    private static void runDeposit(Scanner scanner) {
        try {
            System.out.print("Enter Deposit Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter Vendor Name: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter Deposit Amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            saveTransaction(description, vendor, amount);

            System.out.println("Deposit was successful!");
        } catch (IOException e) {
            System.out.println("error recording deposit, try again.");
        } catch (NumberFormatException e){
            System.out.println("Invalid amount entered. Please enter a valid number.");
        }
    }
    private static void runPayment(Scanner scanner){
        try{
            System.out.print("Enter Payment Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor name: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter Payment Amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            amount = -Math.abs(amount);

            saveTransaction(description, vendor, amount);

            System.out.println("Payment was successful!");

        } catch (IOException e) {
            System.out.println("error recording payment, try again.");
        } catch (NumberFormatException e){
            System.out.println("Invalid amount entered. Please enter a valid number.");
        }
    }
    private static void saveTransaction(String description, String vendor, double amount)
        throws IOException{
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        String transactionLine = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;


        FileWriter writer = new FileWriter("transactions.csv", true);
        writer.write(transactionLine + "\n");
        writer.close();
    }
}