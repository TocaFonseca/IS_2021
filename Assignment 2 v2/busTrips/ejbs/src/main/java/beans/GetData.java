package beans;
import data.*;
import org.apache.maven.plugins.annotations.Component;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetData {

    public GetData() { super(); }

    /**
     * 4.
     * As a user, I want to authenticate and start a session with
     * my e-mail and password.
     * @return true if succeed, false otherwise
     * */
    public BusUserDTO authentication (BusUser user) {

        BusUserDTO bususerDTO = new BusUserDTO(user.getUserID(), getListAvailableTrips(user.getTickets()), user.getName(), user.getWallet());

        return bususerDTO;
    }

    /**
     * 8.
     * As a user, I want to list the available trips,
     * providing date intervals.
     * */
    public List<TripDTO> getListAvailableTrips(List<Trip> output){

        List<TripDTO> out = new ArrayList<>();

        for (Trip t: output){
            out.add(new TripDTO(t.getDepDate(), t.getDestDate(), t.getDeparture(), t.getDestination(), t.getCapacity(), t.getPrice(), t.getTripID()));
        }

        return out;
    }

}
