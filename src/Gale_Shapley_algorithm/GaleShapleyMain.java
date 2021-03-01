package Gale_Shapley_algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * The main class for the Gale Shapley algorithm implementation. The class drives the execution of the algorithm.
 */
public class GaleShapleyMain {

    private static HashMap<Integer, Sender> senders = new HashMap<>();
    private static HashMap<Integer, Receiver> receivers = new HashMap<>();

    public static void main(String[] args) {

        setUpSendersAndReceivers();

        // TODO: Transform the HashSet structure to LinkedList for enhanced performance
        HashSet<Sender> stillActiveSenders = new HashSet<>(senders.values());
        HashSet<Sender> copy = new HashSet<>();

        while (!stillActiveSenders.isEmpty()) {
            copy.addAll(stillActiveSenders);
            for (Sender sender : copy) {
                FlexiblePrinter.print("Currently working on sender number #" + sender.getSerialNumber() + ".");
                int preferredReceiverSerialNumber = sender.getNextPreferredReceiver();
                Receiver preferredReceiver = receivers.get(preferredReceiverSerialNumber);

                if (preferredReceiver.acceptsProposalFromSender(sender.getSerialNumber())) {
                    FlexiblePrinter.print("Receiver number #" + preferredReceiver.getSerialNumber() +
                            " has accepted.");
                    sender.connectWithReceiver(preferredReceiverSerialNumber);
                    stillActiveSenders.remove(sender);
                    int rejectedSenderSerialNumber = preferredReceiver.getRejectedSenderSerialNumber();

                    if (!(rejectedSenderSerialNumber == 0)) {
                        FlexiblePrinter.print("In order to do so, he has withdrawn sender number #" +
                                rejectedSenderSerialNumber + ".");
                        stillActiveSenders.add(senders.get(rejectedSenderSerialNumber));
                    } else {
                        FlexiblePrinter.print("No second thoughts at all, he was free!");
                    }

                } else {
                    FlexiblePrinter.print("Receiver number #" + preferredReceiver.getSerialNumber() +
                            " has rejected the proposal.");
                }
                FlexiblePrinter.print("");
            }

            copy.clear();
        }

        System.out.println("\nFinal matching [sender] - [receiver]:\n");
        for (Sender sender : senders.values()) {
            System.out.println(sender);
        }

        System.out.println("\n\n**For detailed printing of the algorithmic steps, set DETAILED_PRINTING " +
                "(inside the FlexiblePrinter.java file) to true.**");
    }

    /**
     * Sets up the two hash maps that map the senders and receivers (respectively) serial number
     * with the actual object.
     */
    private static void setUpSendersAndReceivers() {
        ArrayList<String> headedPreferenceMatrices = FileManager.getHeadedPreferenceMatrices(
                "src/Gale_Shapley_algorithm/Data files/Gale Shapley.txt");

        int serialNumber = 1;
        for (String matrixLine : FileManager.getActualDataLines(headedPreferenceMatrices.get(0))) {
            senders.put(serialNumber, new Sender(serialNumber++, matrixLine));
        }

        serialNumber = 1;
        for (String matrixLine : FileManager.getActualDataLines(headedPreferenceMatrices.get(1))) {
            receivers.put(serialNumber, new Receiver(serialNumber++, matrixLine));
        }
    }
}