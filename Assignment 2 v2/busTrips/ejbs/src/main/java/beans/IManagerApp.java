package beans;

import data.*;

import javax.ejb.Local;
import javax.persistence.*;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import java.util.*;

@Local
public interface IManagerApp {
    /**
     * 18.
     * As a company manager I want to list all passengers on a given trip
     * listed during one of the previous searches.
     *
     * @param t trip listed previously
     * @return list of users in the previous trip
     */
    static List<BusUser> searchUser(Trip t) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<BusUser> u = em.createQuery("Select b from BusUser b", BusUser.class);
        List<BusUser> userList = u.getResultList();
        List<BusUser> out = new ArrayList<BusUser>();

        for (BusUser cur_user : userList) {
            if (t.getUser().contains(cur_user)) {
                out.add(cur_user);
            }
        }

        return out;

    }

    Date getDate(int day, int month, int year);

    Date getTimeStamp(int day, int month, int year, int hour, int minute);

    BusUserDTO registerManager(String name, Date birth, String email, String password, String address) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    BusUserDTO authentication (String password, String email);

    TripDTO createTrip(Date depDate, Date destDate, String departure, String destination, int capacity, int price) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    boolean deleteTrip(int id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException;

    List<BusUserDTO> topPassengers();

    List<TripDTO> searchTrips(Date firstDate, Date secondDate);

    boolean isSameDay(Date date1, Date date2);

    List<TripDTO> searchByDate(Date date);

    Integer dailyRevenue();
}