package data;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name="MANAGER")
public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int manager_id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "manager")
    @JsonManagedReference
    private List<Client> clientList;

    private String name;

    public Manager() {
        super();
    }

    public Manager(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public static long getSerialversionuid() { return serialVersionUID; }

    public int getManager_id(){
        return manager_id;
    }

}
