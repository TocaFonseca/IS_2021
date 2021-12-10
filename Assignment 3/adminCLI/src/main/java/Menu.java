import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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

        WebTarget target = client.target("http://localhost:8080/myservice/clientsList");
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

        WebTarget target = client.target("http://127.0.0.1:8080/myservice/managersList");
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

        WebTarget target = client.target("http://localhost:8080/myservice/paymentsList");
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

        WebTarget target = client.target("http://localhost:8080/myservice/paymentsList/currenciesList");
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
     * Recursive method that calculates the total credits
     * from the transactions saved in the database (only gains)
     * */
    public static double recursive_totalCredits(HashMap<String, Object> map, double latest_sum){

        double sum = latest_sum;

        for (HashMap.Entry<String, Object> entry : map.entrySet()) {
            if (map.containsKey("price") && map.containsKey("credit") && Boolean.TRUE.equals(map.get("credit"))){
                double aux = Double.valueOf((map.get("price").toString()));
                return sum + aux;
            } else if (entry.getValue() instanceof HashMap) {
                sum = recursive_totalCredits((HashMap<String, Object>) entry.getValue(), sum);
            }
        }

        return sum;

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

    // TODO falta completar com o REST
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

        // make sure client entity has id

        // add client to the database

    }

    // TODO falta testar com o REST
    /**
     * 4.
     * List managers from the database
     * */
    public static void listManagers() throws IOException {

        HashMap<String, Object> map = new HashMap<>();
        map = getListOfManagers();

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
            System.out.println("\n-> Managers List");
            recursive_nameAndId(map);
        } else {
            System.out.println("There are no managers registered in the system!");
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

    /**
     * 8.
     * Get the payments per client
     * */
    public static void paymentsFromAll() throws IOException {

        //getListOfClients();
        // for each client
            // print client name
            // for each client payment
                // print sum and currency

    }

    // TODO falta testar com o REST
    /**
     * 10.
     * Get the total credits
     * */
    public static void totalCredits() throws IOException {

        double sum = 0;
        HashMap<String, Object> map = new HashMap<>();
        map = getListOfPayments();

        /*  FOR EASY DEBUG
        HashMap<String, Object> help1 = new HashMap<>();
        HashMap<String, Object> help2 = new HashMap<>();
        HashMap<String, Object> help3 = new HashMap<>();
        help1.put("price", 1.13);
        help1.put("credit", true);
        help2.put("price", 0.85);
        help2.put("credit", false);
        help3.put("price", 1);
        help3.put("credit", true);
        map.put("1", help1);
        map.put("2", help2);
        map.put("3", help3);
        //System.out.println("\nREAL HASHMAP HERE\n" + map);*/

        System.out.println("\nTotal credits from the database is " + recursive_totalCredits(map, 0) + "€"); // não esquecer as conversões

    }

    // TODO falta testar com o REST
    /**
     * 12.
     * Get the total balance
     * */
    public static void totalBalance() throws IOException {

        double sum = 0;
        HashMap<String, Object> map = new HashMap<>();
        map = getListOfPayments();

        /*  FOR EASY DEBUG
        HashMap<String, Object> help1 = new HashMap<>();
        HashMap<String, Object> help2 = new HashMap<>();
        HashMap<String, Object> help3 = new HashMap<>();
        help1.put("price", 1.13);
        help1.put("credit", true);
        help2.put("price", -0.85);
        help2.put("credit", false);
        help3.put("price", 1);
        help3.put("credit", true);
        map.put("1", help1);
        map.put("2", help2);
        map.put("3", help3);
        //System.out.println("\nREAL HASHMAP HERE\n" + map);*/

        System.out.printf("\nTotal balance from the database is %.2f €\n", recursive_totalBalance(map, 0)); // não esquecer as conversões
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
     * 16.
     * Get the data of the manager who has made the highest revenue in payments from his or her clients.
     * */
    public static void managerHighestRevenue() throws IOException {

        //getListOfManagers();
        // for each manager
            // get list of clients
            // compare with highest recorded -> not sure what to compare with
        // print manager and revenue

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
            System.out.println("\t2\tAdd new client ~~~ not working ~~~");
            System.out.println("\t4\tList managers ~~~ working without rest ~~~");
            System.out.println("\t6\tList currencies ~~~ working without rest ~~~");
            System.out.println("\t8\tSee payments per client ~~~ not done yet ~~~");
            System.out.println("\t10\tSee total credits ~~~ working without rest ~~~");
            System.out.println("\t12\tSee total balance ~~~ working without rest ~~~");
            System.out.println("\t14\tList of clients with no payments for the last 2 months ~~~ not done yet ~~~");
            System.out.println("\t16\tSee manager with highest revenue ~~~ not done yet ~~~");
            System.out.println("\t0\tExit");
            opt = scan.nextInt();

            switch (opt) {
                case 0:
                    break;
                case 2:
                    addClients();
                    break;
                case 4:
                    listManagers();
                    break;
                case 6:
                    listCurrencies();
                    break;
                case 8:
                    paymentsFromAll();
                    break;
                case 10:
                    totalCredits();
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
