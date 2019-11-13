package namedays;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Represents namedays associated to dates.
 *
 * @author Reetta Puska
 */
public class Namedays {

    private final HashMap<MonthDay, ArrayList<String>> namedays;
    private static Logger LOG = Logger.getLogger(Namedays.class.getName());

    private Namedays(HashMap<MonthDay, ArrayList<String>> namedays) {
        this.namedays = namedays;
    }

    /**
     * Returns a Namedays object parsed from the given file.
     *
     * @param filename name of the given file
     * @return Namedays parsed from the file
     * @throws java.io.FileNotFoundException if file doesn't exist
     */
    public static Namedays fromFile(String filename) throws FileNotFoundException {
        HashMap<MonthDay, ArrayList<String>> namedays = new HashMap();
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            addNamedayInMap(namedays, line);
        }

        return new Namedays(namedays);
    }

    /**
     * Parses a line containing date and names, and adds it to the given hashmap
     *
     * @param line line of a file to get parsed and added to the hashmap
     */
    private static void addNamedayInMap(
            HashMap<MonthDay, ArrayList<String>> namedays, String line) {
        String[] parts = line.split(";");
        if (parts.length < 2) {
            return; //line not containing enough info
        }

        MonthDay date;
        try {
            date = parseDate(parts[0]);
        } catch (ParseException e) {
            LOG.warning("File contained invalid date, skipped line");
            return;
        }

        //parse names
        String[] names = parts[1].split(",");
        ArrayList<String> nameList = new ArrayList();
        for (String name : names) {
            nameList.add(name.trim());
        }

        namedays.putIfAbsent(date, new ArrayList());
        namedays.get(date).addAll(nameList);
    }

    /**
     *
     * @param dateString
     * @return
     */
    public ArrayList<String> fetchNamesForDate(String dateString) {
        try {
            MonthDay date = parseDate(dateString);
            return namedays.get(date);
        } catch (ParseException e) {
            System.out.println("Given date is invalid");
        }

        return null;
    }

    private static MonthDay parseDate(String dateString) throws ParseException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d.M[.]");
            return MonthDay.parse(dateString, format);
        } catch (DateTimeParseException e) {
            throw new ParseException("Parsing date failed", 0);
        }
    }

}
