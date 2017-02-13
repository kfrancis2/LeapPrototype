import com.google.firebase.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leapmotion.leap.*;

import java.io.FileInputStream;
import java.util.HashMap;

public class Probe {
    int region;
    DatabaseReference ref;

    public Probe() {
        region = 6;
        FirebaseOptions options;
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Kathleen Francis\\OneDrive\\Documents\\LeapPrototype\\breaking-vad-online-simulation-firebase-adminsdk-pn399-0fa8b48b27.json");
            options = new FirebaseOptions.Builder()
                    .setServiceAccount(fileInputStream)
                    .setDatabaseUrl("https://breaking-vad-online-simulation.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch(Exception e) {
            e.printStackTrace();
        }

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("yWzgI0q39TRtvJUFhaapLFOfJAJ3/ProbeData");

    }

    public void analyzeFrame(Frame frame) {
        if (frame.hands().count() > 0) {
            Vector position = frame.hands().rightmost().palmPosition();
            double x = position.get(0);
            double y = position.get(1);
            double z = position.get(2);

            int newRegion;
            if (z > 40) {
                if (x > 12 && x < 52 && y > 184 && y < 224) {
                    newRegion = 1;
                } else if (x > -86 && x < -46 && y > 320 && y < 400) {
                    newRegion = 3;
                } else {
                    newRegion = 5;
                }
            } else {
                newRegion = 6;
            }
            System.out.println(newRegion);
            sendToFirebase(newRegion);
        }
    }

    public void sendToFirebase(int newRegion) {
        if (newRegion != region) {
            HashMap<String, Integer> regionMap = new HashMap<>();
            regionMap.put("region", newRegion);
            ref.setValue(regionMap);
            region = newRegion;
        }
    }
}
