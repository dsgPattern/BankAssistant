public abstract class Account {
    protected float _balance=0;

    public float getBalance()
    {
        return _balance;
    }

    public void deposit(float amount)
    {
        if(amount <=0)return;
        _balance+=amount;
    }
}
