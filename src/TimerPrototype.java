import com.google.firebase.*;
import com.google.firebase.database.*;
import com.leapmotion.leap.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

class TimerPrototype {
    public static void main(String[] args) {

        // Create a sample listener and controller
        ConnectionListener listener = new ConnectionListener();
        Controller controller = new Controller();

        // Have the connection listener check for connection
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

class ConnectionListener extends Listener {
    static Probe probe;
    static Timer timer = new Timer();

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        probe = new Probe();
//        while(controller.isConnected()) {
        getFrames(controller);

    }

    public static void getFrames(Controller controller) {
        TimerTask task;
        task = new TimerTask() {
            @Override
            public void run() {
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
        };

        timer.schedule(task, new Date(), 1000);
    }
}