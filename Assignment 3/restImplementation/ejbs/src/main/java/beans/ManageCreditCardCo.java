package beans;
import java.util.*;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Currency;
import data.Client;
import data.Transaction;
import data.Manager;

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
    public String addClient (String name, int id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        if (name.equals("") || id == 0)
            return null;

        ut.begin();
        TypedQuery<Manager> mL = em.createQuery("Select m from Manager m where m.id = :id", Manager.class);
        mL.setParameter("id", id);
        List<Manager> clientManager = mL.getResultList();

        if(clientManager.size()== 1) {
            Client newClient = new Client(name, clientManager.get(0));
            em.persist(newClient);
            ut.commit();
            return newClient.getName();
        }

        ut.commit();
        return null;
    }

    /**
     * 3.
     * Add a currency and respective exchange rate for the euro to the database
     * */
    public String addCurrency (String name, float exchange_rate) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        if (name.equals("") || exchange_rate == 0)
            return null;

        Currency newCurrency = new Currency(name, exchange_rate);
        ut.begin();
        em.persist(newCurrency);
        ut.commit();

        return newCurrency.getName();
    }

    /**
     * 4.
     * List managers from the database.
     * */
    public List<Manager> listManagers ()  {

        TypedQuery<Manager> mL = em.createQuery("Select m from Manager m", Manager.class);
        List<Manager> managerList = mL.getResultList();

        if(managerList.size() > 0)
            return managerList;

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
     * List currencies from the database.
     * */
    public List<Currency> listCurrencies ()  {

        TypedQuery<Currency> mL = em.createQuery("Select c from Currency c", Currency.class);
        List<Currency> currenciesList = mL.getResultList();

        if(currenciesList.size() > 0)
            return currenciesList;

        return null;
    }

    public List<Transaction> listTransactionList ()  {

        TypedQuery<Transaction> mL = em.createQuery("Select t from Transaction t", Transaction.class);
        List<Transaction> transactionList = mL.getResultList();

        if(transactionList.size() > 0)
            return transactionList;

        return null;
    }

    /**
     * 7.
     * Get the credit per client
     * */
    public String getClientCredit (int id) {

        float clientCredit = 0;

        TypedQuery<Client> cL = em.createQuery("Select c from Client c where c.id = :id", Client.class);
        cL.setParameter("id", id);
        List<Client> clientList = cL.getResultList();

        if (clientList.size() == 1) {
            for(Transaction t: clientList.get(0).getTransactionList()){
                if(t.isCredit()){
                    clientCredit+=t.getPrice()/t.getCurrency().getExchange_rate();
                }
            }
            return String.valueOf(clientCredit);
        }

        return null;
    }

    /**
     * 8.
     * Get the payments per client
     * */
    public String getClientPayments (int id) {

        float clientPayments = 0;

        TypedQuery<Client> cL = em.createQuery("Select c from Client c where c.id = :id", Client.class);
        cL.setParameter("id", id);
        List<Client> clientList = cL.getResultList();

        if (clientList.size() == 1) {
            for(Transaction t: clientList.get(0).getTransactionList()){
                if(!t.isCredit()){
                    clientPayments-=t.getPrice()/t.getCurrency().getExchange_rate();
                }
            }
            return String.valueOf(clientPayments);
        }

        return null;
    }

    /**
     * 9.
     * current balance per client
     * */
    public String getClientBalance (int id) {
        return String.valueOf(Float.parseFloat(getClientCredit(id)) + Float.parseFloat(getClientPayments(id))); // the payments are already <0 so we use +
    }

    /**
     * 10.
     * Get the total (i.e., sum of all persons) credits
     * */
    public float getTotalCredit () {

        float totalCredit = 0;

        TypedQuery<Transaction> tL = em.createQuery("Select t from Transaction t", Transaction.class);
        List<Transaction> transactionList = tL.getResultList();

        for(Transaction t: transactionList){
            if(t.isCredit()){
                totalCredit += t.getPrice()/t.getCurrency().getExchange_rate();
            }
        }

        return totalCredit;
    }

    /**
     * 11.
     * Get the total payments
     * */
    public float getTotalPayments () {

        float totalPayments = 0;

        TypedQuery<Transaction> tL = em.createQuery("Select t from Transaction t", Transaction.class);
        List<Transaction> transactionList = tL.getResultList();

        for(Transaction t: transactionList){
            if(!t.isCredit()){
                totalPayments -= t.getPrice()/t.getCurrency().getExchange_rate();
            }
        }

        return totalPayments;
    }

    /**
     * 12.
     * Get the total balance
     * */
    public float getTotalBalance () {
        return getTotalCredit() + getTotalPayments();
    }

    /**
     * 15.
     * Get the data of the person with the heighest outstanding debt
     * */
    public String heighestDebt() throws JsonProcessingException {

        Client cur = new Client();
        float cur_debt = 0;
        float aux;
        ObjectMapper mapper = new ObjectMapper();

        TypedQuery<Client> cL = em.createQuery("Select c from Client c", Client.class);
        List<Client> clientList = cL.getResultList();

        for(Client c: clientList){
            if (cur == null){
                cur = c;
                cur_debt = Float.parseFloat(getClientBalance(cur.getClient_id()));
            } else {
                aux = Float.parseFloat(getClientBalance(c.getClient_id()));
                if (aux < cur_debt){
                    cur = c;
                    cur_debt = aux;
                }
            }
        }

        return mapper.writeValueAsString(cur);

    }

    /**
     * 16.
     * Get the data of the manager who has made the
     * highest revenue in payments from his or her clients.
     * */
    public String heighestRevenue() throws JsonProcessingException {

        Manager cur = new Manager();
        float cur_max = 0;
        float aux;
        ObjectMapper mapper = new ObjectMapper();

        TypedQuery<Manager> mL = em.createQuery("Select m from Manager m", Manager.class);
        List<Manager> managerList = mL.getResultList();

        for (Manager m: managerList){
            aux = 0;
            for(Client c: m.getClientList()){
                aux += Float.parseFloat(getClientBalance(c.getClient_id()));
            }
            if (cur == null || aux > cur_max){
                cur = m;
                cur_max = aux;
            }
        }

        return mapper.writeValueAsString(cur);

    }
}