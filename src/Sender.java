import java.util.HashMap;
import java.util.StringTokenizer;

public class Sender {
    private HashMap<Integer, Integer> preferences = new HashMap<>();
    private int currentPreferenceIndex = 1;
    private int slotsAvailable = 1;
    private int connectedReceiverSerialNumber = 0;

    public Sender(String encodedPreferences) {
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


    @Override
    public String toString() {
        StringBuilder stringToReturn = new StringBuilder("[sender]\n");
        for (int preference : preferences.keySet()) {
            stringToReturn.append("Preference #");
            stringToReturn.append(preference);
            stringToReturn.append(": ");
            stringToReturn.append(preferences.get(preference));
            stringToReturn.append("\n");
        }
        return stringToReturn.toString();
    }
}
