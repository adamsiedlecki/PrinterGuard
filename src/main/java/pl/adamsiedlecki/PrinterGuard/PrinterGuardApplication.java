package pl.adamsiedlecki.PrinterGuard;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamStreamer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.awt.*;

@SpringBootApplication
public class PrinterGuardApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PrinterGuardApplication.class, args);

		Environment env = ctx.getEnvironment();

		Webcam w = Webcam.getDefault();
		w.setViewSize(new Dimension(Integer.parseInt(env.getProperty("camera.width", "480")),
				Integer.parseInt(env.getProperty("camera.height", "400")))); // max is 1024,768

		int camPort = Integer.parseInt(env.getProperty("camera.port","8080"));

		new Thread(()->{
			WebcamStreamer webcamStreamer = new WebcamStreamer(8080, w, 25, false);
			webcamStreamer.start();
		}).start();


	}

}
