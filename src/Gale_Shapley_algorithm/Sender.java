package Gale_Shapley_algorithm;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Sender {
    private final int serialNumber;
    private HashMap<Integer, Integer> preferences = new HashMap<>();
    private int currentPreferenceIndex = 1;
    private int slotsAvailable = 1;
    private int connectedReceiverSerialNumber = 0;

    public Sender(int serialNumber, String encodedPreferences) {
        this.serialNumber = serialNumber;
        int preferenceOrderIndex = 1;
        StringTokenizer tokenizer = new StringTokenizer(encodedPreferences, " ");

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            preferences.put(preferenceOrderIndex++, Integer.parseInt(token));
        }
    }

    public void connectWithReceiver(int receiverSerialNumber) {
        connectedReceiverSerialNumber = receiverSerialNumber;
    }

    public int getNextPreferredReceiver() {
        return preferences.get(currentPreferenceIndex++);
    }

    public boolean hasSlotsAvailable() {
        return !(slotsAvailable == 0);
    }

    public void getInformedAboutWithdrawalOfReceiver(int receiverSerialNumber) {
        slotsAvailable++;
        connectedReceiverSerialNumber = 0;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        return "[" + this.serialNumber + "] - [" + this.connectedReceiverSerialNumber + "]\n";
    }
}
