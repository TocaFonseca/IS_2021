package beans;

import data.*;
import data.Currency;
import data.Transaction;

import javax.transaction.*;
import javax.transaction.RollbackException;
import java.util.*;

public interface IManageCreditCardCo {

    public String addManager (String name) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    public String addClient (String name, String nameM) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    public Currency addCurrency (String name, float exchange_rate ) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    public List<Client> listClients ();

    public List<Manager> listManagers();

    public List<Transaction> listTransactions();

    public List<Currency> listCurrencies();

    public float getClientCredit (String name);

    public float getClientPayments (String name);

    public float getClientBalance (String name);

    public float getTotalCredit ();

    public float getTotalPayments ();

    public float getTotalBalance ();
}
