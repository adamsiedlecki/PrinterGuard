package pl.adamsiedlecki.PrinterGuard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import pl.adamsiedlecki.PrinterGuard.hardware.Camera;

@SpringBootApplication
public class PrinterGuardApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PrinterGuardApplication.class, args);

		Environment env = ctx.getEnvironment();

		Camera camera = ctx.getBean(Camera.class);
		camera.start();
//		camera.stop();
//		camera.restart();

	}

}
