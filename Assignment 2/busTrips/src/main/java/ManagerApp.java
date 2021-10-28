import javax.persistence.*;
import java.util.*;

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

    public static void main (String[] args) {
        /* Test 13.
        boolean ex1 = createTrip(getTimeStamp(23, 12, 2020, 4, 5), getTimeStamp(23, 12, 2020, 7, 8), "Porto", "Lille", 100, 30);
        boolean ex2 = createTrip(getTimeStamp(23, 11, 2021, 4, 8), getTimeStamp(23, 11, 2021, 2, 5), "Figueira da Foz", "Coimbra", 50, 1);
        boolean ex3 = createTrip(getTimeStamp(23, 11, 2021, 2, 5), getTimeStamp(23, 11, 2021, 4, 8), "Figueira da Foz", "Coimbra", 50, 1);
        System.out.println(ex1+ "\n" + ex2 + "\n" + ex3);
        */
    }

}
