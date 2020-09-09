package pl.adamsiedlecki.PrinterGuard.hardware;

import com.github.sarxos.webcam.Webcam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.adamsiedlecki.PrinterGuard.MyWebcamStreamer;

import java.awt.*;

@Component
public class Camera {

    private final Environment env;
    private MyWebcamStreamer webcamStreamer;
    private Webcam webcam;
    private boolean firstStart = true;

    @Autowired
    public Camera(Environment env) {
        this.env = env;
    }

    public void start() {
        webcam = Webcam.getDefault();

        webcam.setViewSize(new Dimension(Integer.parseInt(env.getProperty("camera.width", "480")),
                Integer.parseInt(env.getProperty("camera.height", "400")))); // max is 1024,768

        webcam.open();

//        if(firstStart){
        int camPort = Integer.parseInt(env.getProperty("camera.port", "8080"));
        webcamStreamer = new MyWebcamStreamer(camPort, webcam, 25, false);
        webcamStreamer.start();
        firstStart = false;
        //}

    }

    public void stop() {
        if (webcam != null) {
            webcam.close();
        }
        if (webcamStreamer != null) {
            webcamStreamer.stop();
            webcamStreamer = null; // I want GarbageCollector to get rid of it; I have only hope
        }

    }

    public void restart() {
        stop();
        start();
    }
}
