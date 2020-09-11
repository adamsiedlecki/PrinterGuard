package pl.adamsiedlecki.PrinterGuard.tools;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamEvent;
import com.github.sarxos.webcam.WebcamStreamer;

import java.util.ArrayList;
import java.util.List;


public class MyWebcamStreamer extends WebcamStreamer {

    private final List<Runnable> runnables = new ArrayList<>();

    public MyWebcamStreamer(int port, Webcam webcam, double fps, boolean start) {
        super(port, webcam, fps, start);
    }

    public void stop() {
        for (Runnable r : runnables) {
            r = null;
        }
        try {
            super.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public Thread newThread(Runnable r) {
        runnables.add(r);
        return super.newThread(r);
    }

    @Override
    public void webcamOpen(WebcamEvent we) {
        super.webcamOpen(we);
    }

    @Override
    public void webcamClosed(WebcamEvent we) {
        super.webcamClosed(we);
    }

    @Override
    public void webcamDisposed(WebcamEvent we) {
        super.webcamDisposed(we);
    }
}

