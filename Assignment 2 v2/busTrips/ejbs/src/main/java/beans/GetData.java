package beans;
import data.BusUser;
import data.Trip;
import java.util.ArrayList;
import java.util.List;

public class GetData {

    public GetData() { super(); }

    /*
    * Métodos que penso não valer a pena converter
    * 7. deleteProfile
    * 14. deleteTrip
    * 19. dailyRevenue
    * */

    /**
     * Converts a single BusUser to DTO
     * Requirements: 1, 2, 4, 6, 9
     * */
    public BusUserDTO convertUser(BusUser u){
        return new BusUserDTO(u.getUserID(), convertListTrips(u.getTickets()), u.getName(), u.getWallet());
    }

    /**
    * Converts a single Trip to DTO
     * Requirements: 10, 11, 13
     * */
    public TripDTO convertTrip(Trip t){
        return new TripDTO(t.getDepDate(), t.getDestDate(), t.getDeparture(), t.getDestination(), t.getCapacity(), t.getPrice(), t.getTripID());
    }

    /**
     * Converts a List of BusUsers to DTO
     * Requirements: 15
     * */
    public List<BusUserDTO> convertListBusUsers(List<BusUser> output){

        List<BusUserDTO> out = new ArrayList<>();

        for (BusUser b: output){
            out.add(convertUser(b));
        }

        return out;

    }

    /**
     * Converts a List of Trips to DTO
     * Requirements: 8, 12, 16, 17
     * */
    public List<TripDTO> convertListTrips(List<Trip> output){

        List<TripDTO> out = new ArrayList<>();

        for (Trip t: output){
            out.add(convertTrip(t));
        }

        return out;
    }

}
