package com.pluralsight;
import java.util.Scanner;
public class runLedger {

    public static void displayLedgerMenu() {
        Scanner scanner = new Scanner(System.in);

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
                    // insert method here :))
                    break;
                case 2:
                    System.out.println("\n[Showing all DEPOSITS]");
                    // insert method here :))
                    break;
                case 3:
                    System.out.println("\n[Showing all PAYMENTS]");
                    // insert method here :))
                    break;
                case 4:
                    System.out.println("\n[Showing all REPORTS]");
                    // insert method here :))
                    break;
                case 5:
                    System.out.println("\n[Returning to HOME]");
                    // insert method here :))
                    break;
                default:
                    System.out.println("\nInvalid choice, Please try again.");
            }


        }

    }
