package EWallet.Services.Imp;

import EWallet.Global.Validation;
import EWallet.Services.ApplicationService;
import EWallet.Services.TransferService;
import EWallet.Services.DepositService;
import EWallet.Services.WithdrawService;
import EWallet.V1.Account;
import EWallet.V1.WalletSys;
import java.util.Scanner;

public class ApplicationServiceImp implements ApplicationService {

    private final WalletSys walletSys;
    private final Scanner input;
    private final TransferService transferService;
    private final DepositService depositService;
    private final WithdrawService withdrawService;

    public ApplicationServiceImp() {
        this.walletSys = new WalletSys();
        this.input = new Scanner(System.in);
        this.transferService = new TransferServiceImp(walletSys, input);
        this.depositService = new DepositServiceImp(walletSys, input);
        this.withdrawService = new WithdrawServiceImp(walletSys, input);
    }

    // ============================= START =============================

    @Override
    public void start() {

        int count = 0;

        while (true) {

            System.out.println("\n===== E-Wallet System =====");
            System.out.println("1- Login");
            System.out.println("2- Sign Up");
            System.out.println("3- Exit");
            System.out.print("Choose: ");

            try {

                int choose = Integer.parseInt(input.nextLine());

                switch (choose) {

                    case 1:
                        count = 0;

                        System.out.println("\n===== Login =====");
                        login();
                        break;

                    case 2:
                        count = 0;

                        System.out.println("\n===== Sign Up =====");
                        signup();
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println(
                                "Invalid choice! Please choose 1, 2, or 3."
                        );
                        count++;
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter a number."
                );

                count++;
            }

            if (count >= 3) {
                System.out.println(
                        "Please contact the support team."
                );
                break;
            }
        }
    }

    // --------------------- SIGN UP ---------------------

    @Override
    public void signup() {

        String userName;
        String password;
        String phoneNumber;
        int age;


        // ---------------- USERNAME ----------------

        while (true) {

            System.out.print("Enter username: ");
            userName = input.nextLine();

            if (!Validation.isValidInput(userName)) {
                System.out.println("Username cannot be null, empty, or blank.");
                continue;
            }

            if (userName.length() < 3 || userName.length() > 20) {
                System.out.println("Username must be between 3 and 20 characters.");
                continue;
            }

            if (!Character.isUpperCase(userName.charAt(0))) {

                System.out.println(
                        "First letter must be uppercase."
                );

                continue;
            }

            if (walletSys.isUsernameExists(userName)) {

                System.out.println(
                        "Username already exists."
                );

                continue;
            }

            System.out.println("Username is valid!");
            break;
        }


        // ---------------- PASSWORD ----------------

        while (true) {

            System.out.print("Enter password: ");
            password = input.nextLine();

            if (!isValidPassword(password)) {
                continue;
            }

            System.out.println("Password is valid!");
            break;
        }


        // ---------------- PHONE NUMBER ----------------

        while (true) {

            System.out.print("Enter phone number: ");
            phoneNumber = input.nextLine();

            // Egyptian phone:
            // 010xxxxxxxx
            // 011xxxxxxxx
            // 012xxxxxxxx
            // 015xxxxxxxx

            if (!phoneNumber.matches("^01[0125][0-9]{8}$")) {

                System.out.println(
                        "Invalid phone number."
                );

                continue;
            }

            if (walletSys.isPhoneNumberExists(phoneNumber)) {

                System.out.println(
                        "Phone number already exists."
                );

                continue;
            }

            System.out.println("Phone number is valid!");
            break;
        }


        // ---------------- AGE ----------------

        while (true) {

            System.out.print("Enter age: ");

            try {

                age = Integer.parseInt(input.nextLine());

                if (age < 18 || age > 100) {

                    System.out.println(
                            "Age must be between 18 and 100."
                    );

                    continue;
                }

                System.out.println("Age is valid!");
                break;

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid age! Please enter a number."
                );
            }
        }


        // ---------------- STORE ACCOUNT ----------------

        Account account =
                new Account(userName, password, phoneNumber, age);

        walletSys.addAccount(account);

        System.out.println("\nSign up successful!");
        System.out.println(
                "Welcome " + userName
        );


        // For testing only
        System.out.println(
                "Number of accounts: "
                        + walletSys.getAccounts().size()
        );

        System.out.println(
                "Username exists: "
                        + walletSys.isUsernameExists(userName)
        );

        System.out.println(
                "Phone exists: "
                        + walletSys.isPhoneNumberExists(phoneNumber)
        );
    }

    // --------------------- LOGIN ---------------------
    @Override
    public void login() {

        System.out.print("Enter username: ");
        String username = input.nextLine();

        Account account = walletSys.findAccountByUsername(username);

        if (account == null) {
            System.out.println(
                    "The account doesn't exist, please create an account."
            );
            return;
        }

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter password: ");
            String password = input.nextLine();

            // Correct password
            if (account.getPassword().equals(password)) {

                System.out.println(
                        "Welcome " + account.getUserName()
                );


                userMenu(account);
                return;
            }

            // Wrong password
            attempts++;

            System.out.println("Wrong password");

            // If user still has attempts
            if (attempts < 3) {

                System.out.println(
                        "Remaining attempts: " + (3 - attempts)
                );
            }
        }

        // --------------------- 3 WRONG ATTEMPTS ---------------------

        System.out.println("\nInvalid attempts");

        while (true) {

            System.out.println("1- Forget Password");
            System.out.println("2- Exit");
            System.out.print("Choose: ");

            String choice = input.nextLine();

            switch (choice) {

                case "1":
                    forgetPassword(account);
                    return;

                case "2":
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println(
                            "Invalid choice! Please choose 1 or 2."
                    );
            }
        }
    }

    // --------------------- FORGET PASSWORD ---------------------

    public void forgetPassword(Account account) {

        System.out.println(
                "\n===== Forget Password ====="
        );


        // ---------------- VERIFY PHONE ----------------

        while (true) {

            System.out.print(
                    "Enter your registered phone number: "
            );

            String phoneNumber = input.nextLine();

            if (!account.getPhoneNumber().equals(phoneNumber)) {

                System.out.println(
                        "Phone number is incorrect."
                );

                continue;
            }

            System.out.println(
                    "Phone number verified!"
            );

            break;
        }


        // ---------------- NEW PASSWORD ----------------

        while (true) {

            System.out.print("Enter new password: ");
            String newPassword = input.nextLine();

            if (!isValidPassword(newPassword)) {
                continue;
            }


            // prevent using same old password
            if (account.getPassword().equals(newPassword)) {

                System.out.println(
                        "New password cannot be the same as the old password."
                );

                continue;
            }


            account.setPassword(newPassword);

            System.out.println(
                    "Password changed successfully!"
            );

            break;
        }
    }

    // --------------------- PASSWORD VALIDATION ---------------------

    private boolean isValidPassword(String password) {

        if (password.length() < 8 || password.length() > 16) {

            System.out.println(
                    "Password must be between 8 and 16 characters."
            );

            return false;
        }


        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSymbol = false;


        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {

                hasUppercase = true;

            } else if (Character.isLowerCase(c)) {

                hasLowercase = true;

            } else if (Character.isDigit(c)) {

                hasNumber = true;

            } else {

                hasSymbol = true;
            }
        }


        if (!hasUppercase ||
                !hasLowercase ||
                !hasNumber ||
                !hasSymbol) {

            System.out.println(
                    "Password must contain at least one uppercase, "
                            + "lowercase, number, and symbol."
            );

            return false;
        }


        return true;
    }

    // --------------------- UserMenu ---------------------

    public void userMenu(Account account) {
        while (true) {
            System.out.println("=========UserMenu========");
            System.out.println("1- Deposit");
            System.out.println("2- Withdraw");
            System.out.println("3- Transfer");
            System.out.println("4- Show Account details");
            System.out.println("5- Change password");
            System.out.println("6- Logout");
            String choice = input.nextLine();

            switch (choice) {

                case "1":
                    depositService.deposit(account);
                    break;

                case "2":
                    withdrawService.withdraw(account);
                    break;

                case "3":
                    transferService.transfer(account);
                    break;

                case "4":
                    showAccountDetails(account);
                    break;

                case "5":
                    changePassword(account);
                    break;

                case "6":
                    logout();
                    return;

                default:
                    System.out.println(
                            "Invalid choice! Please choose from 1 to 6."
                    );
            }
        }
    }


    public void showAccountDetails(Account account) {

        System.out.println("\n===== Account Details =====");

        System.out.println(
                "Username: " + account.getUserName()
        );

        System.out.println(
                "Phone Number: " + account.getPhoneNumber()
        );

        System.out.println(
                "Age: " + account.getAge()
        );

        System.out.println(
                "Balance: " + account.getBalance()
        );
    }

    //---------------------ChangePassword---------------------

    public void changePassword(Account account) {

        System.out.print("Enter current password: ");
        String currentPassword = input.nextLine();

        if (!account.getPassword().equals(currentPassword)) {

            System.out.println(
                    "Wrong password."
            );

            return;
        }

        while (true) {

            System.out.print("Enter new password: ");
            String newPassword = input.nextLine();

            if (!isValidPassword(newPassword)) {
                continue;
            }

            if (account.getPassword().equals(newPassword)) {

                System.out.println(
                        "New password cannot be the same as the old password."
                );

                continue;
            }

            account.setPassword(newPassword);

            System.out.println(
                    "Password changed successfully."
            );

            break;
        }
    }

    // ---------------------Logout---------------------
    @Override
    public void logout() {
        System.out.println("Goodbye!");
        return;
    }
}