package beans;
import data.Trip;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class GetData implements IGetData {

    private ManagerApp manager = new ManagerApp();
    private UserApp user = new UserApp();


    /**
     * 8.
     * As a user, I want to list the available trips,
     * providing date intervals.
     * */
    @Override
    public List<TripDTO> listAvailableTrips(Date firstDate, Date secondDate){

        List<TripDTO> out = new ArrayList<TripDTO>();

        for (Trip t: user.listAvailableTrips(firstDate, secondDate)){
            out.add(new TripDTO(t.getDepDate(), t.getDestDate(), t.getDeparture(), t.getDestination(), t.getCapacity(), t.getPrice(), t.getTripID()));
        }
        return out;
    }

}
