import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankAssistant {
    private Client _currentClient;

    public void setCurrentClient(Client client) {
        _currentClient = client;
    }

    public Client createClient(String name) {
        return new Client(name);
    }

    public void createSavingAccount(float initialAmount) {
        if (_currentClient.equals(null)) return;
        SavingsAccount savings = new SavingsAccount();
        savings.deposit(initialAmount);
        _currentClient.addAccount(savings);
    }

    public void tranferAmount(SavingsAccount fromAccount, Account toAccount, float amount) {
        if (_currentClient.equals(null)) return;

        if (fromAccount.withdraw(amount))
            toAccount.deposit(amount);
        else
            System.out.println("Insuficient founds");
    }

    public void AddDebt(float amount) {
        DebtAccount debtAccount = getDebtAccount();
        if (debtAccount == null) {
            debtAccount = new DebtAccount();
            debtAccount.addDebt(amount);
            _currentClient.addAccount(debtAccount);
            return;
        }

        debtAccount.addDebt(amount);
    }

    public void getNewCredit(float amount) {
        DebtAccount debtAccount = getDebtAccount();
        if (debtAccount != null) {
            System.out.println("You have a debt of " + debtAccount.getBalance());
            return;
        }

        List<CreditAccount> creditAccounts = getCreditAccounts();
        float sumOfCredits = amount;
        for (CreditAccount credit : creditAccounts
                ) {
            sumOfCredits += credit.getBalance();
        }

        SavingsAccount savingsAccount = getSavingsAccount();
        if (savingsAccount == null) {
            System.out.println("You have no savings, you are not eligible");
            return;
        }

        if (sumOfCredits / 2 < savingsAccount.getBalance()) {
            CreditAccount creditAccount = new CreditAccount(amount);
            _currentClient.addAccount(creditAccount);
            System.out.println("New credit account with "+amount);
        } else
            System.out.println("Too many credits");
    }

    public void payCredit(float amount) {
        DebtAccount debtAccount = getDebtAccount();
        float amountToDeposit = amount;
        if (debtAccount!=null) {
            float debtBalance = debtAccount.getBalance();
            if (debtBalance <= amount) {
                amountToDeposit -= debtBalance;
                debtAccount.deposit(debtBalance);
                _currentClient.deleteAccount(debtAccount);
            } else {
                debtAccount.deposit(amount);
            }
        }

        List<CreditAccount> creditAccounts = getCreditAccounts();
        for (int i = 0; i < creditAccounts.size(); i++) {
            CreditAccount creditAccount = creditAccounts.get(i);
            if (creditAccount.needsPayment()) {
                float neededPayment = creditAccount.getInitialCredit() - creditAccount.getBalance();
                if (neededPayment < amountToDeposit) {
                    amountToDeposit -= neededPayment;
                    creditAccount.deposit(neededPayment);
                } else {
                    creditAccount.deposit(amountToDeposit);
                    return;
                }
            }
        }
    }

    public void withDrawFromCreditAccount(float amount){
        List<CreditAccount> creditAccounts =getCreditAccounts();
        CreditAccount creditAccount = creditAccounts.get(0);
        creditAccount.withdraw(amount);
    }

    private DebtAccount getDebtAccount() {
        List<Account> accounts = _currentClient.get_accounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof DebtAccount) {
                return (DebtAccount) accounts.get(i);
                //Arrays.stream(accounts.toArray()).filter(x->x instanceof DebtAccount).collect()
            }
        }

        return null;
    }

    private SavingsAccount getSavingsAccount() {
        List<Account> accounts = _currentClient.get_accounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof SavingsAccount) {
                return (SavingsAccount) accounts.get(i);
            }
        }

        return null;
    }

    private List<CreditAccount> getCreditAccounts() {
        List<Account> accounts = _currentClient.get_accounts();
        List<CreditAccount> creditAccounts = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof CreditAccount) {
                creditAccounts.add((CreditAccount) accounts.get(i));
            }
        }

        return creditAccounts;
    }
}
