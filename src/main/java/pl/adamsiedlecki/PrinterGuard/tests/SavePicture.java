package pl.adamsiedlecki.PrinterGuard.tests;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SavePicture {

    public File fromInputStream(InputStream inputStream){
        InputStream is = new BufferedInputStream(inputStream);
        File outputFile = new File("savedFromInputStream.jpg");
        try {
            BufferedImage read = ImageIO.read(is);
            ImageIO.write(read, "jpg",outputFile );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;
    }
}
