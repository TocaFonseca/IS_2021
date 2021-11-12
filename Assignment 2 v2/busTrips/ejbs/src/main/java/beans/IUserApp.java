package beans;

import data.*;

import javax.ejb.Local;
import java.util.*;

@Local
public interface IUserApp {
    Date getDate(int day, int month, int year);

    Date getTimeStamp(int day, int month, int year, int hour, int minute);

    BusUserDTO register(String name, Date birth, String email, String password, String address);

    BusUserDTO authentication(String password, String email);

    BusUserDTO editProfile(String paramToChange, String changedParam, int id);

    boolean deleteProfile(int id, String password);

    List<TripDTO> listAvailableTrips(Date firstDate, Date secondDate);

    BusUserDTO chargeWallet(int id, int amount);

    TripDTO buyTicket(int id, int ticket);

    TripDTO returnTicket(int userID, int tripID);

    List<TripDTO> listUserTrips(int id);
}