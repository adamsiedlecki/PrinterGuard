package pl.adamsiedlecki.PrinterGuard.tests;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MakePhoto {

    private int photoNumber;

    public File make(){
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        BufferedImage image = webcam.getImage();
        File f = new File("frames/img"+ photoNumber +".jpg");

        try {
            ImageIO.write(image, "jpg", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        webcam.close();
        photoNumber++;
        return f;
    }
}
