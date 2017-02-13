//import com.google.firebase.*;
//import com.google.firebase.database.*;
//import com.leapmotion.leap.*;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.HashMap;
//
//public class LeapPrototype {
//    public static void main(String args[]) {
//        FirebaseOptions options;
//        try {
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Kathleen Francis\\OneDrive\\Documents\\LeapPrototype\\breaking-vad-online-simulation-firebase-adminsdk-pn399-0fa8b48b27.json");
//            options = new FirebaseOptions.Builder()
//                    .setServiceAccount(fileInputStream)
//                    .setDatabaseUrl("https://breaking-vad-online-simulation.firebaseio.com")
//                    .build();
//
//            FirebaseApp.initializeApp(options);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        final FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference ref = database.getReference("yWzgI0q39TRtvJUFhaapLFOfJAJ3/ProbeData");
//
//        FrameListener listener = new FrameListener();
//        Controller controller = new Controller();
//
//        // Have the listener receive events from the controller
//        controller.addListener(listener);
//
//        // Remove the listener when done
//        controller.removeListener(listener);
//    }
//}
//
//class FrameListener extends Listener {
//    public void onFrame(Controller controller) {
//        Frame frame = controller.frame(); //The latest frame
//        InteractionBox ibox = frame.interactionBox();
//        Hand hand = frame.hands().rightmost();
//        Vector position = hand.palmPosition();
//
//        Probe probe = new Probe();
//        System.out.println(probe.getProbeCoords());
//        ref.setValue(probe.getProbeCoords());
//    }
//}
//
//class Probe {
//    private HashMap<String, Double> probeCoords = new HashMap<>();
//
//    Probe(double xVal, double yVal, double zVal, double xAngle, double yAngle, double zAngle) {
//        probeCoords.put("xVal", xVal);
//        probeCoords.put("yVal", yVal);
//        probeCoords.put("zVal", zVal);
//        probeCoords.put("xAngle", xAngle);
//        probeCoords.put("yAngle", yAngle);
//        probeCoords.put("zAngle", zAngle);
//    }
//
//    HashMap<String, Double> getProbeCoords() {
//        return this.probeCoords;
//    }
//
//}
//
