
package namedays;

import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

public class Namedays {

    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello there!");
        
        //define filename and date:
        String filename = filename(args);
        String date = date(args, scan);
        
        //read file
        HashMap<String, String> namedays = readFile(filename);
        
        //show the result for given date
        printNames(namedays, date);
    }
    
    public static HashMap<String, String> readFile(String filename){
        //Read file and return hashmap containing the date-names pairs
        HashMap<String, String> namedays = new HashMap();
        
        try{
            Scanner scan = new Scanner(new File(filename));
            while(scan.hasNext()){
                String line = scan.nextLine();
                addNamedayInMap(line, namedays);
            }
        }catch (Exception e){
            System.out.println("reading file \"" + filename + "\" failed: " + e);
        }
        return namedays;
    }
    
    public static void addNamedayInMap(String line, HashMap<String, String> hm){
        try{
            String[] t = line.split(";");
            hm.put(t[0], t[1]);
        }catch (Exception e){
            System.out.println("reading a line in the file failed: " + e);
        }
    }
    
    public static String date(String[] args, Scanner scan){
        String date;
        try{
            date = args[0];
        }catch(Exception e){
            System.out.println("Please give the date (eg. 4.7. or 16.12.)");
            date = scan.nextLine();
        }
        
        return date;
    }
    
    public static String filename(String[] args){
        String file;
        try{
            file = args[1];
        }catch (Exception e){
            file = "nimet.csv";
        }
        return file;
    }
    
    public static void printNames(HashMap<String, String> namedays, String date){
        
        String names = namedays.get(date);
        if(names == null){
            System.out.println("no names foud for " + date);
        } else {
            System.out.println("\non " + date + " we celebrate the nameday of: \n" + names);
        }        
    }
    
    
}
