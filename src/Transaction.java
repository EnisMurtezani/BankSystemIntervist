class Transaction {
    private double amount;
    private Account originAccount;
    private Account targetAccount; // This may be null for deposits/withdrawals
    private String transactionReason;
    private TransactionType type;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL, TRANSFER
    }

    // Constructor for deposits and withdrawals
    public Transaction(double amount, Account account, String transactionReason, TransactionType type) {
        this.amount = amount;
        this.originAccount = account;
        this.transactionReason = transactionReason;
        this.type = type;
    }

    // Constructor for transfers
    public Transaction(double amount, Account originAccount, Account targetAccount, String transactionReason) {
        this.amount = amount;
        this.originAccount = originAccount;
        this.targetAccount = targetAccount;
        this.transactionReason = transactionReason;
        this.type = TransactionType.TRANSFER;
    }

    public void execute() throws InsufficientFundsException {
        switch (type) {
            case DEPOSIT:
                originAccount.deposit(amount);
                break;
            case WITHDRAWAL:
                originAccount.withdraw(amount);
                break;
            case TRANSFER:
                if (originAccount != null && targetAccount != null) {
                    double fee = originAccount.getBank().applyTransactionFee(amount, type);
                    originAccount.withdraw(amount + fee);  // Deduct the amount and the fee
                    targetAccount.deposit(amount);  // Deposit only the amount
                } else {
                    throw new InsufficientFundsException("Transfer failed. Account not found.");
                }
                break;
        }
        System.out.println("Transaction completed: " + type + " | " + transactionReason);
    }
}