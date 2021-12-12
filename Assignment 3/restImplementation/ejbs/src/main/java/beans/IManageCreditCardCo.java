package beans;

import data.*;
import data.Currency;
import data.Transaction;

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

    public Client heighestDebt();

    public Manager heighestRevenue();
}
