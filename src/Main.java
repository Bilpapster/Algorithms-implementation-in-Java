import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("Gale Shapley.txt")).useDelimiter("#");
        String headedPreferenceMatrix, lineToken;
        HashMap<Integer, Sender> senders = new HashMap<>();
        HashMap<Integer, Receiver> receivers = new HashMap<>();
        int index;



        for (int involvedCommunity = 0; involvedCommunity < 2; involvedCommunity++) {
            headedPreferenceMatrix = fileScanner.next();
            StringTokenizer lineTokenizer = new StringTokenizer(headedPreferenceMatrix, "\n");

            do {
                lineToken = lineTokenizer.nextToken();
            } while (lineToken.contains(":") || lineToken.isBlank());

            index = 1;
            if (involvedCommunity == 0) {
                do {
                    Sender sender = new Sender(lineToken.trim());
                    senders.put(index++, sender);
//                    System.out.println(sender.toString());
                    if (!lineTokenizer.hasMoreTokens()) {
                        break;
                    }
                    lineToken = lineTokenizer.nextToken();
                } while (true);
            } else {
                do {
                    Receiver receiver = new Receiver(0, lineToken.trim());
                    receivers.put(index++, receiver);
//                    System.out.println(receiver.toString());
                    if (!lineTokenizer.hasMoreTokens()) {
                        break;
                    }
                    lineToken = lineTokenizer.nextToken();
                } while (true);
            }
        }

        for (Sender sender : senders.values()) {
            System.out.println(sender);
        }
        for (Receiver receiver : receivers.values()) {
            System.out.println(receiver);
        }
    }
}