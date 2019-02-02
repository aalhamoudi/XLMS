package SystemTray;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class IconReader{

    static ImageReader pngReader;
    static  ImageInputStream in;

    public static Image readIcon(String iconPath)
    {
        try {
            File path = new File(iconPath);


            in = ImageIO.createImageInputStream(path);
            ImageReader pngReader = ImageIO.getImageReadersBySuffix("jpg").next();
            pngReader.getDefaultReadParam();
            pngReader.setInput(in, false);
            return pngReader.read(0);
        }

        catch (IOException e)
        {
            System.out.println("Icon couldn't be read.");
        }
        return null;
    }


}
