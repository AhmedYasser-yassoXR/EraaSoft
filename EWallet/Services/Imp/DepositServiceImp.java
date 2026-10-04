package EWallet.Services.Imp;

import EWallet.Services.TransferService;
import EWallet.V1.Account;
import EWallet.V1.WalletSys;
import java.util.Scanner;
import EWallet.Services.DepositService;

public class DepositServiceImp implements DepositService {

    private final WalletSys walletSys;
    private final Scanner input;

    public DepositServiceImp(WalletSys walletSys, Scanner input) {
        this.walletSys = walletSys;
        this.input = input;
    }
    public void deposit(Account account) {

        System.out.print("Enter amount to deposit: ");

        try {

            double amount = Double.parseDouble(input.nextLine());

            if (amount <= 5) {
                System.out.println("Amount must be greater than 5.");
                return;
            }

            account.setBalance(
                    account.getBalance() + amount
            );

            System.out.println("Deposit successful.");

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