package namedays;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * The main class.
 *
 * @author Reetta Puska
 */
public class Main {

    /**
     * Command line tool for viewing namedays by date.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UI ui = new UI();
        ui.start(args);
    }

}
