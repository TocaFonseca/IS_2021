import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.*;
import java.util.*;
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
    public static List<Map<String, Object>> getListOfClients() throws IOException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/clientsList");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        List<Map<String, Object>> map = mapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});
        response.close();

        return map;
    }

    /**
     * Auxiliary method that gets the managers list
     * from the database from the REST module
     * */
    public static List<Map<String, Object>> getListOfManagers() throws IOException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/managersList");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        List<Map<String, Object>> map = mapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});
        response.close();

        return map;

    }

    // TODO not sure we're gonna need this
    /**
     * Auxiliary method that gets the payments list
     * from the database from the REST module
     * */
    public static List<Map<String, Object>> getListOfPayments() throws IOException{

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/paymentsList");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        List<Map<String, Object>> map = mapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});
        response.close();

        return map;

    }

    /**
     * Auxiliary method that gets the currencies list
     * from the database from the REST module
     * */
    public static List<Map<String, Object>> getListOfCurrencies() throws IOException{

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/currenciesList");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        List<Map<String, Object>> map = mapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});
        response.close();

        return map;

    }

    /**
     * Recursive method that searches for a name and a id
     * from the hashmap with transactions older than 2 months,
     * and then presents in the CLI
     * */
    public static void recursive_nameAndId(List<Map<String, Object>> map, String type){
        for (Map<String, Object> entry : map) {
            if (entry.containsKey(type) && entry.containsKey("name")){
                System.out.println("\t" + entry.get(type) + "\t" + entry.get("name"));
            }
        }
    }

    /**
     * Recursive method that searches for a currencies's
     * name and exchange rate from the hashmap, and then
     * presents in the CLI
     * */
    public static void recursive_currencyName(List<Map<String, Object>> map){
        for (Map<String, Object> entry : map) {
            if (entry.containsKey("exchange_rate") && entry.containsKey("name")){
                System.out.println("\t" + entry.get("name") + "\t" + entry.get("exchange_rate"));
            }
        }
    }

    // TODO not sure we're gonna need this
    /**
     * Recursive method that calculates the total balance
     * from the transactions saved in the database (gains and losses)
     * */
    public static double recursive_totalBalance(List<Map<String, Object>> map, double latest_sum){

        double sum = latest_sum;

        for (Map<String, Object> entry : map) {
            if (entry.containsKey("price") && entry.containsKey("credit")){
                double aux = Double.valueOf((entry.get("price").toString()));
                sum += aux;
            }
        }

        return sum;

    }

    /**
     * 1.
     * Add managers to the database.
     * */
    public static void addManagers() throws IOException {

        HashMap<String, Object> newMan = new HashMap<>();

        System.out.println("Insert the name of the manager:");
        String name = scan.next() + scan.nextLine();
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

    /**
    * 2.
     * Add clients to the database.
     * Each client has a manager.
    * */
    public static void addClients() throws IOException {

        HashMap<String, Object> newClient = new HashMap<>();

        System.out.println("Insert the name of the client:");
        String name = scan.next() + scan.nextLine();
        newClient.put("name", name);

        System.out.println("\tList of Managers: ");
        recursive_nameAndId(getListOfManagers(), "manager_id");
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

    /**
     * 3.
     * Add currency to the database.
     * */
    public static void addCurrency() throws IOException {

        HashMap<String, Object> newCur = new HashMap<>();

        System.out.println("Insert the name of the currency:");
        String name = scan.next() + scan.nextLine();
        newCur.put("name", name);

        System.out.println("Insert the exchange rate from euro: ");
        String exch = scan.next() + scan.nextLine();
        newCur.put("exchange_rate", exch);

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

    /**
     * 4.
     * List managers from the database
     * */
    public static void listManagers() throws IOException {

        List<Map<String, Object>> map;
        map = getListOfManagers();

        if (map != null){
            System.out.println("\n-> Managers List");
            recursive_nameAndId(map, "manager_id");
        } else {
            System.out.println("There are no managers registered in the system!");
        }
    }

    /**
     * 5.
     * List clients from the database
     * */
    public static void listClients() throws IOException {

        List<Map<String, Object>> map;
        map = getListOfClients();

        if (map != null){
            System.out.println("\n-> Clients List");
            recursive_nameAndId(map, "client_id");
        } else {
            System.out.println("There are no clients registered in the system!");
        }
    }

    /**
     * 6.
     * List currencies
     * */
    public static void listCurrencies() throws IOException {

        List<Map<String, Object>> map;
        map = getListOfCurrencies();

        if (map != null){
            System.out.println("\n-> Currencies List");
            recursive_currencyName(map);
        } else {
            System.out.println("There are no currencies registered in the system!");
        }
    }

    /**
     * 7.
     * Get the credit per client
     * */
    public static void clientCredit() throws IOException {

        char opt = 'n';

        List<Map<String, Object>> map;
        HashMap<String, Object> aux_map = new HashMap<>();
        map = getListOfClients();

        do{
            recursive_nameAndId(map, "client_id");
            System.out.println("Select the id of the client to see their credit: ");
            String id = scan.next() + scan.nextLine();
            aux_map.put("id", id);

            WebTarget target = client.target("http://localhost:8080/rest/services/myservice/clientCredit");
            String jsonString = mapper.writeValueAsString(aux_map);
            Entity<String> ent = Entity.json(jsonString);
            Response response = target.request().post(ent);
            String aux = response.readEntity(String.class);
            response.close();
            float out = Float.parseFloat(aux);

            if (out == 0){
                System.out.println("ERROR: System failed to see client credit!");
            } else {
                System.out.println("Client with id " + id + " has a credit of +" + out + "€");
            }

            System.out.println("Do you want to search for another client? y/n");
            opt = scan.next().charAt(0);

        } while (opt == 'y');

    }

    /**
     * 8.
     * Get the payments per client
     * */
    public static void clientPayment() throws IOException {

        char opt = 'n';
        HashMap<String, Object> aux_map = new HashMap<>();
        List<Map<String, Object>> map = getListOfClients();

        do{
            recursive_nameAndId(map, "client_id");
            System.out.println("Select the id of the client to see their payments: ");
            String id = scan.next() + scan.nextLine();
            aux_map.put("id", id);

            WebTarget target = client.target("http://localhost:8080/rest/services/myservice/clientPayment");
            String jsonString = mapper.writeValueAsString(aux_map);
            Entity<String> ent = Entity.json(jsonString);
            Response response = target.request().post(ent);
            String aux = response.readEntity(String.class);
            response.close();
            float out = Float.parseFloat(aux);

            if (out == 0){
                System.out.println("ERROR: System failed to see client payments!");
            } else {
                System.out.println("Client with id " + id + " has the total payments of " + out + "€");
            }

            System.out.println("Do you want to search for another client? y/n");
            opt = scan.next().charAt(0);

        } while (opt == 'y');

    }

    /**
     * 9.
     * Get client balance
     * */
    public static void clientBalance() throws IOException {

        char opt = 'n';
        HashMap<String, Object> aux_map = new HashMap<>();
        List<Map<String, Object>> map = getListOfClients();

        do{
            recursive_nameAndId(map, "client_id");
            System.out.println("Select the id of the client to see their payments: ");
            String id = scan.next() + scan.nextLine();
            aux_map.put("id", id);

            WebTarget target = client.target("http://localhost:8080/rest/services/myservice/clientBalance");
            String jsonString = mapper.writeValueAsString(aux_map);
            Entity<String> ent = Entity.json(jsonString);
            Response response = target.request().post(ent);
            String aux = response.readEntity(String.class);
            response.close();
            float out = Float.parseFloat(aux);

            if (out == 0){
                System.out.println("ERROR: System failed to see client balance!");
            } else {
                System.out.println("Client with id " + id + " has a balance of " + out + "€");
            }

            System.out.println("Do you want to search for another client? y/n");
            opt = scan.next().charAt(0);

        } while (opt == 'y');

    }

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
     * 13.
     * Compute the bill for each client for the last month 1
     * use a tumbling time window
     * */
    public static void monthBill() throws IOException, ParseException {


        char opt;

        HashMap<String, Object> aux_map = new HashMap<>();

        do{
            System.out.println("Pick a date (dd/MM/yyyy)");
            String date = scan.next() + scan.nextLine();
            aux_map.put("date", date);

            WebTarget target = client.target("http://localhost:8080/rest/services/myservice/monthBill");
            String jsonString = mapper.writeValueAsString(aux_map);
            Entity<String> ent = Entity.json(jsonString);
            Response response = target.request().post(ent);
            String aux = response.readEntity(String.class);
            response.close();
            List<Map<String, Object>> map = mapper.readValue(aux, new TypeReference<List<Map<String, Object>>>() {});

            if (map != null){
                System.out.println("\n-> Monthly Balance(up to "+date+"):");
                for (Map<String, Object> entry : map) {
                    if (entry.containsKey("client") && entry.containsKey("balance")){
                        System.out.println("\t" + entry.get("client") + "\t" + entry.get("balance"));
                    }
                }
            } else {
                System.out.println("There are no currencies registered in the system!");
            }


           /* float out = Float.parseFloat(aux);

            if (out == 0){
                System.out.println("ERROR: System failed to see client credit!");
            } else {
                System.out.println("Client with id " + id + " has a credit of +" + out + "€");
            }
            */
            System.out.println("Do you want to search for another client? y/n");
            opt = scan.next().charAt(0);



        } while (opt == 'y');


        /*
        List<Map<String, Object>> map = getListOfClients();

        if (map != null){
            System.out.println("\n-> Clients List");
            recursive_nameAndId(map, "client_id");
        } else {
            System.out.println("There are no clients registered in the system!");
        }
        */

    }

    /**
     * 14.
     * Get the list of clients with no payments for the last 2 months
     * */
    public static void noPaymentsInMonths() throws IOException {

        List<Map<String, Object>> map;
        map = getListOfClients();

        if (map != null){
            System.out.println("\n-> Clients List");
            recursive_nameAndId(map, "client_id");
        } else {
            System.out.println("There are no clients registered in the system!");
        }

    }

    /**
     * 15.
     * Get the data of the person with the highest outstanding debt
     * */
    public static void highestDebt() throws JsonProcessingException {

        WebTarget target = client.target("http://localhost:8080/rest/services/myservice/heighestDebt");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);
        HashMap<String, Object> map = mapper.readValue(jsonString, HashMap.class);
        response.close();

        System.out.printf("\nPerson with the highest debt:");
        System.out.println("\t" + map.get("client_id") + "\t" + map.get("name"));

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

        System.out.printf("\nManager with the highest revenue:");
        System.out.println("\t" + map.get("manager_id") + "\t" + map.get("name"));

    }

    public static void main(String[] args) throws IOException, ParseException {

        int opt;
        scan = new Scanner(System.in);
        mapper = new ObjectMapper();
        client = ClientBuilder.newClient();

        System.out.println("\n===== Welcome to the Admin Console=====");

        do {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("Please choose one of the following options:");
            System.out.println("\t1\tAdd new manager");
            System.out.println("\t2\tAdd new client");
            System.out.println("\t3\tAdd new currency");
            System.out.println("\t4\tList managers");
            System.out.println("\t5\tList clients");
            System.out.println("\t6\tList currencies");
            System.out.println("\t7\tSee credits per client");
            System.out.println("\t8\tSee payments per client");
            System.out.println("\t9\tGet current balance of a client");
            System.out.println("\t10\tSee total credits");
            System.out.println("\t11\tSee total payments");
            System.out.println("\t12\tSee total balance ");
            System.out.println("\t13\tLast month bill from each client");
            System.out.println("\t14\tList of clients with no payments for the last 2 months ~~~ not done yet ~~~");
            System.out.println("\t15\tClient with highest debt");
            System.out.println("\t16\tSee manager with highest revenue");
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
                case 13:
                    monthBill();
                    break;
                case 14:
                    noPaymentsInMonths();
                    break;
                case 15:
                    highestDebt();
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
