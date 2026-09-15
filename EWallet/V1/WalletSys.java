package EWallet.V1;

import java.util.ArrayList;
import java.util.List;
import EWallet.V1.Account;

public class WalletSys {

    public final static String name = "E-Wallet";

    private List<Account> accounts = new ArrayList<>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }


    public boolean isUsernameExists(String username) {

        for (Account account : accounts) {
            if (username.equals(account.getUserName())) {
                return true;
            }
        }

        return false;
    }
    public Account findAccountByUsername(String username) {

        for (Account account : accounts) {
            if (account.getUserName().equals(username)) {
                return account;
            }
        }

        return null;
    }

    public boolean isPhoneNumberExists(String phoneNumber) {
        for (Account account : accounts) {
            if (phoneNumber.equals(account.getPhoneNumber())) {
                return true;
            }

        }
        return false;

    }
}