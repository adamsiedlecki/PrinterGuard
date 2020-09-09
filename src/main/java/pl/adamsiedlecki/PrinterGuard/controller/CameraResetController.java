package pl.adamsiedlecki.PrinterGuard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.adamsiedlecki.PrinterGuard.hardware.Camera;

@RestController()
@RequestMapping("api/camera")
public class CameraResetController {

    private final Camera camera;

    @Autowired
    public CameraResetController(Camera camera) {
        this.camera = camera;
    }

    @GetMapping(value = "/restart")
    public String restartCamera() {
        System.out.println("RESTARTING CAMERA....");
        camera.restart();
        return "CAMERA RESTARTED";
    }
}
