package beans;

import data.*;

import javax.ejb.Local;
import java.util.*;

@Local
public interface IUserApp {
    Date getDate(int day, int month, int year);

    Date getTimeStamp(int day, int month, int year, int hour, int minute);

    boolean register(String name, Date birth, String email, String password, String address);

    boolean authentication(String password, String email);

    boolean editProfile(String paramToChange, String changedParam, int id);

    boolean deleteProfile(int id, String password);

    List<Trip> listAvailableTrips(Date firstDate, Date secondDate);

    boolean chargeWallet(int id, int amount);

    boolean buyTicket(int id);

    boolean returnTicket(int userID, int tripID);

    List<Trip> listUserTrips(int id);
}