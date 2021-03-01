package Gale_Shapley_algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * A class that handles the data reading from the text file. Offers static methods for
 * both reading and tokenizing the data, in order to be used in constructing the
 * senders and receivers actual objects.
 */
public class FileManager {

    /**
     * Reads the data file and tokenizes the two headed matrices that are expected to be found
     * inside it. Returns an ArrayList that contains as entries these two headed preference
     * matrices. It it important that the first line of each matrix is the header of it, rather
     * than actual data, which needs special treatment when extracting the actual data.
     *
     * @param fileName the name of the data file to read from
     * @return an array list containing the (two) headed preference matrices
     */
    public static ArrayList<String> getHeadedPreferenceMatrices(String fileName) {
        ArrayList<String> headedMatricesToReturn = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(fileName)).useDelimiter("#");
            while (fileScanner.hasNext()) {
                headedMatricesToReturn.add(fileScanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return headedMatricesToReturn;
    }

    /**
     * Given a headed table of data, extracts the actual data lines and constructs an
     * ArrayList with them.
     *
     * @param stringToTokenLines the headed table to extract data from
     * @return an ArrayList that contains the actual data lines
     */
    public static ArrayList<String> getActualDataLines(String stringToTokenLines) {
        ArrayList<String> dataLinesToReturn = new ArrayList<>();
        String lineToken;
        StringTokenizer tokenizer = new StringTokenizer(stringToTokenLines, "\n");

        do {
            lineToken = tokenizer.nextToken();
        } while (lineToken.contains(":") || lineToken.isBlank());

        do {
            dataLinesToReturn.add(lineToken.trim());
            if (!tokenizer.hasMoreTokens()) {
                break;
            }
            lineToken = tokenizer.nextToken();
        } while (true);

        return dataLinesToReturn;
    }
}
