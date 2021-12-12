package beans;
import com.fasterxml.jackson.core.JsonProcessingException;
import data.Client;
import data.Currency;
import data.Transaction;
import data.Manager;
import javax.transaction.*;
import javax.transaction.RollbackException;
import java.util.*;

public interface IManageCreditCardCo {

    public String addManager (String name) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    public String addClient (String name, int id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    public String addCurrency (String name, float exchange_rate ) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    public List<Client> listClients ();

    public List<Manager> listManagers();

    public List<Transaction> listTransactionList();

    public List<Currency> listCurrencies();

    public String getClientCredit (int id);

    public String getClientPayments (int id);

    public String getClientBalance (int id);

    public float getTotalCredit ();

    public float getTotalPayments ();

    public float getTotalBalance ();

    public String heighestDebt() throws JsonProcessingException;

    public String heighestRevenue() throws JsonProcessingException;
}
