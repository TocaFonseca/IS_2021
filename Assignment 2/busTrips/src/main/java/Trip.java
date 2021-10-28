import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int tripID;

    @ManyToMany(mappedBy = "tickets")
    private List<BusUser> user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date destDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date depDate;

    private String destination;
    private String departure;
    private int capacity;
    private int price;

    public Trip() { super(); }

    public Trip(Date depDate, Date destDate, String departure, String destination, int capacity, int price) {
        super();
        this.destDate = destDate;
        this.depDate = depDate;
        this.destination = destination;
        this.departure = departure;
        this.capacity = capacity;
        this.price = price;
    }

    public List<BusUser> getUser() {
        return user;
    }

    public void setUser(List<BusUser> user) {
        this.user = user;
    }

    public Date getDestDate() {
        return destDate;
    }

    public void setDestDate(Date destDate) {
        this.destDate = destDate;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static long getSerialversionuid() { return serialVersionUID; }
}
