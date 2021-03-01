package Gale_Shapley_algorithm;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * A simple class that represents a member of the community that sends proposals to
 * the receiving community.
 */
public class Sender {
    private final int serialNumber;
    private HashMap<Integer, Integer> preferences = new HashMap<>();
    private int currentPreferenceIndex = 1;
    private int slotsAvailable = 1;
    private int connectedReceiverSerialNumber = 0;

    /**
     * Constructs a sender with given attributes.
     *
     * @param serialNumber       the unique serial number of the receiver
     * @param encodedPreferences the sequence of preferences as string (one line, separated by space)
     */
    public Sender(int serialNumber, String encodedPreferences) {
        this.serialNumber = serialNumber;
        int preferenceOrderIndex = 1;
        StringTokenizer tokenizer = new StringTokenizer(encodedPreferences, " ");

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            preferences.put(preferenceOrderIndex++, Integer.parseInt(token));
        }
    }

    /**
     * Connects the sender object with a receiver.
     *
     * @param receiverSerialNumber the serial number of the receiver to connect with
     */
    public void connectWithReceiver(int receiverSerialNumber) {
        connectedReceiverSerialNumber = receiverSerialNumber;
    }

    /**
     * Getter for the serial number of the next receiver in the preference list.
     *
     * @return the serial number of the next receiver in the preference list
     */
    public int getNextPreferredReceiver() {
        return preferences.get(currentPreferenceIndex++);
    }

    /**
     * Checks whether there are free slots or not.
     *
     * @return true if there are still free slots, else false
     */
    public boolean hasSlotsAvailable() {
        return !(slotsAvailable == 0);
    }

    /**
     * Executes all necessary actions in case a receiver suddenly declines a formerly
     * accepted proposal. Disconnects the withdrawn receiver and iincreases the available
     * slots.
     *
     * @param receiverSerialNumber the serial number of the receiver that has withdrawn
     *                             a previously accepted proposal
     */
    public void getInformedAboutWithdrawalOfReceiver(int receiverSerialNumber) {
        slotsAvailable++;
        connectedReceiverSerialNumber = 0;
    }

    /**
     * Getter for the serial number of the sender.
     *
     * @return the serial number of the sender
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * Prints the final matching to the console. Must only be called when the matching
     * process has finished.
     *
     * @return a String in the form [senderID] - [connectedReceiverID]
     */
    @Override
    public String toString() {
        return "[" + this.serialNumber + "] - [" + this.connectedReceiverSerialNumber + "]\n";
    }
}
