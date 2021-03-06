package beans;
import data.BusUser;
import data.Trip;

import java.security.*;
import java.util.*;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserApp implements IUserApp{

    @PersistenceContext(unitName = "UsersTrips")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    private GetData getdata = new GetData();

    public UserApp(){ super(); }

    //get a date in Date format from integers
    @Override
    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

    //get a date in TimeStamp format from integers
    @Override
    public Date getTimeStamp (int day, int month, int year, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    @Override
    public BusUserDTO updateUser(int id){

        BusUser aux = em.find(BusUser.class, id);
        return getdata.convertUser(aux);

    }

    //get future user's trips
    @Override
    public List<TripDTO> listFutureUserTrips (int id) {

        Date date = new Date();
        BusUser getUser = em.find(BusUser.class, id);
        List<Trip> futureTrips =  new ArrayList<>();

        for(Trip cur_trip: getUser.getTickets()){
            //if  the trip has not happened yet
            if(cur_trip.getDepDate().after(date)) {
                futureTrips.add(cur_trip);
            }
        }

        if (!futureTrips.isEmpty()){
            return getdata.convertListTrips(futureTrips);
        }

        return null;

    }

    //Generate MD5
    private String getSecurePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(passwordToHash.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
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
    @Override
    public BusUserDTO register (String name, Date birth, String email, String password, String address) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException, NoSuchAlgorithmException, NoSuchProviderException {

        TypedQuery<BusUser> bu = em.createQuery("Select b from BusUser b where b.email = :email", BusUser.class);
        bu.setParameter("email", email);
        List<BusUser> userList = bu.getResultList();

        if (userList.size() == 0) {
            String securePassword = getSecurePassword(password);
            BusUser newBusUser = new BusUser(name, birth, email, securePassword, address);
            ut.begin();
            em.persist(newBusUser);
            ut.commit();

            return getdata.convertUser(newBusUser);
        }

        return null;
    }

    /**
     * 4.
     * As a user, I want to authenticate and start a session with
     * my e-mail and password.
     * @param email input email
     * @param password input password
     * @return true if succeed, false otherwise
     * */
    @Override
    public BusUserDTO authentication (String password, String email) throws NoSuchAlgorithmException, NoSuchProviderException {

        TypedQuery<BusUser> bu = em.createQuery("Select b from BusUser b where b.email = :email", BusUser.class);
        bu.setParameter("email", email);
        List<BusUser> userList = bu.getResultList();
        String securePassword = getSecurePassword(password);

        if (userList.size() == 1) {
            for (BusUser u: userList){
                // TODO - manage encripted password
                if (email.equals((u.getEmail())) && securePassword.equals(u.getPassword())){
                    return getdata.convertUser(u);
                }
            }
        }

        return null;
    }

    /**
     * 6.
     * As a user, I want to edit my personal information.
     * @param paramToChange attribute the user wants to change
     * @param changedParam attribute modified
     * @param id user id
     * @return true if succeed, false otherwise
     */
    @Override
    public BusUserDTO editProfile (String paramToChange, String changedParam, int id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        ut.begin();
        BusUser updateUser = em.find(BusUser.class, id);

        if (updateUser != null){
            if (paramToChange.equals("name")) {
                updateUser.setName(changedParam);
            } else if (paramToChange.equals("email")) {
                updateUser.setEmail(changedParam);
            } else if (paramToChange.equals("password")) {
                updateUser.setPassword(getSecurePassword(paramToChange));
            } else if (paramToChange.equals("address")) {
                updateUser.setAddress(changedParam);
            } else if (paramToChange.equals("birth")) {
                String[] aux = changedParam.split(" ");
                updateUser.setBirth(getDate(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
            }

            em.persist(updateUser);
            ut.commit();

            return getdata.convertUser(updateUser);
        }

        ut.commit();
        return null;

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
    @Override
    public boolean deleteProfile (int id, String password) throws HeuristicRollbackException, SystemException, HeuristicMixedException, RollbackException, NotSupportedException {

        boolean foundProfile = false;

        BusUser deletedUser = em.find(BusUser.class, id);

        if(deletedUser != null && deletedUser.getPassword().equals(getSecurePassword(password))){
            ut.begin();
            em.remove(deletedUser);
            ut.commit();
            foundProfile = true;
        }
        return foundProfile;

    }

    /**
     * 8.
     * As a user, I want to list the available trips,
     * providing date intervals.
     * */
    @Override
    public List<TripDTO> listAvailableTrips(Date firstDate, Date secondDate) {

        List<Trip> out = new ArrayList<>();

        List<Trip> allTrips = em.createQuery("Select t from Trip t where t.capacity > 0 and t.depDate >= CURRENT_TIMESTAMP", Trip.class).getResultList();

        for (Trip cur_trip: allTrips) {
            if (cur_trip.getDepDate().after(firstDate) && cur_trip.getDepDate().before(secondDate)) {
                out.add(cur_trip);
            }
        }

        return getdata.convertListTrips(out);

    }

    /**
     * 9.
     * As a user, I want to charge my wallet
     * to be able to purchase tickets.
     * @param id user id
     * @param amount money to add to the wallet
     * */
    @Override
    public BusUserDTO chargeWallet(int id, String amount) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        ut.begin();
        BusUser cur_user = em.find(BusUser.class, id);

        if (cur_user != null) {
            int currentWallet = cur_user.getWallet();
            cur_user.setWallet(currentWallet +Integer.parseInt(amount));
            em.persist(cur_user);
            ut.commit();
            return getdata.convertUser(cur_user);
        }
        ut.commit();
        return null;
    }

    /**
     * 10.
     * As a user, I want to purchase a ticket.
     * I should be able to select the place.
     * @param id user id
     * @param ticket ticket to buy
     * @return true if succeed, false otherwise
     * */
    @Override
    public TripDTO buyTicket (int id, int ticket) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        ut.begin();
        BusUser user = em.find(BusUser.class, id);
        Trip trip = em.find(Trip.class, ticket);

        if (user != null && trip != null && !user.getTickets().contains(trip) && trip.getPrice()<=user.getWallet()){

            int oldcap = trip.getCapacity()-1;
            trip.setCapacity(oldcap);

            List<Trip> listAux = user.getTickets();
            listAux.add(trip);
            user.setTickets(listAux);

            int wal = user.getWallet()-trip.getPrice();
            user.setWallet(wal);

            em.persist(user);
            em.persist(trip);

            ut.commit();
            return getdata.convertTrip(trip);

        }

        ut.commit();
        return null;

    }

    /**
     * 11.
     * As a user, I may be able to return a ticket for
     * future trips and get a reimbursement in my wallet.
     * @param userID user id
     * @param tripID trip id
     * @return true if succeed, false otherwise
     * */
    @Override
    public TripDTO returnTicket (int userID, int tripID) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        Date date = new Date();
        ut.begin();

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
                    em.persist(cur_user);
                    em.persist(cur_trip);

                    ut.commit();
                    return getdata.convertTrip(cur_trip);
                }
            }
        }
        ut.commit();
        return null;
    }

    /**
     * 12.
     * As a user, I can list my trips.
     * @param id user id
     * @return list with the users trips
     * */
    @Override
    public List<TripDTO> listUserTrips (int id) {

        BusUser getUser = em.find(BusUser.class, id);

        if (getUser != null){
            return getdata.convertListTrips(getUser.getTickets());
        }

        return null;

    }

}