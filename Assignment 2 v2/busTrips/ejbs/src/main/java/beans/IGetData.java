package beans;
import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local
public interface IGetData {
    List<TripDTO> listAvailableTrips(Date firstDate, Date secondDate);
}
