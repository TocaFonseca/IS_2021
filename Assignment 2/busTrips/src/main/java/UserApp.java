import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;

public class UserApp {

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
     * 4.
     * As a user, I want to authenticate and start a session with
     * my e-mail and password.
     * @param email input email
     * @param password input password
     * @return true if succeed, false otherwise
     * */
    public static boolean authentication (String password, String email) {

        boolean aut = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<BusUser> bu = em.createQuery("Select b from BusUser b where b.email = :email", BusUser.class);
        bu.setParameter("email", email);
        List<BusUser> userList = bu.getResultList();

        if (userList.size() == 1) {
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
     * 8.
     * As a user, I want to list the available trips,
     * providing date intervals.
     * */
    public static List<Trip> listAvailableTrips() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t where t.capacity > 0 and t.depDate >= CURRENT_TIMESTAMP", Trip.class);
        return t.getResultList();

    }

    /**
     * 10.
     * As a user, I want to purchase a ticket.
     * I should be able to select the place.
     * @param id user id
     * @return true if succeed, false otherwise
     * */
    public static boolean buyTicket (int id) {

        boolean out = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t where t.capacity > 0 and t.depDate >= CURRENT_TIMESTAMP", Trip.class);
        List<Trip> tripsList = t.getResultList();

        BusUser cur_user = em.find(BusUser.class, id);

        // TODO - change this to interface
        int count = 0;
        for (Trip cur_trip: tripsList) {
            System.out.println(count + " - " + cur_trip.tripID);
            count++;
        }
        Scanner scan = new Scanner(System.in);
        count = scan.nextInt();
        //end TODO

        if (!cur_user.getTickets().contains(tripsList.get(count)) && tripsList.get(count).getPrice()<=cur_user.getWallet()){

            tripsList.get(count).setCapacity(tripsList.get(count).getCapacity()-1);

            List<Trip> listAux = cur_user.getTickets();
            listAux.add(tripsList.get(count));
            cur_user.setTickets(listAux);

            cur_user.setWallet(cur_user.getWallet()-tripsList.get(count).getPrice());

            trx.begin();
            em.persist(cur_user);
            em.persist(tripsList.get(count));
            trx.commit();

            out = true;
        }

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

        BusUser getUser = em.find(BusUser.class, id);

        return getUser.getTickets();

    }

    public static void main (String[] args) {

        /**
         * Quando se for fazer os testes verificar se os ids estão corretos
         **/

        /* Test 4.
        boolean ex1 = authentication("password", "ritafonseca@me.com");
        boolean ex2 = authentication("something", "anything");
        System.out.println(ex1 + "\n" + ex2); */

        /* Test 6.
        boolean ex1 = editProfile("name", "Rita", 203);
        boolean ex2 = editProfile("address", "Cartaxo", 203);
        boolean ex3 = editProfile("birth", "20 8 2000", 203);
        boolean ex4 = editProfile("nome_do_user", "Rita Fonseca", 203);
        System.out.println(ex1 + "\n" + ex2 + "\n" + ex3 + "\n" + ex4); */

        // TODO - change this to interface
        /* Test 8.
        List<Trip> aux = listAvailableTrips();
        for (Trip t: aux){
            long dif = t.getDestDate().getTime() - t.getDepDate().getTime();
            System.out.println(t.tripID + " - From " + t.getDeparture() + " to " + t.getDestination() + " (" + (dif/1000)/60 + " minutes)");
        }*/

        /* Test 10.
        boolean ex1 = buyTicket(202); // false - está em todas
        boolean ex2 = buyTicket(203); // true - escolher 402
        boolean ex3 = buyTicket(203); // false
        boolean ex4 = buyTicket(204); // true
        System.out.println(ex1 + "\n" + ex2 + "\n" + ex3 + "\n" + ex4); */

        // TODO - change this to interface
        /* Test 12.
        List<Trip> aux1 = listUserTrips(202);
        List<Trip> aux2 = listUserTrips(203);
        System.out.println("User id = 202");
        for (Trip t: aux1) { System.out.println(t.tripID); }
        System.out.println("User id = 203");
        for (Trip t: aux2) { System.out.println(t.tripID); } */

    }

}
