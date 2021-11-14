package beans;
import data.BusUser;
import data.Trip;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.*;
import javax.transaction.*;
import javax.transaction.RollbackException;
import java.security.*;
import java.util.*;
import java.text.*;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ManagerApp implements IManagerApp{

    @PersistenceContext(unitName = "UsersTrips")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    private GetData getdata = new GetData();

    public ManagerApp() { super(); }

    @Override
    public Date getDate(int day, int month, int year) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();

    }

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
     * 2.
     * To create manager accounts the system should use a script
     * written in JPA.
     * */
    @Override
    public BusUserDTO registerManager (String name, Date birth, String email, String password, String address) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        ut.begin();

        TypedQuery<BusUser> bu = em.createQuery("Select b from BusUser b where b.email = :email", BusUser.class);
        bu.setParameter("email", email);
        List<BusUser> userList = bu.getResultList();

        if (userList.size() == 0) {
            BusUser newManager = new BusUser(name, birth, email, getSecurePassword(password), address);
            newManager.setManager(true);

            em.persist(newManager);
            ut.commit();

            return getdata.convertUser(newManager);
        }

        ut.commit();
        return null;

    }

    /**
     * 4. (other)
     * Authenticate managers
     * */
    @Override
    public BusUserDTO authentication (String password, String email) {

        TypedQuery<BusUser> bu = em.createQuery("Select b from BusUser b where b.email = :email", BusUser.class);
        bu.setParameter("email", email);
        List<BusUser> userList = bu.getResultList();

        if (userList.size() == 1) {
            for (BusUser u: userList){
                if (email.equals((u.getEmail())) && getSecurePassword(password).equals(u.getPassword()) && u.isManager()){
                    return getdata.convertUser(u);
                }
            }
        }

        return null;
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
    public TripDTO createTrip (Date depDate, Date destDate, String departure, String destination, int capacity, int price) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        ut.begin();
        Date date = new Date(System.currentTimeMillis());
        //check if dates are valid
        if (date.before(depDate) && depDate.before(destDate)){
            Trip newTrip = new Trip(depDate, destDate, departure, destination, capacity, price);
            em.persist(newTrip);
            ut.commit();
            return getdata.convertTrip(newTrip);
        }

        ut.commit();
        return null;
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
    public boolean deleteTrip(int id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        boolean out = false;

        ut.begin();
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

        ut.commit();

        return out;

    }

    /**
     * 15.
     * As a company manager I want to list the passengers that have made more trips
     * @return list of trips with departure date between both dates
     * */
    @Override
    public List<BusUserDTO> topPassengers () {

        List<BusUser> top5users;

        TypedQuery<BusUser> query = em.createQuery("SELECT ph FROM Trip e JOIN e.user ph", BusUser.class);
        List<BusUser> busUsers = query.getResultList();
        Set<BusUser> distinct = new HashSet<>(busUsers);
        Map<BusUser,Integer> elementCountMap = new LinkedHashMap<>();

        for (BusUser s: distinct) {
            elementCountMap.put(s, Collections.frequency(busUsers, s));
        }
        top5users = new ArrayList<BusUser>(elementCountMap.keySet());

        return getdata.convertListBusUsers(top5users);
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
    public List<TripDTO> searchTrips (Date firstDate, Date secondDate) {

        List<TripDTO> final_out = new ArrayList<>();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t order by t.depDate asc", Trip.class);
        List<Trip> allTrips = t.getResultList();

        for (Trip cur_trip: allTrips) {
            if (cur_trip.getDepDate().after(firstDate) && cur_trip.getDepDate().before(secondDate)) {
                TripDTO dto = getdata.convertTrip(cur_trip);
                dto.setUsers(getdata.convertListBusUsers(cur_trip.getUser()));
                final_out.add(dto);
            }
        }

        return final_out;

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
    public List<TripDTO> searchByDate (Date date) {

        List<TripDTO> final_out = new ArrayList<>();

        TypedQuery<Trip> t = em.createQuery("Select t from Trip t order by t.depDate asc, t.destDate asc", Trip.class);
        List<Trip> allTrips = t.getResultList();

        for (Trip cur_trip: allTrips) {
            if (isSameDay(date, cur_trip.getDepDate())||isSameDay(date, cur_trip.getDestDate())) {
                TripDTO dto = getdata.convertTrip(cur_trip);
                dto.setUsers(getdata.convertListBusUsers(cur_trip.getUser()));
                final_out.add(dto);
            }
        }

        return final_out;
    }

    /**
     * 19.
     * The system sends a daily summary of the revenues
     * of that day’s trips to the managers.
     * @return revenue value
     * */
    @Override
    public Map<TripDTO, Integer> dailyRevenue() {

        int revenue = 0;
        Date cur_date = new Date();
        Map<TripDTO,Integer> elementCountMap = new LinkedHashMap<>();


        TypedQuery<Trip> t = em.createQuery("Select t from Trip t", Trip.class);
        List<Trip> allTrips = t.getResultList();

        for (Trip cur_trip: allTrips) {
            if (isSameDay(cur_trip.getDepDate(), cur_date)) {
                revenue += cur_trip.getUser().size() * cur_trip.getPrice();
                elementCountMap.put(getdata.convertTrip(cur_trip),revenue);
            }
        }

        return elementCountMap;

    }

}