package EWallet.V1;

public class Account {

    private String userName;
    private String password;
    private Double balance;
    private String phoneNumber;
    private int age;

    public Account(String userName, String password, String phoneNumber, int age) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.balance = 0.0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
