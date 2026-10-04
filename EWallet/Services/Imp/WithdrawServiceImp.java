package EWallet.Services.Imp;

import EWallet.Services.TransferService;
import EWallet.Services.WithdrawService;
import EWallet.V1.Account;
import EWallet.V1.WalletSys;
import java.util.Scanner;


public class WithdrawServiceImp implements WithdrawService {

    private final WalletSys walletSys;
    private final Scanner input;

    public WithdrawServiceImp(WalletSys walletSys, Scanner input) {
        this.walletSys = walletSys;
        this.input = input;
    }
    public void withdraw(Account account) {

        System.out.print("Enter amount to withdraw: ");

        try {

            double amount = Double.parseDouble(input.nextLine());

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
                return;
            }

            if (amount > account.getBalance()) {
                System.out.println("Insufficient balance.");
                return;
            }

            account.setBalance(
                    account.getBalance() - amount
            );

            System.out.println("Withdraw successful.");

            System.out.println(
                    "Current balance: " + account.getBalance()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid amount! Please enter a number."
            );
        }
    }
}