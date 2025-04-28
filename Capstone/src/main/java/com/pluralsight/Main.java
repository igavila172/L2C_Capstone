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
        System.out.println("***** WELCOME TO THE LEDGER APP *****");

        while (running){
            System.out.println("[1] Add Deposit");
            System.out.println("[2] Make Payment (Debit)");
            System.out.println("[3] View Ledger");
            System.out.println("[4] Exit");
            System.out.print("\nPlease select an option (1-4): ");

            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case 1 -> runDeposit(scanner);
                case 2 -> runPayment(scanner);
                case 3 -> runLedger(scanner);
                case 4 -> {
                    running = false;
                    System.out.println("Thank you for banking with LEDGER. Goodbye!");
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

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            String date = now.format(dateFormatter);
            String time = now.format(timeFormatter);

            String transactionLine = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;


            FileWriter writer = new FileWriter("transactions.csv", true);
            writer.write(transactionLine + "\n");
            writer.close();

            System.out.println("Deposit was successful!");
        } catch (IOException e) {
            System.out.println("error recording deposit, try again.");
        } catch (NumberFormatException e){
            System.out.println("Invalid amount entered. Please enter a valid number.");
        }
    }
}