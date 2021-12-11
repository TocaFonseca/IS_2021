package data;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="CURRENCY")
public class Currency implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String currencyCode;

    //exchange rate for euro
    private float exchange_rate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "currency")
    @JsonManagedReference
    private List<Transaction> transactionList;

    public Currency() {
        super();
    }

    public Currency(String name, float exchange_rate) {
        super();
        this.currencyCode = name;
        this.exchange_rate = exchange_rate;
    }

    public String getName() {
        return currencyCode;
    }

    public void setName(String name) {
        this.currencyCode = name;
    }

    public float getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(float exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

}
