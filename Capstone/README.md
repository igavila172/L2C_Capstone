# 🐷 Project Name: Piggy Banky

Welcome to **Piggy Banky** a simple Java-based savings tracker and ledger app. Log deposits, payments, and view reports from your command-line piggy bank. Oink oink!

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Usage](#usage)
- [Code Example](#code-example)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [File Format](#file-format)
- [Credits](#credits)

---

## 📝 Overview

Piggy Banky is a command-line app written in Java that helps users:

- Track deposits and debits
- View all transactions in a ledger
- Filter by vendor, date, or transaction type
- Bonus: Monitor savings progress

---

## ⭐ Features

- [x] Add deposits and payments
- [x] View all transactions
- [x] Generate date and vendor-based reports
- [x] Track net savings

---

## 🚀 Usage

Once the app starts, you'll be greeted with a menu:

```
***** WELCOME TO PIGGY BANKY *****
 ^  ^
(oo)   <- oink oink!
(____)
^^ ^^

CURRENT BALANCE | $123.45
----------------
[1] Add Deposit
[2] Make Payment (Debit)
[3] View Ledger
[4] Savings Account
[5] Exit
```

---

## 📦 Code Example

### Main.java

```java
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running){
        System.out.printf("CURRENT BALANCE | $%.2f\n", SavingsGoalTracker.getNetFundsFromTransactions());
        // ... menu options ...
        switch (choice) {
            case 1 -> runDeposit(scanner);
            case 2 -> runPayment(scanner);
            case 3 -> RunLedger.displayLedgerMenu();
            case 5 -> running = false;
        }
    }
}
```

### Save Transaction Format

Each transaction is saved in a CSV file using:

```java
String transactionLine = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
```

---

## 📊 File Format

Each transaction is stored in `transactions.csv` using this pipe-delimited format:

```
2025-04-28|15:42:10|Paycheck|Employer Inc|2000.00
2025-04-28|17:18:09|Groceries|Trader Joe's|-75.32
```

---

## 🖼️ Screenshots

```markdown
![Piggy Bank ASCII Art](images/PiggyScreenshot.png)
```

---

## 🔧 Installation

1. Clone the repo:
   ```bash
   git clone https://github.com/yourusername/piggy-banky.git
   ```
2. Open the project in your IDE
3. Run `Main.java`

---

## 🙌 Credits

Created with ❤️ by [Your Name] as part of a Pluralsight Java project.