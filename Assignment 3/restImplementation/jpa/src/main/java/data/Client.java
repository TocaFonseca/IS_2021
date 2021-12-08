package data;

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

    @OneToMany(mappedBy="client")
    private List<Transaction> transactionList;

    @ManyToOne
    @JoinColumn(name="manager_id", nullable=false)
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

    public List<Transaction> getTransactions() {
        return transactionList;
    }

    public void setTransactions(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public static long getSerialversionuid() { return serialVersionUID; }

    public int getClient_id(){
        return client_id;
    }

}
