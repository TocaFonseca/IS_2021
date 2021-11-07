package beans;
import data.BusUser;
import data.Trip;

import javax.ejb.*;
import javax.persistence.*;
import java.util.*;
import java.text.*;

@Stateless
public class ManagerApp implements IManagerApp {

    @PersistenceContext(unitName = "UsersTrips")
    EntityManager em;

    @Override
    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    @Override
    public Date getTimeStamp(int day, int month, int year, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 2.
     * To create manager accounts the system should use a script
     * written in JPA.
     * */
    @Override
    public boolean registerManager(String name, Date birth, String email, String password, String address){

        boolean out = false;

        EntityTransaction trx = em.getTransaction();

        TypedQuery<BusUser> bu = em.createQuery("Select b from BusUser b where b.email = :email", BusUser.class);
        bu.setParameter("email", email);
        List<BusUser> userList = bu.getResultList();

        if (userList.size() == 0) {
            BusUser newManager = new BusUser(name, birth, email, password, address);
            newManager.setManager(true);
            trx.begin();
            em.persist(newManager);
            trx.commit();
            out = true;
        }

        return out;

    }

    /**
     * 13.
     * As a company manager, I want to create future bus trips,
     * including the departure date and hour, departure point,
     * destination, capacity, and price.
     * @param depDate departure timestamp
     * @param destDate arrival timestamp
     * @param departure departure point
     * @param destination destination point
     * @param capacity bus capacity
     * @param price ticket (unit) price
     * @return true if succeed, false otherwise
     * */
    @Override
    public boolean createTrip(Date depDate, Date destDate, String departure, String destination, int capacity, int price) {

        boolean created = false;

        Date date = new Date();

        //check if dates are valid
        if (date.before(depDate) && depDate.before(destDate)){

            Trip newTrip = new Trip(depDate, destDate, departure, destination, capacity, price);

            EntityTransaction trx = em.getTransaction();

            trx.begin();
            em.persist(newTrip);
            trx.commit();

            created = true;

        } else{

            created = false;

        }

        return created;

    }

    /**
     * 14.
     * As a company manager, I want to delete future bus trips.
     * The money of all tickets should be returned to the correct
     * wallets, and the system should warn the affected users by
     * email.
     * @param id trip id to be deleted
     * @return true if succeed, false otherwise
     * */
    @Override
    public boolean deleteTrip(int id) {

        boolean out = false;

        EntityTransaction trx = em.getTransaction();

        trx.begin();
        Trip deletedTrip = em.find(Trip.class, id);

        if(deletedTrip != null){

            for (BusUser u: deletedTrip.getUser()) {
                int returnValue = u.getWallet() + deletedTrip.getPrice();
                u.setWallet(returnValue);
                u.getTickets().remove(deletedTrip);
                // TODO send email
            }

            em.remove(deletedTrip);
            out = true;
        }

        trx.commit();

        return out;

    }

    /**
     * 15.
     * TODO - selecao pela query do top 5
     * As a company manager I want to list the passengers that have made more trips
     * @return list of trips with departure date between both dates
     * */
    @Override
    public List<BusUser> topPassengers() {
        List<BusUser> top5users= new ArrayList<BusUser>();
        int count = 0;

        TypedQuery<BusUser> query = em.createQuery("Select u from BusUser u", BusUser.class);
        List<BusUser> busUsers = query.getResultList();

        for (BusUser u: busUsers) {
            if (count < 5){
                count++;
            } else {

            }
            top5users.add(u);
        }

        return busUsers;
    }

    /**
     * 16.
     * As a company manager I want to search for all bus trips
     * sorted by date between two date limits.
     * @param firstDate first date limit to compare
     * @param secondDate second date limit to compare
     * @return list of trips with departure date between both dates
     * */
    @Override
    public List<Trip> searchTrips(Date firstDate, Date secondDate) {

        List<Trip> out = new ArrayList<Trip>();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t order by t.depDate asc", Trip.class);
        List<Trip> allTrips = t.getResultList();

        for (Trip cur_trip: allTrips) {
            if (cur_trip.getDepDate().after(firstDate) && cur_trip.getDepDate().before(secondDate)) {
                out.add(cur_trip);
            }
        }

        return out;

    }

    /**
     * auxiliar (17.)
     * @param date1 one date
     * @param date2 another date
     * @return true if both dates are on the same day
     * */
    @Override
    public boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

    /**
     * 17.
     * As a company manager I want to search for all
     * bus trips occurring on a given date.
     * @param date given date for the search
     * @return list of trips happening on the given date
     * */
    @Override
    public List<Trip> searchByDate(Date date) {

        List<Trip> out = new ArrayList<Trip>();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t order by t.depDate asc, t.destDate asc", Trip.class);
        List<Trip> allTrips = t.getResultList();

        for (Trip cur_trip: allTrips) {
            if (isSameDay(date, cur_trip.getDepDate())||isSameDay(date, cur_trip.getDestDate())) {
                out.add(cur_trip);
            }
        }

        return out;
    }

    /**
     * 19.
     * The system sends a daily summary of the revenues
     * of that day’s trips to the managers.
     * @return revenue value
     * */
    @Override
    public Integer dailyRevenue() {

        int revenue = 0;
        Date cur_date = new Date();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t", Trip.class);
        List<Trip> allTrips = t.getResultList();

        for (Trip cur_trip: allTrips) {
            if (isSameDay(cur_trip.getDepDate(), cur_date)) {
                revenue += cur_trip.getUser().size() * cur_trip.getPrice();
            }
        }

        return revenue;

    }

}