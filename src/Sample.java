import com.google.firebase.*;
import com.google.firebase.database.*;
import com.leapmotion.leap.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

class Sample {
    public static void main(String[] args) {

        // Create a sample listener and controller
        SampleListener listener = new SampleListener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);
    }
}

class SampleListener extends Listener {
    static Probe probe;

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        probe = new Probe();
    }

    public void onFrame(Controller controller) {
        Frame frame = controller.frame();

        probe.analyzeFrame(frame);

        InteractionBox ibox = frame.interactionBox();
        Hand hand = frame.hands().rightmost();
        Vector position = hand.palmPosition();
        Vector direction = hand.direction();


        System.out.println("Frame id: " + frame.id()
                + ", timestamp: " + frame.timestamp()
                + ", hands: " + frame.hands().count()
                + ", hand position: " + frame.hands().rightmost().palmPosition()
                + ", interaction box: " + ibox.toString());

    }
}