package data;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="TRANSACTION")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int transaction_id;

    private float price;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    @JsonBackReference
    private Client client;

    @ManyToOne
    @JoinColumn(name="currencyCode", nullable=false)
    private Currency currency;

    //if it is a payment or a credit
    private boolean credit;


    public Transaction() {
        super();
    }

    public Transaction(Client client, Currency currency, boolean credit) {
        super();
        this.client = client;
        this.currency = currency;
        this.credit = credit;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public float getPrice() {
        return price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isCredit() {
        return credit;
    }

    public void setCredit(boolean credit) {
        this.credit = credit;
    }
}
