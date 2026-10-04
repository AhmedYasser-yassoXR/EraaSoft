package EWallet.Services.Imp;

import EWallet.Global.Validation;
import EWallet.Services.TransferService;
import EWallet.V1.Account;
import EWallet.V1.WalletSys;

import java.util.Scanner;

public class TransferServiceImp implements TransferService {

    private final WalletSys walletSys;
    private final Scanner input;

    public TransferServiceImp(WalletSys walletSys, Scanner input) {
        this.walletSys = walletSys;
        this.input = input;
    }

    @Override
    public void transfer(Account sender) {

        // Validate sender
        if (sender == null) {
            System.out.println("Sender account cannot be null.");
            return;
        }

        // Receiver username
        System.out.print("Enter receiver username: ");
        String receiverUsername = input.nextLine();

        if (!Validation.isValidInput(receiverUsername)) {
            System.out.println("Username cannot be null, empty, or blank.");
            return;
        }

        // Find receiver
        Account receiver =
                walletSys.findAccountByUsername(receiverUsername);

        if (receiver == null) {
            System.out.println("Receiver account doesn't exist.");
            return;
        }

        // Cannot transfer to himself
        if (receiver == sender) {
            System.out.println(
                    "You cannot transfer money to yourself."
            );
            return;
        }

        // Amount
        System.out.print("Enter amount to transfer: ");
        String amountInput = input.nextLine();

        if (!Validation.isValidInput(amountInput)) {
            System.out.println("Amount cannot be null, empty, or blank.");
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountInput);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid amount! Please enter a valid number."
            );
            return;
        }

        // Amount validation
        if (amount <= 0) {
            System.out.println(
                    "Amount must be greater than zero."
            );
            return;
        }

        // Balance validation
        if (amount > sender.getBalance()) {
            System.out.println(
                    "Insufficient balance."
            );
            return;
        }

        // Transfer
        sender.setBalance(
                sender.getBalance() - amount
        );

        receiver.setBalance(
                receiver.getBalance() + amount
        );

        System.out.println("Transfer successful.");

        System.out.println(
                "Transferred "
                        + amount
                        + " to "
                        + receiver.getUserName()
        );

        System.out.println(
                "Current balance: "
                        + sender.getBalance()
        );
    }
}