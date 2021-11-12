package beans;
import java.util.List;

public class BusUserDTO {

    private int id;
    private List<TripDTO> tickets;
    private String name;
    private int wallet;

    public BusUserDTO() { super(); }

    public BusUserDTO(int id, List<TripDTO> tickets, String name, int wallet){
        super();
        this.id = id;
        this.tickets = tickets;
        this.name = name;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TripDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TripDTO> tickets) {
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }
}
