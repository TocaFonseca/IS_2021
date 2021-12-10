package beans;

import java.security.*;
import java.util.*;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;
import data.*;
import data.Currency;
import data.Transaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ManageCreditCardCo implements IManageCreditCardCo {

    @PersistenceContext(unitName = "cardCo")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    public ManageCreditCardCo(){
        super();
    }

    /**
     * 1.
     * Add managers to the database.
     * */
    public String addManager (String name) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        if (name.equals(""))
            return null;

        Manager newManager = new Manager(name);
        ut.begin();
        em.persist(newManager);
        ut.commit();

        return newManager.getName();
    }

    /**
     * 2.
     * Add clients to the database
     * */
    public String addClient (String name, String nameM ) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        if (name.equals("") || nameM.equals(""))
            return null;

        ut.begin();
        TypedQuery<Manager> mL = em.createQuery("Select m from Manager m where m.name = :name", Manager.class);
        mL.setParameter("name", nameM);
        List<Manager> clientManager = mL.getResultList();

        if(clientManager.size()== 1) {
            Client newClient = new Client(name, clientManager.get(0));
            em.persist(newClient);
            ut.commit();
            return newClient.getName();
        }

       return null;
    }

    /**
     * 3.
     * Add a currency and respective exchange rate for the euro to the database
     * */
    public Currency addCurrency (String name, float exchange_rate ) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        if (name.equals("") || exchange_rate == 0)
            return null;

        ut.begin();
        Currency findCurrency = em.find(Currency.class, name);

        if (findCurrency != null) {
            Currency newCurrency = new Currency(name, exchange_rate);
            ut.begin();
            em.persist(newCurrency);
            ut.commit();

            return newCurrency;
        }

        return null;
    }

    /**
     * 5.
     * List clients from the database.
     * */
    public List<Client> listClients ()  {

        TypedQuery<Client> cL = em.createQuery("Select c from Client c", Client.class);
        List<Client> clientList = cL.getResultList();

        if(clientList.size() > 0)
            return clientList;

        return null;
    }

    /**
     * 6.
     * List managers from the database.
     * */
    public List<Manager> listManagers ()  {

        TypedQuery<Manager> mL = em.createQuery("Select m from Manager m", Manager.class);
        List<Manager> managerList = mL.getResultList();

        if(managerList.size() > 0)
            return managerList;

        return null;
    }

    public List<Transaction> listTransactions ()  {

        TypedQuery<Transaction> mL = em.createQuery("Select t from Transaction t", Transaction.class);
        List<Transaction> transactionList = mL.getResultList();

        if(transactionList.size() > 0)
            return transactionList;

        return null;
    }

    public List<Currency> listCurrencies ()  {

        TypedQuery<Currency> mL = em.createQuery("Select c from Currency c", Currency.class);
        List<Currency> currenciesList = mL.getResultList();

        if(currenciesList.size() > 0)
            return currenciesList;

        return null;
    }

    /**
     * 7.
     * Get the credit per client
     * */
    public float getClientCredit (String name) {

        if (name.equals(""))
            return 0;

        float clientCredit = 0;

        TypedQuery<Client> cL = em.createQuery("Select c from Client c where c.name = :name", Client.class);
        cL.setParameter("name", name);
        List<Client> clientList = cL.getResultList();

        if (clientList.size() == 1) {
            for(Transaction t: clientList.get(0).getTransactions()){
                if(t.isCredit()){
                    clientCredit+=t.getPrice()/t.getCurrency().getExchange_rate();
                }
            }
            return clientCredit;
        }

        return 0;
    }

    /**
     * 8.
     * Get the payments per client
     * */
    public float getClientPayments (String name) {

        if (name.equals(""))
            return 0;

        float clientPayments=0;

        TypedQuery<Client> cL = em.createQuery("Select c from Client c where c.name = :name", Client.class);
        cL.setParameter("name", name);
        List<Client> clientList = cL.getResultList();

        if (clientList.size() == 1) {
            for(Transaction t: clientList.get(0).getTransactions()){
                if(!t.isCredit()){
                    clientPayments-=t.getPrice()/t.getCurrency().getExchange_rate();
                }
            }
            return clientPayments;
        }

        return 0;
    }

    /**
     * 9.
     * current balance per client
     * */
    public float getClientBalance (String name) {

        if (name.equals(""))
            return 0;

        float clientBalance = getClientCredit(name) - getClientPayments(name);

        return clientBalance;
    }

    /**
     * 10.
     * Get the total (i.e., sum of all persons) credits
     * */
    public float getTotalCredit () {

        float totalCredit=0;

        TypedQuery<Transaction> tL = em.createQuery("Select t from Transaction t", Transaction.class);
        List<Transaction> transactionList = tL.getResultList();

        for(Transaction t: transactionList){
            if(t.isCredit()){
                totalCredit+=t.getPrice()/t.getCurrency().getExchange_rate();
            }
        }

        return totalCredit;
    }

    /**
     * 11.
     * Get the total payments
     * */
    public float getTotalPayments () {

        float totalPayments=0;

        TypedQuery<Transaction> tL = em.createQuery("Select t from Transaction t", Transaction.class);
        List<Transaction> transactionList = tL.getResultList();

        for(Transaction t: transactionList){
            if(!t.isCredit()){
                totalPayments-=t.getPrice()/t.getCurrency().getExchange_rate();
            }
        }

        return totalPayments;
    }

    /**
     * 12.
     * Get the total balance
     * */
    public float getTotalBalance () {

        float totalBalance = getTotalCredit() + getTotalPayments();

        return totalBalance;
    }

}