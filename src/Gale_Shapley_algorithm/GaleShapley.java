package Gale_Shapley_algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GaleShapley {

    private static HashMap<Integer, Sender> senders = new HashMap<>();
    private static HashMap<Integer, Receiver> receivers = new HashMap<>();

    public static void main(String[] args) {
        setUpSendersAndReceivers();

        HashSet<Sender> stillAlive = new HashSet<>(senders.values());
        HashSet<Sender> copy = new HashSet<>();

        while (!stillAlive.isEmpty()) {
            copy.addAll(stillAlive);
            for (Sender sender : copy) {
                FlexiblePrinter.print("Currently working on sender number #" + sender.getSerialNumber());
                int preferredReceiverSerialNumber = sender.getNextPreferredReceiver();
                Receiver preferredReceiver = receivers.get(preferredReceiverSerialNumber);

                if (preferredReceiver.acceptsProposalFromSender(sender.getSerialNumber())) {
                    FlexiblePrinter.print("Gale_Shapley_algorithm.Receiver number #" + preferredReceiver.getSerialNumber() + " has accepted");
                    sender.connectWithReceiver(preferredReceiverSerialNumber);
                    stillAlive.remove(sender);
                    int rejectedSenderSerialNumber = preferredReceiver.getRejectedSenderSerialNumber();
                    if (!(rejectedSenderSerialNumber == 0)) {
                        FlexiblePrinter.print("In order to do so, he has withdrawn sender number #" + rejectedSenderSerialNumber);
                        stillAlive.add(senders.get(rejectedSenderSerialNumber));
                    } else {
                        FlexiblePrinter.print("No second thought at all, he was free!");
                    }
                } else {
                    FlexiblePrinter.print("Gale_Shapley_algorithm.Receiver number #" + preferredReceiver.getSerialNumber() + " has rejected the proposal");
                }
                FlexiblePrinter.print("");
            }
            copy.clear();
        }

        System.out.println("Final matching [sender] - [receiver]");
        for (Sender sender : senders.values()) {
            System.out.println(sender);
        }
    }

    private static void setUpSendersAndReceivers() {
        ArrayList<String> headedPreferenceMatrices = FileManager.getHeadedPreferenceMatrices("Gale Shapley.txt");

        int index = 1;
        for (String matrixLine : FileManager.getActualDataLines(headedPreferenceMatrices.get(0))) {
            senders.put(index, new Sender(index++, matrixLine));
        }

        index = 1;
        for (String matrixLine : FileManager.getActualDataLines(headedPreferenceMatrices.get(1))) {
            receivers.put(index, new Receiver(index++, matrixLine));
        }
    }
}