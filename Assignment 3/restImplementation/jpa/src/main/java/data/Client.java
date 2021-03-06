package data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="CLIENT")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int client_id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="client")
    @JsonManagedReference
    private List<Transaction> transactionList;

    @ManyToOne
    @JoinColumn(name="manager_id", nullable=false)
    @JsonBackReference
    private Manager manager;

    private String name;

    public Client() {
        super();
    }

    public Client(String name, Manager manager) {
        super();
        this.name = name;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public static long getSerialversionuid() { return serialVersionUID; }

    public int getClient_id(){
        return client_id;
    }

}
