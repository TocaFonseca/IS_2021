import org.hibernate.Transaction;
import javax.persistence.*;
import java.util.*;
import java.text.*;

public class ManagerApp {

    public static Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }
    public static Date getTimeStamp (int day, int month, int year, int hour, int minute) {
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
    public static boolean registerManager (String name, Date birth, String email, String password, String address){

        boolean out = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
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

        em.close();
        emf.close();

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
    public static boolean createTrip (Date depDate, Date destDate, String departure, String destination, int capacity, int price) {

        boolean created = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

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

        em.close();
        emf.close();

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
    public static boolean deleteTrip(int id) {

        boolean out = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
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
        em.close();
        emf.close();

        return out;

    }

    /**
     * 15.
     * TODO - selecao pela query do top 5
     * As a company manager I want to list the passengers that have made more trips
     * @return list of trips with departure date between both dates
     * */
    public static List<BusUser> topPassengers () {
        List<BusUser> top5users= new ArrayList<BusUser>();;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<BusUser> query = em.createQuery("Select u from BusUser u", BusUser.class);
        List<BusUser> busUsers = query.getResultList();

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
    public static List<Trip> searchTrips (Date firstDate, Date secondDate) {

        List<Trip> out = new ArrayList<Trip>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

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
    public static boolean isSameDay(Date date1, Date date2) {
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
    public static List<Trip> searchByDate (Date date) {

        List<Trip> out = new ArrayList<Trip>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

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
     * 18.
     * As a company manager I want to list all passengers on a given trip
     * listed during one of the previous searches.
     * @param t trip listed previously
     * @return list of users in the previous trip
     * */
    public static List<BusUser> searchUser (Trip t) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<BusUser> u = em.createQuery("Select b from BusUser b", BusUser.class);
        List<BusUser> userList = u.getResultList();
        List<BusUser> out = new ArrayList<BusUser>();

        for (BusUser cur_user: userList){
            if (t.getUser().contains(cur_user)){ out.add(cur_user); }
        }

        return out;

    }

    /**
     * 19.
     * The system sends a daily summary of the revenues
     * of that day’s trips to the managers.
     * @return revenue value
     * */
    public static Integer dailyRevenue() {

        int revenue = 0;
        Date cur_date = new Date();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t", Trip.class);
        List<Trip> allTrips = t.getResultList();

        for (Trip cur_trip: allTrips) {
            if (isSameDay(cur_trip.getDepDate(), cur_date)) {
                revenue += cur_trip.getUser().size() * cur_trip.getPrice();
            }
        }

        return revenue;

    }

    public static void main (String[] args) {

        /* Test 2.
        boolean ex1 = registerManager("falseOne", getDate(22, 2, 2022), "ritafonseca@me.com", "qualquercoisa", "pt");
        boolean ex2 = registerManager("Malena", getDate(19, 11, 1975), "malena@gmail", "betty", "Évora");
        System.out.println(ex1+ "\n" + ex2); */

        /* Test 13.
        boolean ex1 = createTrip(getTimeStamp(23, 12, 2020, 4, 5), getTimeStamp(23, 12, 2020, 7, 8), "Porto", "Lille", 100, 30);
        boolean ex2 = createTrip(getTimeStamp(23, 11, 2021, 4, 8), getTimeStamp(23, 11, 2021, 2, 5), "Figueira da Foz", "Coimbra", 50, 1);
        boolean ex3 = createTrip(getTimeStamp(23, 11, 2021, 2, 5), getTimeStamp(23, 11, 2021, 4, 8), "Figueira da Foz", "Coimbra", 50, 1);
        System.out.println(ex1+ "\n" + ex2 + "\n" + ex3); */

        /* Test 14.
        System.out.println(deleteTrip(1053)); */

        /* Test 15. TODO help
         List<BusUser> ex1 = topPassengers();
         for (BusUser u: ex1) { System.out.println(u.getName()); }
         */

        /* Test 16.
        List<Trip> ex1 = searchTrips(getDate(29, 10, 2021), getDate(31, 12, 2021));
        List<Trip> ex2 = searchTrips(getDate(22, 2, 2002), getDate(23, 3, 2003));
        System.out.println("First:");
        for (Trip t: ex1) { System.out.println(t.tripID); }
        System.out.println("Second:");
        for (Trip t: ex2) { System.out.println(t.tripID); } */

        /* Test 17.
         List<Trip> ex1 = searchByDate(getDate(23, 12, 2021));
         List<Trip> ex2 = searchByDate(getDate(8, 2, 2010));
         for (Trip t: ex1) { System.out.println("ex1 "+t.tripID); }
         for (Trip t: ex2) { System.out.println("ex2 "+t.tripID); }
         */

        /* Test 18.
        List<Trip> ex1 = searchTrips(getDate(29, 10, 2000), getDate(31, 12, 2222));
        for (Trip t: ex1) {
            System.out.println(t.tripID);
            if (t.getUser() != null){
                for (BusUser u : searchUser(t)) { System.out.println("\t" + u.getName()); }
            }
        } */

        /* Test 19.
         int revenue = dailyRevenue();
         System.out.println(revenue);
         */

    }

}