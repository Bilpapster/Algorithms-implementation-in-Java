package Gale_Shapley_algorithm;

/**
 * A simple class representing a printer to the console.
 * Depending on the value of DETAILED_PRINTING, the printer
 * can print or not a requested message.
 */
public class FlexiblePrinter {
    private static final boolean DETAILED_PRINTING = false;

    public static void print(String stringToPrint) {
        if (DETAILED_PRINTING) {
            System.out.println(stringToPrint);
        }
    }
}
