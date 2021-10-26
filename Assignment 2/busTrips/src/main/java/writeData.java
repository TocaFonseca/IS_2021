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
        Trip trip_ex = new Trip(getTimeStamp(23, 12, 2021, 2, 5), getTimeStamp(23, 12, 2021, 00, 00), "Coimbra", "Lisboa", 20, 15);
        User user_ex = new User("Maria", getDate(29, 8, 1988), "mpviegas@hotmail.com", "1234", "Coimbra", 0);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Users");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        em.persist(trip_ex);
        //em.persist(user_ex);
        trx.commit();
        em.close();
        emf.close();
    }
}
