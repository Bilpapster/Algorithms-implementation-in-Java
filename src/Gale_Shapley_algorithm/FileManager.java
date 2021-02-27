package Gale_Shapley_algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileManager {

    public static ArrayList<String> getHeadedPreferenceMatrices(String fileName) {
        ArrayList<String> headedMatricesToReturn = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(fileName)).useDelimiter("#");
            String headedMatrix;
            while (fileScanner.hasNext()) {
                headedMatricesToReturn.add(fileScanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return headedMatricesToReturn;
    }

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
