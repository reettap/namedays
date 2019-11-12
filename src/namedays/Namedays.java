package namedays;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 *
 * @author Reetta Puska
 */
public class Namedays {

    /**Command line tool for viewing namedays by date
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //define filename and date:
        String filename = retrieveFilename(args);
        String date = retrieveDate(args, scan);

        //fetch data from the file
        HashMap<String, String> namedays = readFile(filename);

        //show the result for given date
        printNames(namedays, date);
    }

    /**Reads file and returns a hashmap containing the date-names pairs
     *
     * @param filename
     * @return
     */
    public static HashMap<String, String> readFile(String filename) {
        //
        HashMap<String, String> namedays = new HashMap();

        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNext()) {
                String line = scan.nextLine();
                addNamedayInMap(line, namedays);
            }
        } catch (FileNotFoundException e) {
            System.out.println("reading file \"" + filename + "\" failed: " + e);
        }
        return namedays;
    }

    /** Parses a line containing date and names, and adds it to the given hashmap
     *
     * @param line line of a file to get parsed and added to the hashmap
     * @param namedays hashmap for adding the date-name pairs
     */
    public static void addNamedayInMap(String line, HashMap<String, String> namedays) {
        String[] parts = line.split(";");
        if (parts.length >= 2) {
            namedays.put(parts[0], parts[1]);
        }
    }

    /**Fetches the date from the arguments if possible,
     * otherwise prompts the user for a date.
     *
     * @param args command line arguments to be parsed
     * @param scan scanner for user input
     * @return String representing the date
     */
    public static String retrieveDate(String[] args, Scanner scan) {
        String date;
        try {
            date = args[0];
        } catch (Exception e) {
            System.out.println("Please give the date (eg. 4.7. or 16.12.)");
            date = scan.nextLine();
        }

        return date;
    }

    /**Fetches the filename from the arguments if possible,
     * otherwise uses the default "nimet.csv"
     *
     * @param args command line arguments to be parsed
     * @return String representing the filename
     */
    public static String retrieveFilename(String[] args) {
        String file;
        try {
            file = args[1];
        } catch (Exception e) {
            file = "nimet.csv";
        }
        return file;
    }

    /**Prints the namedays for given date
     *
     * @param namedays Hashmap containing the date-nameday pairs
     * @param date date for which the names are returned
     */
    public static void printNames(HashMap<String, String> namedays, String date) {
        String names = namedays.get(date);
        if (names == null) {
            System.out.println("no names foud for " + date);
        } else {
            System.out.println("\non " + date + " we celebrate the nameday of: \n" + names);
        }
    }

}
