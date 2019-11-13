package namedays;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the interaction with user
 *
 * @author Reetta Puska
 */
public class UI {

    private final String DEFAULT_FILENAME = "nimet.csv";

    /**
     * Create a user interface 
     */
    public UI() {
    }

    /**
     * Starts the program depending on the command line arguments.
     * 
     * @param args passed command line arguments
     */
    public void start(String[] args) {
        switch (args.length) {
            case 0:
                startInteractive();
                break;
            case 1:
                startQuiet(args[0], DEFAULT_FILENAME);
                break;
            case 2:
                startQuiet(args[0], args[1]);
                break;
            default:
                System.out.println("namedays: bad usage");
                System.out.println("Usage: 'java -jar dist/namedays.jar <date>'");
                System.out.println("OR     'java -jar dist/namedays.jar <date> <filename>'");
                System.out.println("OR     'java -jar dist/namedays.jar'");
                break;
        }
    }

    /**
     * Verbose communication with the user when no command line arguments are
     * present.
     */
    public void startInteractive() {
        Namedays namedays;
        Scanner scan = new Scanner(System.in);

        System.out.println("Insert the filename: ");
        String filename = scan.nextLine();

        try {
            namedays = Namedays.fromFile(filename);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + e);
            return;
        }

        System.out.println("Insert the date: ");
        String date = scan.nextLine();

        printNames(namedays, date);

    }

    /**
     * Quiet user interaction when parameters have been given.
     *
     * @param date
     * @param filename
     */
    public void startQuiet(String date, String filename) {
        Namedays namedays;

        try {
            namedays = Namedays.fromFile(filename);
        } catch (FileNotFoundException e) {
            System.out.println("File was not found: " + e);
            return;
        }

        printNames(namedays, date);

    }

    /**
     * Prints the namedays for given date.
     *
     * @param namedays Namedays object from which the names are fetched
     * @param date date for which the names are printed
     */
    public static void printNames(Namedays namedays, String date) {
        ArrayList<String> names = namedays.fetchNamesForDate(date);

        if (names == null) {
            System.out.println("no names found for " + date);
        } else {
            System.out.println("\non " + date + " we celebrate the nameday of:");
            for (String name : names) {
                System.out.println(name);
            }
        }
    }

}
