public class CreditAccount extends Account {
    private float _initialCredit;


    public CreditAccount(float initialCredit)
    {
        _balance=initialCredit;
        _initialCredit = initialCredit;
    }

    public float getInitialCredit()
    {
        return _initialCredit;
    }

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

    public boolean needsPayment(){
        return _balance<_initialCredit;
    }
}
