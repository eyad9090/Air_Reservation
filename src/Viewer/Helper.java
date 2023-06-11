package Viewer;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public final class Helper {
    static ImageIcon image;
    static Image imageSize;

    private Helper(){

    }
    public static ImageIcon getImage(String path, int x, int y) {
        image = new ImageIcon(Objects.requireNonNull(Helper.class.getResource(path)));
        imageSize = image.getImage();
        imageSize = imageSize.getScaledInstance(
                x,
                y,
                Image.SCALE_SMOOTH
        );
        image = new ImageIcon(imageSize);
        return image;
    }
    public static void setOptionpaneStyle() {
        UIManager.put("OptionPane.background", Color.white);
        UIManager.put("Panel.background", Color.white);
    }


}
