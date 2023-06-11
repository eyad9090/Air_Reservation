package Viewer;

import javax.swing.*;
import java.awt.*;

public class Text {
    Position position;
    Size size;

    public Text() {
        position=new Position();
        size=new Size();
    }
    public void setText(TextAttributes textAttributes,JLabel text,Dimensions dimensions) {
        text.setFont(textAttributes.font);
        text.setForeground(Color.decode(textAttributes.color));
        text.setBounds(
                position.MoveXPercent(dimensions.positionX),
                position.MoveYPercent(dimensions.positionY),
                size.SizeXPercent(dimensions.x),
                size.SizeYPercent(dimensions.y)
        );
    }
    public void setText(JPanel sizeSection,
                        TextAttributes textAttributes,JLabel text,Dimensions dimensions) {
        text.setFont(textAttributes.font);
        text.setForeground(Color.decode(textAttributes.color));
        text.setBounds(
                position.MoveXPercent(sizeSection.getWidth(),dimensions.positionX),
                position.MoveYPercent(sizeSection.getHeight(),dimensions.positionY),
                size.SizeXPercent(sizeSection.getWidth(),dimensions.x),
                size.SizeYPercent(sizeSection.getHeight(),dimensions.y)
        );
    }
}
