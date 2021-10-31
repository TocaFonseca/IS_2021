package data;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class writeData {

    public static Date getDate(int day, int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        Date d = cal.getTime();
        return d;
    }

    public static Date getTimeStamp (int day, int month, int year, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        Date d = cal.getTime();
        return d;
    }

    public static void main(String[] args) {

        Trip[] trips = {
                new Trip(getTimeStamp(23, 12, 2021, 2, 5), getTimeStamp(23, 12, 2021, 4, 8), "Coimbra", "Lisboa", 20, 15),
                new Trip(getTimeStamp(27, 11, 2021, 14, 19), getTimeStamp(27, 11, 2021, 18, 1), "Coimbra", "Santarem", 30, 10),
                new Trip(getTimeStamp(17, 1, 2022, 14, 19), getTimeStamp(17, 1, 2022, 18, 41), "Faro", "Lisboa", 30, 10)
        };

        BusUser[] users = {
                new BusUser("Maria", getDate(29, 8, 1988), "mpviegas@hotmail.com", "1234", "Coimbra", 5),
                new BusUser("Rita", getDate(21, 8, 2000), "ritafonseca@me.com", "password", "Cartaxo", 15),
                new BusUser("João", getDate(21, 8, 1999), "joao@gmail.com", "something", "Lisboa", 20)
        };

        users[0].setTickets(Arrays.asList(trips));
        users[2].setTickets(Arrays.asList(trips[1]));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        trx.begin();
            (em.createQuery("delete from BusUser")).executeUpdate();
            (em.createQuery("delete from Trip")).executeUpdate();

            for (BusUser bu: users) { em.persist(bu); }
            for (Trip t: trips) { em.persist(t); }

        trx.commit();

        em.close();
        emf.close();
    }
}