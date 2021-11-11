package beans;
import java.util.Date;
import java.util.List;

public class TripDTO {

    private int id;
    private List<BusUserDTO> users;
    private Date destDate;
    private Date depDate;
    private String destination;
    private String departure;
    private int capacity;
    private int price;

    public TripDTO() { super(); }

    public TripDTO(Date depDate, Date destDate, String departure, String destination, int capacity, int price) {
        super();
        this.destDate = destDate;
        this.depDate = depDate;
        this.destination = destination;
        this.departure = departure;
        this.capacity = capacity;
        this.price = price;
    }

    public TripDTO(Date depDate, Date destDate, String departure, String destination, int capacity, int price, int id) {
        super();
        this.destDate = destDate;
        this.depDate = depDate;
        this.destination = destination;
        this.departure = departure;
        this.capacity = capacity;
        this.price = price;
        this.id = id;
    }

    public TripDTO(Date depDate, Date destDate, String departure, String destination, int capacity, int price, List<BusUserDTO> users, int id) {
        super();
        this.destDate = destDate;
        this.depDate = depDate;
        this.destination = destination;
        this.departure = departure;
        this.capacity = capacity;
        this.price = price;
        this.users = users;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BusUserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<BusUserDTO> users) {
        this.users = users;
    }

    public void addUser(BusUserDTO user) { this.users.add(user); }

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
}
