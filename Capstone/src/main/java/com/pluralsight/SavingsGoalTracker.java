package com.pluralsight;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.*;
import java.util.Scanner;

public class SavingsGoalTracker {
    private static final String GOAL_FILE = "goal.txt";
    private static final String SAVINGS_FILE = "savings.txt";
    private static final String TRANSACTION_FILE = "transactions.csv";

    public static void savingsMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n***** PIGGY BANKY - SAVINGS *****");
        System.out.println(" ^  ^\n(oo)   <- oink oink!\n(____)\n^^ ^^\n\n");
        double goalAmount = getGoalAmount();
        double currentSavings = getCurrentSavings();

        saveCurrentSavings(currentSavings);
        showProgress(currentSavings, goalAmount);

            System.out.println("Would you like to add to your savings?");
            System.out.println("\n[1] YES");
            System.out.println("[2] NO/EXIT");
            System.out.print("\nPlease select an option (1 or 2): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                transferToSavings();
                }
    }
    public static void transferToSavings() {
        double currentChecking = getNetFundsFromTransactions();
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Current available to transfer is $%.2f\n",currentChecking);
        System.out.print("How much would you like to transfer to your piggy bank? $");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String transactionLine = timestamp + "|Savings Transfer|Savings|-" + amount;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.newLine();
            writer.write(transactionLine);
            System.out.println("Transaction added to CSV.");
        } catch (IOException e) {
            System.out.println("Failed to write to transactions file.");
        }

        double currentSavings = getCurrentSavings();
        currentSavings += amount;
        saveCurrentSavings(currentSavings);
        System.out.printf("$%.2f added to piggy bank!\n", amount);
    }

    public static double getGoalAmount() {
        File file = new File(GOAL_FILE);

        if (file.exists()) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                return Double.parseDouble(content.trim());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading goal. Setting new one.");
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your savings goal: $");
        double goal = scanner.nextDouble();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(String.valueOf(goal));
        } catch (IOException e) {
            System.out.println("Failed to save goal.");
        }

        return goal;
    }

    public static double getCurrentSavings() {
        File file = new File(SAVINGS_FILE);
        if (file.exists()) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                return Double.parseDouble(content.trim());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error reading savings. Starting at $0.");
            }
        }
        return 0;
    }

    public static void saveCurrentSavings(double amount) {
        try (FileWriter writer = new FileWriter(SAVINGS_FILE)) {
            writer.write(String.valueOf(amount));
        } catch (IOException e) {
            System.out.println("Failed to save current savings.");
        }
    }
    public static double getNetFundsFromTransactions() {
        double currentChecking = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    try {
                        double amount = Double.parseDouble(parts[4]);
                        currentChecking += amount;
                    } catch (NumberFormatException e) {
                        System.out.println("Error reading transaction."+e);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions.");
        }
        return currentChecking;
    }

    public static void showProgress(double currentSavings, double goalAmount) {
        int totalBars = 30;
        double ratio = currentSavings / goalAmount;
        int filledBars = (int)(ratio * totalBars);

        StringBuilder progressBar = new StringBuilder("[");
        for (int i = 0; i < totalBars; i++) {
            progressBar.append(i < filledBars ? "#" : "-");
        }
        progressBar.append("]");

        System.out.printf("Savings Goal: $%.2f / $%.2f\n", currentSavings, goalAmount);
        System.out.println(progressBar + " " + (int)(ratio * 100) + "%");

        if (currentSavings >= goalAmount) {
            System.out.println("Goal Reached! Your piggy bank is proud! 🐷");
        }
    }
}
