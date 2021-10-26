import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class App {

    public static Date getDate(int day, int month, int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, day);

        Date d = cal.getTime();
        return d;
    }

    public static void main( String[] args) {

        BusUser user = new BusUser("Rita", getDate(21, 8, 2000), "ritafonseca@me.com", "password", "cartaxo", 15);
        Trip trip = new Trip(getDate(27, 10, 2021), getDate(27, 10, 2021), "Coimbra", "Santarem", 30, 10);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        trx.begin();

            // clear the database first
            (em.createQuery("delete from BusUser")).executeUpdate();
            (em.createQuery("delete from Trip")).executeUpdate();

            em.persist(user);
            em.persist(trip);

        trx.commit();

        em.close();
        emf.close();
    }

}
