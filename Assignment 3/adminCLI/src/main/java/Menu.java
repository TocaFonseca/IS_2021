import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Menu {

    private static Scanner scan;
    private static ObjectMapper mapper;
    private static Client client;

    /**
     * Auxiliary method that gets the clients list
     * from the database from the REST module
     * */
    public static HashMap<String, Object> getListOfClients() throws IOException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/clientsList");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        HashMap<String, Object> map = mapper.readValue(jsonString, HashMap.class);
        response.close();

        return map;
    }

    /**
     * Auxiliary method that gets the managers list
     * from the database from the REST module
     * */
    public static HashMap<String, Object> getListOfManagers() throws IOException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/managersList");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        HashMap<String, Object> map = mapper.readValue(jsonString, HashMap.class);
        response.close();

        return map;

    }

    /**
     * Auxiliary method that gets the payments list
     * from the database from the REST module
     * */
    public static HashMap<String, Object> getListOfPayments() throws IOException{

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/paymentsList");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        HashMap<String, Object> map = mapper.readValue(jsonString, HashMap.class);
        response.close();

        return map;

    }

    /**
     * Auxiliary method that gets the currencies list
     * from the database from the REST module
     * */
    public static HashMap<String, Object> getListOfCurrencies() throws IOException{

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/currenciesList");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        HashMap<String, Object> map = mapper.readValue(jsonString, HashMap.class);
        response.close();

        return map;

    }

    /**
     * Recursive method that searches for a name and a id
     * from the hashmap with transactions older than 2 months,
     * and then presents in the CLI
     * */
    public static void recursive_nameAndId(HashMap<String, Object> map){
        for (HashMap.Entry<String, Object> entry : map.entrySet()) {
            if (map.containsKey("id") && map.containsKey("name")){
                System.out.println("\t" + map.get("id") + "\t" + map.get("name"));
                return;
            } else if (entry.getValue() instanceof HashMap) {
                recursive_nameAndId((HashMap<String, Object>) entry.getValue());
            }
        }
    }

    /**
     * Recursive method that searches for a currencies's
     * name and exchange rate from the hashmap, and then
     * presents in the CLI
     * */
    public static void recursive_currencyName(HashMap<String, Object> map){
        for (HashMap.Entry<String, Object> entry : map.entrySet()) {
            if (map.containsKey("exchange") && map.containsKey("name")){
                System.out.println("\t" + map.get("name") + "\t" + map.get("exchange"));
                return;
            } else if (entry.getValue() instanceof HashMap) {
                recursive_currencyName((HashMap<String, Object>) entry.getValue());
            }
        }
    }

    // TODO check currency conversions
    /**
     * Recursive method that calculates the total balance
     * from the transactions saved in the database (gains and losses)
     * */
    public static double recursive_totalBalance(HashMap<String, Object> map, double latest_sum){

        double sum = latest_sum;

        for (HashMap.Entry<String, Object> entry : map.entrySet()) {
            if (map.containsKey("price") && map.containsKey("credit")){
                double aux = Double.valueOf((map.get("price").toString()));
                return sum + aux;
            } else if (entry.getValue() instanceof HashMap) {
                sum = recursive_totalBalance((HashMap<String, Object>) entry.getValue(), sum);
            }
        }

        return sum;

    }

    // TODO falta testar com o REST
    /**
     * 1.
     * Add managers to the database.
     * */
    public static void addManagers() throws IOException {

        HashMap<String, Object> newMan = new HashMap<>();

        System.out.println("Insert the name of the manager:");
        String name = scan.nextLine();
        scan.next();
        newMan.put("name", name);

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/addManager");
        String jsonString = mapper.writeValueAsString(newMan);
        Entity<String> ent = Entity.json(jsonString);
        Response response = target.request().post(ent);
        String out = response.readEntity(String.class);
        response.close();

        if (out.isEmpty()){
            System.out.println("ERROR: System failed to load the new manager!");
        } else {
            System.out.println("New manager added to the database!");
        }

    }

    // TODO falta testar com o REST
    /**
    * 2.
     * Add clients to the database.
     * Each client has a manager.
    * */
    public static void addClients() throws IOException {

        HashMap<String, Object> newClient = new HashMap<>();

        System.out.println("Insert the name of the client:");
        String name = scan.nextLine();
        scan.next();
        newClient.put("name", name);

        recursive_nameAndId(getListOfManagers());
        System.out.println("Select the id of the manager responsible for the client: ");
        int manId = scan.nextInt();
        newClient.put("manager", manId);

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/addClient");
        String jsonString = mapper.writeValueAsString(newClient);
        Entity<String> ent = Entity.json(jsonString);
        Response response = target.request().post(ent);
        String out = response.readEntity(String.class);
        response.close();

        if (out.isEmpty()){
            System.out.println("ERROR: System failed to load the new client!");
        } else {
            System.out.println("New client added to the database!");
        }

    }

    // TODO falta testar com o REST
    /**
     * 3.
     * Add currency to the database.
     * */
    public static void addCurrency() throws IOException {

        HashMap<String, Object> newCur = new HashMap<>();

        System.out.println("Insert the name of the currency:");
        String name = scan.nextLine();
        scan.next();
        newCur.put("name", name);

        System.out.println("Insert the exchange rate from euro: ");
        int exch = scan.nextInt();
        newCur.put("manager", exch);

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/addCurrency");
        String jsonString = mapper.writeValueAsString(newCur);
        Entity<String> ent = Entity.json(jsonString);
        Response response = target.request().post(ent);
        String out = response.readEntity(String.class);
        response.close();

        if (out.isEmpty()){
            System.out.println("ERROR: System failed to load the new currency!");
        } else {
            System.out.println("New currency added to the database!");
        }

    }

    // TODO falta testar com o REST
    /**
     * 4.
     * List managers from the database
     * */
    public static void listManagers() throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        map = getListOfManagers();

        if (map.size() > 0){
            System.out.println("\n-> Managers List");
            recursive_nameAndId(map);
        } else {
            System.out.println("There are no managers registered in the system!");
        }
    }

    // TODO falta testar com o REST
    /**
     * 5.
     * List clients from the database
     * */
    public static void listClients() throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        map = getListOfClients();

        /*  FOR EASY DEBUG
        HashMap<String, Object> help1 = new HashMap<>();
        HashMap<String, Object> help2 = new HashMap<>();
        help1.put("id", 1234);
        help1.put("name", "malena");
        help2.put("id", 4321);
        help2.put("name", "eduardo");
        map.put("1", help1);
        map.put("2", help2);
        //System.out.println("\nREAL HASHMAP HERE\n" + map);*/

        if (map.size() > 0){
            System.out.println("\n-> Clients List");
            recursive_nameAndId(map);
        } else {
            System.out.println("There are no clients registered in the system!");
        }
    }

    // TODO falta testar com o REST
    /**
     * 6.
     * List currencies
     * */
    public static void listCurrencies() throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        map = getListOfCurrencies();

        /*  FOR EASY DEBUG
        HashMap<String, Object> help1 = new HashMap<>();
        HashMap<String, Object> help2 = new HashMap<>();
        help1.put("exchange", 1.13);
        help1.put("name", "dollar");
        help2.put("exchange", 0.85);
        help2.put("name", "pound");
        map.put("1", help1);
        map.put("2", help2);
        //System.out.println("\nREAL HASHMAP HERE\n" + map);*/

        if (map.size() > 0){
            System.out.println("\n-> Currencies List");
            recursive_currencyName(map);
        } else {
            System.out.println("There are no currencies registered in the system!");
        }
    }

    //TODO falta testar com o REST
    /**
     * 7.
     * Get the credit per client
     * */
    public static void clientCredit() throws IOException {

        char opt = 'n';

        do{
            recursive_nameAndId(getListOfClients());
            System.out.println("Select the id of the client to see their credit: ");
            int id = scan.nextInt();

            WebTarget target = client.target("http://localhost:8080/rest/services/myservice/clientCredit");
            String jsonString = mapper.writeValueAsString(id);
            Entity<String> ent = Entity.json(jsonString);
            Response response = target.request().post(ent);
            float out = response.readEntity(Float.class);
            response.close();

            if (out == 0){
                System.out.println("ERROR: System failed to see client credit!");
            } else {
                System.out.println("Client with id " + id + " has a credit of +" + out + "€");
            }

            System.out.println("Do you want to search for another client? y/n");
            opt = scan.next().charAt(0);

        } while (opt == 'y');

    }

    //TODO falta testar com o REST
    /**
     * 8.
     * Get the payments per client
     * */
    public static void clientPayment() throws IOException {

        char opt = 'n';

        do{
            recursive_nameAndId(getListOfClients());
            System.out.println("Select the id of the client to see their payments: ");
            int id = scan.nextInt();

            WebTarget target = client.target("http://localhost:8080/rest/services/myservice/clientPayment");
            String jsonString = mapper.writeValueAsString(id);
            Entity<String> ent = Entity.json(jsonString);
            Response response = target.request().post(ent);
            float out = response.readEntity(Float.class);
            response.close();

            if (out == 0){
                System.out.println("ERROR: System failed to see client payments!");
            } else {
                System.out.println("Client with id " + id + " has the total payments of -" + out + "€");
            }

            System.out.println("Do you want to search for another client? y/n");
            opt = scan.next().charAt(0);

        } while (opt == 'y');

    }

    //TODO falta testar com o REST
    /**
     * 9.
     * Get client balance
     * */
    public static void clientBalance() throws IOException {

        char opt = 'n';

        do{
            recursive_nameAndId(getListOfClients());
            System.out.println("Select the id of the client to see their balance: ");
            int id = scan.nextInt();

            WebTarget target = client.target("http://localhost:8080/rest/services/myservice/clientBalance");
            String jsonString = mapper.writeValueAsString(id);
            Entity<String> ent = Entity.json(jsonString);
            Response response = target.request().post(ent);
            float out = response.readEntity(Float.class);
            response.close();

            if (out == 0){
                System.out.println("ERROR: System failed to see client balance!");
            } else {
                System.out.println("Client with id " + id + " has a balance of " + out + "€");
            }

            System.out.println("Do you want to search for another client? y/n");
            opt = scan.next().charAt(0);

        } while (opt == 'y');

    }

    // TODO falta testar com o REST
    /**
     * 10.
     * Get the total credits
     * */
    public static void totalCredits() throws IOException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/totalCredit");
        Response response = target.request().get();
        float out = response.readEntity(Float.class);
        response.close();

        System.out.println("\nTotal credits from the database is " + out + "€");

    }

    // TODO falta testar com o REST
    /**
     * 11.
     * Get the total payments
     * */
    public static void totalPayments() throws IOException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/totalPayments");
        Response response = target.request().get();
        float out = response.readEntity(Float.class);
        response.close();

        System.out.println("\nTotal payments from the database is " + out + "€");

    }

    // TODO falta testar com o REST
    /**
     * 12.
     * Get the total balance
     * */
    public static void totalBalance() throws IOException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/totalBalance");
        Response response = target.request().get();
        float out = response.readEntity(Float.class);
        response.close();

        System.out.printf("\nTotal balance from the database is " + out + "€");
    }

    /**
     * 14.
     * Get the list of clients with no payments for the last 2 months
     * */
    public static void noPaymentsInMonths() throws IOException {

        //  for each client
            // if last payment > 2 months
                // add client to output list
        // print output list

        HashMap<String, Object> map = new HashMap<>();
        map = getListOfClients();

        if (map.size() > 0){
            System.out.println("\n-> Clients List");
            recursive_nameAndId(map);
        } else {
            System.out.println("There are no clients registered in the system!");
        }

    }

    /**
     * 15.
     * Get the data of the person with the highest outstanding debt
     * */
    public static void heighestDebt() throws JsonProcessingException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/heighestDebt");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        HashMap<String, Object> map = mapper.readValue(jsonString, HashMap.class);
        response.close();

        System.out.printf("\nPerson with the heighest debt:");
        System.out.println("\t" + map.get("id") + "\t" + map.get("name"));

    }

    /**
     * 16.
     * Get the data of the manager who has made the highest revenue in payments from his or her clients.
     * */
    public static void managerHighestRevenue() throws IOException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/heighestRevenue");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        HashMap<String, Object> map = mapper.readValue(jsonString, HashMap.class);
        response.close();

        System.out.printf("\nManager with the heighest revenue:");
        System.out.println("\t" + map.get("id") + "\t" + map.get("name"));

    }

    public static void main(String[] args) throws IOException {

        int opt;
        scan = new Scanner(System.in);
        mapper = new ObjectMapper();
        client = ClientBuilder.newClient();

        System.out.println("\n===== Welcome to the Admin Console=====");

        do {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("Please choose one of the following options:");
            System.out.println("\t1\tAdd new manager ~~~ to be tested yet with REST ~~~");
            System.out.println("\t2\tAdd new client ~~~ to be tested yet with REST ~~~");
            System.out.println("\t3\tAdd new currency ~~~ to be tested yet with REST ~~~");
            System.out.println("\t4\tList managers ~~~ to be tested yet with REST ~~~");
            System.out.println("\t5\tList clients ~~~ to be tested yet with REST ~~~");
            System.out.println("\t6\tList currencies ~~~ to be tested yet with REST ~~~");
            System.out.println("\t7\tSee credits per client ~~~ to be tested yet with REST ~~~");
            System.out.println("\t8\tSee payments per client ~~~ to be tested yet with REST ~~~");
            System.out.println("\t9\tGet current balance of a client ~~~ to be tested yet with REST ~~~");
            System.out.println("\t10\tSee total credits ~~~ to be tested yet with REST ~~~");
            System.out.println("\t11\tSee total payments ~~~ to be tested yet with REST ~~~");
            System.out.println("\t12\tSee total balance ~~~ to be tested yet with REST ~~~");

            System.out.println("\t14\tList of clients with no payments for the last 2 months ~~~ not done yet ~~~");
            System.out.println("\t16\tSee manager with highest revenue ~~~ not done yet ~~~");
            System.out.println("\t0\tExit");
            opt = scan.nextInt();

            switch (opt) {
                case 0:
                    break;
                case 1:
                    addManagers();
                    break;
                case 2:
                    addClients();
                    break;
                case 3:
                    addCurrency();
                    break;
                case 4:
                    listManagers();
                    break;
                case 5:
                    listClients();
                    break;
                case 6:
                    listCurrencies();
                    break;
                case 7:
                    clientCredit();
                    break;
                case 8:
                    clientPayment();
                    break;
                case 9:
                    clientBalance();
                    break;
                case 10:
                    totalCredits();
                    break;
                case 11:
                    totalPayments();
                    break;
                case 12:
                    totalBalance();
                    break;
                case 14:
                    noPaymentsInMonths();
                    break;
                case 16:
                    managerHighestRevenue();
                    break;
                default:
                    System.out.println("ERROR -> Please choose a number between 1 and 16\nInsert 0 if you wish to leave");
                    break;
            }
        } while (opt != 0);

        System.out.println("\n===== Thank you! See you next time! =====\n");

    }

}
