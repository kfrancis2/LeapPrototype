import com.google.firebase.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leapmotion.leap.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.HashMap;

public class Probe {
    private int region;
    private DatabaseReference ref;

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
//            if (frame.hands().count() > 1) {
//                JOptionPane.showMessageDialog(null, "Please remove extra hands from frame.");
//            }
            Hand leapHand = frame.hands().frontmost();
            Vector position = leapHand.palmPosition();
            Vector angle = leapHand.palmNormal();

            double x = position.get(0);
            double y = position.get(1);
            double z = position.get(2);
            double xAng = angle.get(0);
            double yAng = angle.get(1);
            double zAng = angle.get(2);

            int newRegion;
            if (z > 40) {
                if (x > 12 && x < 52 && y > 184 && y < 224) {
                    if(xAng > .35 && xAng < .55 && yAng > .32 && yAng < .52 && zAng > .68 && zAng < .88) {
                        newRegion = 1;
                    } else {
                        newRegion = 7;
                    }
                } else if (x > -86 && x < -46 && y > 320 && y < 400) {
                    if(xAng > -.04 && xAng < .16 && yAng > .43 && yAng < .63 && zAng > .76 && zAng < .96) {
                        newRegion = 3;
                    } else {
                        newRegion = 8;
                    }
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
