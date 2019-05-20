public class SavingsAccount extends Account {

    public boolean withdraw(float amount)
    {
        if(amount<=0) {
            System.out.println("Amount has to be positive");
            return false;
        }

        if(amount>_balance) {
            System.out.println("Amount exceeds existing balance");
            return false;
        }
        _balance-=amount;
        return true;
    }
}
