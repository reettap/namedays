/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namedays;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author reetta
 */
public class NamedaysTest {

    public void test(String filename, String date, List<String> exp) {
        Namedays namedays;

        try {
            namedays = Namedays.fromFile(filename);
            ArrayList<String> names = namedays.fetchNamesForDate(date);

            if (exp == null && names == null) {
                return;
            }

            assertEquals(exp.size(), names.size());
            for (int i = 0; i < exp.size(); i++) {
                assertEquals(exp.get(i), names.get(i));
            }

        } catch (FileNotFoundException e) {
            fail("Valid file was not found: " + e);
        }

    }

    @Test
    public void testLoadingFromValidFile() {
        String filename = "data.csv";
        String date = "01.01.";
        List<String> expNames = Arrays.asList("Aatami", "Aatu");
        test(filename, date, expNames);
    }

    @Test
    public void testLoadingFromValidFileInvalidDate() {
        String filename = "data.csv";
        String date = "01.30.";
        List<String> expNames = null;
        test(filename, date, expNames);
    }

    @Test
    public void testLoadingFromValidFileInvalidFormDate() {
        String filename = "data.csv";
        String date = "Bla";
        List<String> expNames = null;
        test(filename, date, expNames);
    }

    @Test
    public void testLoadingFromValidFileShortDate() {
        String filename = "data.csv";
        String date = "2.1";
        List<String> expNames = Arrays.asList("Siiri");
        test(filename, date, expNames);
    }

    @Test
    public void testLoadingFromInvalidFile() {
        String filename = "nonexistentFile";
        try {
            Namedays namedays = Namedays.fromFile(filename);
            fail("Invalid filename did not throw an exception");
        } catch (Exception e) {
        }
    }
}
