package beans;

import data.*;

import javax.ejb.Local;
import javax.transaction.*;
import java.util.*;

@Local
public interface IUserApp {
    Date getDate(int day, int month, int year);

    Date getTimeStamp(int day, int month, int year, int hour, int minute);

    BusUserDTO updateUser(int id);

    BusUserDTO register(String name, Date birth, String email, String password, String address) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    BusUserDTO authentication(String password, String email);

    BusUserDTO editProfile(String paramToChange, String changedParam, int id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    boolean deleteProfile(int id, String password) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException, NotSupportedException;

    List<TripDTO> listAvailableTrips(Date firstDate, Date secondDate);

    BusUserDTO chargeWallet(int id, String amount) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    TripDTO buyTicket(int id, int ticket) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    TripDTO returnTicket(int userID, int tripID) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    List<TripDTO> listUserTrips(int id);
}