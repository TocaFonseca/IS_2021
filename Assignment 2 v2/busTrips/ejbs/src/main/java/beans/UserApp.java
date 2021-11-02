package beans;
import data.BusUser;
import data.Trip;

import java.util.*;
import javax.ejb.*;
import javax.persistence.*;

@Stateless
public class UserApp {

    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    public Date getTimeStamp (int day, int month, int year, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 1.
     * As an unregistered user, I want to create an account,
     * and insert my personal information, including email.
     * @param name input name
     * @param birth input birthday
     * @param email input email
     * @param password input password
     * @param address input personal address
     * @return true if succeed, false otherwise
     * */
    public boolean register (String name, Date birth, String email, String password, String address) {

        boolean registered = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<BusUser> bu = em.createQuery("Select b from BusUser b where b.email = :email", BusUser.class);
        bu.setParameter("email", email);
        List<BusUser> userList = bu.getResultList();

        if (userList.size() == 0) {
            BusUser newBusUser = new BusUser(name, birth, email, password, address);
            EntityTransaction trx = em.getTransaction();
            trx.begin();
            em.persist(newBusUser);
            trx.commit();
            registered = true;
        } else {
            //the email is already registered
            registered = false;
        }

        em.close();
        emf.close();

        return registered;
    }

    /**
     * 4.
     * As a user, I want to authenticate and start a session with
     * my e-mail and password.
     * @param email input email
     * @param password input password
     * @return true if succeed, false otherwise
     * */
    public boolean authentication (String password, String email) {

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
     * @param paramToChange attribute the user wants to change
     * @param changedParam attribute modified
     * @param id user id
     * @return true if succeed, false otherwise
     */
    public boolean editProfile (String paramToChange, String changedParam, int id) {

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
     * 7.
     * As a user, I want to delete my account,
     * thus erasing all traces of my existence
     * from the system, including my available items.
     * @param id user id
     * @param password user password
     * @return true if succeed, false otherwise
     */
    public boolean deleteProfile (int id, String password) {

        boolean foundProfile = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BusUser deletedUser = em.find(BusUser.class, id);

        if(deletedUser != null && deletedUser.getPassword().equals(password)){
            em.remove(deletedUser);
            em.getTransaction().commit();
            foundProfile = true;
        }

        emf.close();
        em.close();

        return foundProfile;

    }

    /**
     * 8.
     * As a user, I want to list the available trips,
     * providing date intervals.
     * */
    public List<Trip> listAvailableTrips(Date firstDate, Date secondDate) {

        List<Trip> out = new ArrayList<Trip>();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t where t.capacity > 0 and t.depDate >= CURRENT_TIMESTAMP", Trip.class);
        List<Trip> allTrips = t.getResultList();

        for (Trip cur_trip: allTrips) {
            if (cur_trip.getDepDate().after(firstDate) && cur_trip.getDepDate().before(secondDate)) {
                out.add(cur_trip);
            }
        }

        return out;

    }

    /**
     * 9.
     * As a user, I want to charge my wallet
     * to be able to purchase tickets.
     * @param id user id
     * @param amount money to add to the wallet
     * */
    public boolean chargeWallet(int id, int amount) {

        boolean charged = false;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        BusUser cur_user = em.find(BusUser.class, id);

        if (cur_user != null) {
            int currentWallet = cur_user.getWallet();
            cur_user.setWallet(currentWallet + amount);
            charged = true;
            trx.begin();
            em.persist(cur_user);
            trx.commit();
        }

        em.close();
        emf.close();

        return charged;
    }

    /**
     * 10.
     * As a user, I want to purchase a ticket.
     * I should be able to select the place.
     * @param id user id
     * @return true if succeed, false otherwise
     * */
    public boolean buyTicket (int id) {

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
            System.out.println(count + " - " + cur_trip.getTripID());
            count++;
        }
        Scanner scan = new Scanner(System.in);
        count = scan.nextInt();
        //end TODO

        if (cur_user != null && !cur_user.getTickets().contains(tripsList.get(count)) && tripsList.get(count).getPrice()<=cur_user.getWallet()){

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
     * 11.
     * As a user, I may be able to return a ticket for
     * future trips and get a reimbursement in my wallet.
     * @param userID user id
     * @param tripID trip id
     * @return true if succeed, false otherwise
     * */
    public boolean returnTicket (int userID, int tripID) {

        boolean out = false;
        Date date = new Date();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trx = em.getTransaction();

        BusUser cur_user = em.find(BusUser.class, userID);

        //if user exists
        if(cur_user != null){
            //for every ticket the user has
            for(Trip cur_trip: cur_user.getTickets()){
                //if finds the selected trip and the trip has not happened yet
                if(cur_trip.getTripID() == tripID && cur_trip.getDepDate().after(date)){
                    //disassociate the trip from the user
                    cur_user.getTickets().remove(cur_trip);
                    //reimburses the user
                    cur_user.setWallet(cur_user.getWallet() + cur_trip.getPrice());
                    //update trips capacity
                    cur_trip.setCapacity(cur_trip.getCapacity()+1);

                    //commit
                    trx.begin();
                    em.persist(cur_user);
                    em.persist(cur_trip);
                    trx.commit();
                    out = true;
                }
            }
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
    public List<Trip> listUserTrips (int id) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersTrips");
        EntityManager em = emf.createEntityManager();

        BusUser getUser = em.find(BusUser.class, id);

        if (getUser != null){
            return getUser.getTickets();
        }

        return null;

    }

}