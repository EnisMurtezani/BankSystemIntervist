import java.util.HashMap;

class Bank {
    private String bankName;
    private HashMap<String, Account> accounts = new HashMap<>();
    private double totalTransactionFees = 0;
    private double totalTransferAmount = 0;
    private double flatFeeAmount = 10.0;
    private double percentFeeValue = 0.05;

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public void transfer(String fromAccountId, String toAccountId, double amount, String reason) throws AccountNotFoundException, InsufficientFundsException {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);
        if (fromAccount == null || toAccount == null) {
            throw new AccountNotFoundException("One or both accounts not found.");
        }
        Transaction transaction = new Transaction(amount, fromAccount, toAccount, reason);
        transaction.execute();
    }

    public double applyTransactionFee(double amount, Transaction.TransactionType type) {
        double fee = (type == Transaction.TransactionType.TRANSFER) ? flatFeeAmount + (amount * percentFeeValue) : flatFeeAmount;
        totalTransactionFees += fee;
        return fee;
    }

    public void reportFees() {
        System.out.println("Total transaction fees collected: $" + String.format("%.2f", totalTransactionFees));
        System.out.println("Total transfer amount: $" + String.format("%.2f", totalTransferAmount));
    }
}