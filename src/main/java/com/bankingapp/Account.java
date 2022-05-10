package com.bankingapp;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Account {
    private String customerId;
    private String customerName;
    private String pin;
    private int balance;
    private int previousTransaction;

    public Account(String customerId) {
        this.customerId = customerId;
    }

    public void deposit(int amount) {
        if(amount > 0) {
            balance = balance + amount;
            previousTransaction = amount;
        }
    }

    public void withdraw(int amount) {
            if (amount > 0) {
                balance = balance - amount;
                previousTransaction = -amount;
            }
    }

    public void getPreviousTransaction() {
        if(previousTransaction > 0) {
            System.out.println("Deposited: " + previousTransaction);
        } else if (previousTransaction < 0) {
            System.out.println("Withdrew: " + previousTransaction);
        } else {
            System.out.println("No transaction has previously occurred.");
        }
    }

    public void displayMenu() {
        char option = 'a';
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("Welcome to the banking system! Please input your name.");
        customerName = scanner.next();
        System.out.println("Now please enter a 4 digit pin number. Make sure you remember your pin!");
        pin = scanner.next();
        // Ensures that a user enters a 4 digit pin. If they don't they will be prompted to try again
        if(pin.length() != 4) {
            do {
                System.out.println("Make sure you enter a 4 digit pin. Try again.");
                pin = scanner.next();
            } while (pin.length() != 4);
        }
        System.out.println("Hi " + customerName);
        System.out.println("Your id is " + customerId);
        System.out.println();
        System.out.println("What would you like to do today?");
        System.out.println("------------------------------------------");
        System.out.println("A. Check your current balance");
        System.out.println("B. Make a deposit");
        System.out.println("C. Make a withdrawal");
        System.out.println("D. View your previous transaction");
        System.out.println("E. Exit");

        do {
            System.out.println();
            System.out.println("Enter an option.");
            char option1 = scanner.next().charAt(0);
            option = Character.toUpperCase(option1);
            System.out.println();

            switch(option) {
                case 'A':
                    // Option A allows a user to check their balance
                    System.out.println("========================");
                    System.out.println("Your current balance is: $" + balance);
                    System.out.println("========================");
                    break;
                case 'B':
                    // Option B allows a user to make a deposit
                    System.out.println("Please enter your 4 digit pin.");
                    String inputedPin = scanner.next();
                    if(inputedPin.equals(pin)) {
                        System.out.println("How much would you like to deposit?");
                        int depositAmount = scanner.nextInt();
                        deposit(depositAmount);
                        System.out.println("========================");
                        System.out.println("Deposited: $" + depositAmount);
                        System.out.println("========================");
                        break;
                    }
                        System.out.println("Wrong pin number. Re-enter an option and try again.");
                    break;
                case 'C':
                    // Option C allows a user to make a withdrawal
                    System.out.println("Please enter your 4 digit pin.");
                    String inputedPin2 = scanner.next();
                    if(inputedPin2.equals(pin)) {
                        if (balance > 0) {
                            System.out.println("How much would you like to withdraw?");
                            int withdrawalAmount = scanner.nextInt();
                            if (withdrawalAmount <= balance) {
                                withdraw(withdrawalAmount);
                                System.out.println("========================");
                                System.out.println("Withdrawn: $" + withdrawalAmount);
                                System.out.println("========================");
                                break;
                            }
                            System.out.println("Withdrawal amount exceeds your current balance.");
                            break;
                        }
                    }
                    if(!inputedPin2.equals(pin)) {
                        System.out.println("Wrong pin number. Re-enter an option and try again.");
                    }
                    if(balance == 0) {
                        System.out.println("You have no money in your bank account to withdraw from.");
                    }
                    break;
                case 'D':
                    //Option D allows a user to view their previous transaction
                    System.out.println("========================");
                    getPreviousTransaction();
                    System.out.println("========================");
                    break;
                case 'E':
                    //Option E exits out the application
                    System.out.println("========================");
                    break;
                default:
                    System.out.println("========================");
                    System.out.println("Invalid option! Please enter either A, B, C, D or E.");
                    break;
            }
        } while (option != 'E');
        System.out.println("Thank you for banking with us!");
        System.out.println("========================");
    }

}
