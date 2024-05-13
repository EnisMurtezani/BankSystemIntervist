public class Main {
    public static void main(String[] args) {
        try {
            Bank myBank = new Bank("Demo Bank");
            myBank.addAccount(new Account("001", "Alice", 1000.0, myBank));
            myBank.addAccount(new Account("002", "Bob", 500.0, myBank));

            myBank.transfer("001", "002", 100.0, "Rent payment");
            myBank.reportFees();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}