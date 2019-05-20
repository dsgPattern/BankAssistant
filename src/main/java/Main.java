public class Main {
    public static void main(String[] args){

        BankAssistant bankAssistant=new BankAssistant();
        bankAssistant.setCurrentClient(bankAssistant.createClient("Dani"));
        bankAssistant.createSavingAccount(1000);
        bankAssistant.AddDebt(200);
        bankAssistant.payCredit(200);
        bankAssistant.getNewCredit(300);
        bankAssistant.withDrawFromCreditAccount(100);
        bankAssistant.getNewCredit(3000);
        bankAssistant.getNewCredit(1);

    }
}
