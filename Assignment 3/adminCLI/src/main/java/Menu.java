import java.util.Scanner;

public class Menu {

    public static void main(String[] args){

        int opt;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("\n===== Welcome to the Administrators Menu =====\n");
            System.out.println("Please choose one of the following options:");
            System.out.println("\t 0 -> Exit");
            opt = scan.nextInt();

            switch (opt) {
                case 0:
                    break;
                default:
                    System.out.println("ERROR -> Please choose a number between 1 and 16\nInsert 0 if you wish to leave");
            }
        } while (opt != 0);

    }

}
