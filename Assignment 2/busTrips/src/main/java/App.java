import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

public class App {

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

    /**
     * 4.
     * As a user, I want to authenticate and start a session with
     * my e-mail and password.
     * @param email
     * @param password
     * @return true if succeed, false otherwise
     * */
    public static boolean authentication (String password, String email) {

        boolean aut = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<BusUser> bu = em.createQuery("Select b from BusUser b where b.email = :email", BusUser.class);
        bu.setParameter("email", email);
        List<BusUser> userList = bu.getResultList();

        if (userList.size() != 1) {
            aut = false;
        } else {
            for (BusUser u: userList){
                // TODO - manage encripted password
                if (email.equals((u.getEmail())) && password.equals(u.getPassword())){
                    aut = true;
                }
            }
        }

        em.close();
        emf.close();

        return aut;
    }

    /**
     * 6.
     * As a user, I want to edit my personal information.
     * TODO - fix change date bug
     * @param paramToChange attribute the user wants to change
     * @param changedParam attribute modified
     * @param id user id
     * @return true if succeed, false otherwise
     */
    public static boolean editProfile (String paramToChange, String changedParam, int id) {

        boolean out = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        BusUser updateUser = em.find(BusUser.class, id);

        if (paramToChange.equals("name")) {
            updateUser.setName(changedParam);
            out = true;
        } else if (paramToChange.equals("email")) {
            updateUser.setEmail(changedParam);
            out = true;
        } else if (paramToChange.equals("password")) {
            // TODO - when modifying manage encripted password
            updateUser.setPassword(changedParam);
            out = true;
        } else if (paramToChange.equals("address")) {
            updateUser.setAddress(changedParam);
            out = true;
        } else if (paramToChange.equals("birth")) {
            String[] aux = changedParam.split(" ");
            updateUser.setBirth(getDate(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
            out = true;
        }

        trx.begin();
        em.persist(updateUser);
        trx.commit();
        em.close();
        emf.close();

        return out;

    }

    /**
     * 12.
     * As a user, I can list my trips.
     * @param id user id
     * @return list with the users trips
     * */
    public static List<Trip> listUserTrips (int id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        BusUser getUser = em.find(BusUser.class, id);

        return getUser.getTickets();

    }

    public static void main (String[] args) {

        /* Test 4.
        boolean ex1 = authentication("password", "ritafonseca@me.com");
        boolean ex2 = authentication("something", "anything");
        System.out.println(ex1 + "\n" + ex2); */

        /* Test 6.
        boolean ex1 = editProfile("name", "Rita", 153);
        boolean ex2 = editProfile("address", "Cartaxo", 153);
        boolean ex3 = editProfile("birth", "20 8 2000", 153);
        boolean ex4 = editProfile("nome_do_user", "Rita Fonseca", 153);
        System.out.println(ex1 + "\n" + ex2 + "\n" + ex3 + "\n" + ex4); */

        /* Test 12.
        List<Trip> aux1 = listUserTrips(152);
        List<Trip> aux2 = listUserTrips(153);
        System.out.println("User id = 152");
        for (Trip t: aux1) { System.out.println(t.tripID); }
        System.out.println("User id = 153");
        for (Trip t: aux2) { System.out.println(t.tripID); } */

    }

}
