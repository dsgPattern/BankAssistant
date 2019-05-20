import java.util.ArrayList;
import java.util.List;

public class Client {
    private String _name;
    private List<Account> _accounts = new ArrayList<Account>();

    public Client(String name) {
        _name = name;
    }

    public void addAccount(Account account) {
        if (_accounts.contains(account)) return;
        _accounts.add(account);
    }

    public void deleteAccount(Account account) {
        if (_accounts.contains(account))
            _accounts.remove(account);
    }

    public List<Account> get_accounts() {
        return _accounts;
    }
}
