package Gale_Shapley_algorithm;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * A simple class that represents a member of the community that receives proposals and
 * accepts or decline them. Each member of the receiving community can be connected to
 * just one sender, by definition of the real-life situations.
 */
public class Receiver {
    private int serialNumber;
    private HashMap<Integer, Integer> preferences = new HashMap<>();
    private boolean isFree = true;
    private int connectedSenderSerialNumber = 0;
    private int rejectedSenderSerialNumber = 0;

    /**
     * Constructs a receiver object with given attributes.
     *
     * @param serialNumber       the unique serial number of the receiver
     * @param encodedPreferences the sequence of preferences as string (one line, separated by space)
     */
    public Receiver(int serialNumber, String encodedPreferences) {
        this.serialNumber = serialNumber;

        int preferenceOrderIndex = 1;
        StringTokenizer tokenizer = new StringTokenizer(encodedPreferences, " ");

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            preferences.put(Integer.parseInt(token), preferenceOrderIndex++);
        }
    }

    /**
     * Getter for the serial number attribute.
     *
     * @return the unique serial number of the receiver
     */
    public int getSerialNumber() {
        return serialNumber;
    }

    /**
     * Examines a given proposal from a specific sender. Based on the current state of the receiver
     * (free or not) as well as his preferences, decides on whether the receiver accepts the proposal
     * or not.
     * Important: In order to accept the proposal, the receiver may need to withdraw an alreade accepted
     * one. In such a case, the rejectedSenderSerialNumber field is updated for an one-time-only read.
     * That's why getRejectedSenderSerialNumber() must be always called and checked to 0 right after
     * calling the current method.
     *
     * @param senderSerialNumber the serial number of the sender of the proposal
     * @return true if the receiver accepts the proposal (even if a withdrawal occurs), else false
     */
    public boolean acceptsProposalFromSender(int senderSerialNumber) {
        if (isFree) {
            connectedSenderSerialNumber = senderSerialNumber;
            isFree = false;
            return true;
        }

        if (preferences.get(connectedSenderSerialNumber) > preferences.get(senderSerialNumber)) {
            rejectedSenderSerialNumber = connectedSenderSerialNumber;
            connectedSenderSerialNumber = senderSerialNumber;
            return true;
        }

        return false;
    }

    /**
     * One-time-only getter for the rejected sender, if exists. The method must
     * always be called right after calling acceptsProposalFromSender method.
     *
     * @return the serial number of the rejected sender (if exists), else 0
     */
    public int getRejectedSenderSerialNumber() {
        int defensiveCopy = rejectedSenderSerialNumber;
        rejectedSenderSerialNumber = 0;
        return defensiveCopy;
    }
}