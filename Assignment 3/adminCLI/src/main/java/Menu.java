import java.util.Scanner;

public class Menu {

    // private Manager mng; // this session's manager
    private static Scanner scan;

    public static void getListOfClients(){

        // return list of clients from database

    }

    public static void getListOfManagers(){

        // return list of managers from database

    }

    public static void getListOfPayments(){

        // return list of payments from database

    }

    /**
    * 2.
     * Add clients to the database.
     * Each client has a manager.
    * */
    public static void addClients(){

        System.out.println("Insert client's name:");
        String name = scan.nextLine();
        scan.next();

        // add name to client
        // add this.manager to client
        // make sure client entity has id
        // add client to the database

    }

    /**
     * 4.
     * List managers from the database
     * */
    public static void listManagers(){

        getListOfManagers();
        // if list size >= 1
            // print list managers
        // else
        System.out.println("There are no managers registered in the system!");
    }

    /**
     * 6.
     * List currencies
     * */
    public static void listCurrencies(){

        // get currencies ?????
        // if list size >= 1
            // print list currencies
        // else
            System.out.println("There are no currencies registered in the system!");
    }

    /**
     * 8.
     * Get the payments per client
     * */
    public static void paymentsFromAll(){

        getListOfClients();
        // for each client
            // print client name
            // for each client payment
                // print sum and currency

    }

    /**
     * 10.
     * Get the total credits
     * */
    public static void totalCredits(){

        int sum = 0;

        getListOfPayments();
        // for each payment
            // sum += payment.credits

        System.out.println("Total credits from the database is " + sum + "€"); // não esquecer as conversões

    }

    /**
     * 12.
     * Get the total balance
     * */
    public static void totalBalance(){
        // dunno
    }

    /**
     * 14.
     * Get the list of clients with no payments for the last 2 months
     * */
    public static void noPaymentsInMonths(){

        getListOfClients();
        //  for each client
            // if last payment > 2 months
                // add client to output list
        // print output list

    }

    /**
     * 16.
     * Get the data of the manager who has made the highest revenue in payments from his or her clients.
     * */
    public static void managerHighestRevenue(){

        getListOfManagers();
        // for each manager
            // get list of clients
            // compare with highest recorded -> not sure what to compare with
        // print manager and revenue

    }

    public static void main(String[] args){

        int opt;
        scan = new Scanner(System.in);


        System.out.println("\n===== Welcome to the Admin Console=====");

        do {
            System.out.println("\n===== Admin Menu =====");
            System.out.println("Please choose one of the following options:");
            System.out.println("\t2\tAdd new client");
            System.out.println("\t4\tList managers");
            System.out.println("\t6\tList currencies");
            System.out.println("\t8\tSee payments per client");
            System.out.println("\t10\tSee total credits");
            System.out.println("\t12\tSee total balance");
            System.out.println("\t14\tList of clients with no payments for the last 2 months");
            System.out.println("\t16\tSee manager with highest revenue");
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
