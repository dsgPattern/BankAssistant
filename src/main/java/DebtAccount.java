public class DebtAccount extends Account {
    @Override
    public void deposit(float amount) {
        if (amount <= 0) return;
        if (amount > _balance) {
            System.out.println("Amount is larger than Debt balance");
            return;
        }
        _balance -= amount;
    }

    public void addDebt(float amount) {
        if (amount <= 0) return;
        _balance += amount;
    }
}
