package Gale_Shapley_algorithm;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Receiver {
    private int serialNumber;
    private HashMap<Integer, Integer> preferences = new HashMap<>();
    private boolean isFree = true;
    private int connectedSenderSerialNumber = 0;
    private int rejectedSenderSerialNumber = 0;

    public Receiver(int serialNumber, String encodedPreferences) {
        this.serialNumber = serialNumber;

        int preferenceOrderIndex = 1;
        StringTokenizer tokenizer = new StringTokenizer(encodedPreferences, " ");

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            preferences.put(Integer.parseInt(token), preferenceOrderIndex++);
        }
    }

    public int getSerialNumber() {
        return serialNumber;
    }

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

    public int getRejectedSenderSerialNumber() {
        int defensiveCopy = rejectedSenderSerialNumber;
        rejectedSenderSerialNumber = 0;
        return defensiveCopy;
    }
}