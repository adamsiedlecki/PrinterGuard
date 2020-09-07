package pl.adamsiedlecki.PrinterGuard.tests;

import com.github.sarxos.webcam.Webcam;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Embedded;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MakePhotos extends Thread {

    private int photoNumber = 0;
    private String lastFilePath = "";
    private Embedded pictureBox;

    public MakePhotos(Embedded pictureBox) {
        this.pictureBox = pictureBox;
    }

    public void run(){
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        while(true){
            BufferedImage image = webcam.getImage();
            File f = new File("frames/img"+ photoNumber +".jpg");
            lastFilePath = f.getAbsolutePath();
            try {
                ImageIO.write(image, "jpg", f);
            } catch (IOException e) {
                e.printStackTrace();
            }

            photoNumber++;
            System.out.println("photoNumber: "+ photoNumber);
            pictureBox.setSource(new FileResource(f));
        }
    }

    public int getPhotoNumber() {
        return photoNumber;
    }

}
