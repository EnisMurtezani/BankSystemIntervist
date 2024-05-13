class Account {
    private String accountId;
    private String userName;
    private double balance;
    private Bank bank;

    public Account(String accountId, String userName, double initialBalance, Bank bank) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = initialBalance;
        this.bank = bank;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + String.format("%.2f", amount) + " | New Balance: $" + String.format("%.2f", balance));
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + String.format("%.2f", amount) + " | New Balance: $" + String.format("%.2f", balance));
        } else {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public Bank getBank() {
        return bank;
    }
}