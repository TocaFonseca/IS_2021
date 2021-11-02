package beans;
import data.BusUser;
import data.Trip;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.registry.infomodel.*;

@Stateless
public class ManageData {

    /**
     * Quando se for fazer os testes verificar se os ids estão corretos
     **/
    ManagerApp ma = new ManagerApp();
    UserApp ua = new UserApp();
    /* Test 1.
    boolean ex1 = ua.register("jaRegistado", ua.getDate(29, 8, 2000), "ritafonseca@me.com", "12345", "Coimbra");
    boolean ex2 = ua.register("Ana", ua.getDate(29, 8, 2001), "ana@gmail.com", "12345", "Sintra");
    System.out.println(ex1 + "\n" + ex2);*/


    /* Test 2.
    boolean ex1 = registerManager("falseOne", getDate(22, 2, 2022), "ritafonseca@me.com", "qualquercoisa", "pt");
    boolean ex2 = registerManager("Malena", getDate(19, 11, 1975), "malena@gmail", "betty", "Évora");
    System.out.println(ex1+ "\n" + ex2); */

    /* TODO 3. interface */

    /* Test 4.
    boolean ex1 = authentication("password", "ritafonseca@me.com");
    boolean ex2 = authentication("something", "anything");
    System.out.println(ex1 + "\n" + ex2); */

    /* TODO 5. interface */

    /* Test 6.
    boolean ex1 = editProfile("name", "Rita", 203);
    boolean ex2 = editProfile("address", "Cartaxo", 203);
    boolean ex3 = editProfile("birth", "20 8 2000", 203);
    boolean ex4 = editProfile("nome_do_user", "Rita Fonseca", 203);
    System.out.println(ex1 + "\n" + ex2 + "\n" + ex3 + "\n" + ex4); */

    /* Test 7.
    boolean ex1 = deleteProfile (103, "wrong_password");
    boolean ex2 = deleteProfile (102, "1234");
    boolean ex3 = deleteProfile (1, "wrong_id");
    System.out.println(ex1+ "\n" + ex2 + "\n" + ex3);*/

    // TODO - change this to interface
    /* Test 8.
    List<data.Trip> aux = listAvailableTrips(getDate(29, 10, 2021), getDate(31, 12, 2021));
    for (data.Trip t: aux){ System.out.println(t.tripID); } */

    /* Test 9.
    boolean ex1 = chargeWallet(1, 2);
    boolean ex2 = chargeWallet(152, 20);
    boolean ex3 = chargeWallet(154, 5);
    System.out.println(ex1+ "\n" + ex2 + "\n" + ex3);*/

    /* Test 10.
    boolean ex1 = buyTicket(202); // false - está em todas
    boolean ex2 = buyTicket(203); // true - escolher 402
    boolean ex3 = buyTicket(203); // false
    boolean ex4 = buyTicket(204); // true
    System.out.println(ex1 + "\n" + ex2 + "\n" + ex3 + "\n" + ex4); */

    /* Test 11.
    // TODO - test for past trips
    boolean ex1 = returnTicket (154, 450);
    boolean ex2 = returnTicket (152, 453);
    System.out.println(ex1 + "\n" + ex2);*/

    // TODO - change this to interface
    /* Test 12.
    List<data.Trip> aux1 = listUserTrips(202);
    List<data.Trip> aux2 = listUserTrips(203);
    System.out.println("User id = 202");
    for (data.Trip t: aux1) { System.out.println(t.tripID); }
    System.out.println("User id = 203");
    for (data.Trip t: aux2) { System.out.println(t.tripID); } */

    /* Test 13.
    boolean ex1 = createTrip(getTimeStamp(23, 12, 2020, 4, 5), getTimeStamp(23, 12, 2020, 7, 8), "Porto", "Lille", 100, 30);
    boolean ex2 = createTrip(getTimeStamp(23, 11, 2021, 4, 8), getTimeStamp(23, 11, 2021, 2, 5), "Figueira da Foz", "Coimbra", 50, 1);
    boolean ex3 = createTrip(getTimeStamp(23, 11, 2021, 2, 5), getTimeStamp(23, 11, 2021, 4, 8), "Figueira da Foz", "Coimbra", 50, 1);
    System.out.println(ex1+ "\n" + ex2 + "\n" + ex3); */

    /* Test 14.
    System.out.println(deleteTrip(1053)); */

    /* Test 15. TODO help
     List<BusUser> ex1 = topPassengers();
     for (BusUser u: ex1) { System.out.println(u.getName()); } */

    /* Test 16.
    List<data.Trip> ex1 = searchTrips(getDate(29, 10, 2021), getDate(31, 12, 2021));
    List<data.Trip> ex2 = searchTrips(getDate(22, 2, 2002), getDate(23, 3, 2003));
    System.out.println("First:");
    for (data.Trip t: ex1) { System.out.println(t.tripID); }
    System.out.println("Second:");
    for (data.Trip t: ex2) { System.out.println(t.tripID); } */

    /* Test 17.
     List<data.Trip> ex1 = searchByDate(getDate(23, 12, 2021));
     List<data.Trip> ex2 = searchByDate(getDate(8, 2, 2010));
     for (data.Trip t: ex1) { System.out.println("ex1 "+t.tripID); }
     for (data.Trip t: ex2) { System.out.println("ex2 "+t.tripID); }
     */

    /* Test 18.
    List<data.Trip> ex1 = searchTrips(getDate(29, 10, 2000), getDate(31, 12, 2222));
    for (data.Trip t: ex1) {
        System.out.println(t.tripID);
        if (t.getUser() != null){
            for (data.BusUser u : searchUser(t)) { System.out.println("\t" + u.getName()); }
        }
    } */

    /* Test 19.
     int revenue = dailyRevenue();
     System.out.println(revenue);
     */

}
