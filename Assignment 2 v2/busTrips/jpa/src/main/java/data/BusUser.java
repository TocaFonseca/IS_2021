package data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class BusUser implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int userID;

    @Temporal(TemporalType.DATE)
    private Date birth;

    @ManyToMany
    @JoinTable(name = "bususer_trip", joinColumns = { @JoinColumn(name = "user_userid")}, inverseJoinColumns = { @JoinColumn(name = "tickets_tripid")})
    private List<Trip> tickets;

    private String name;
    private String email;
    private String password; // alterar !!!
    private String address;
    private boolean manager;
    private boolean login;
    private int wallet;

    public BusUser() { super(); }

    public BusUser(String name, Date birth, String email, String password, String address, int wallet) {
        super();
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.address = address;
        this.wallet = wallet;
    }
    public BusUser(String name, Date birth, String email, String password, String address) {
        super();
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public List<Trip> getTickets() {
        return tickets;
    }

    public void setTickets(List<Trip> tickets) {
        this.tickets = tickets;
    }

    public static long getSerialversionuid() { return serialVersionUID; }

}