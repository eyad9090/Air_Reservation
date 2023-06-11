package Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JFrame {
    Position position ;
    Size size;
    public Button(){

        position=new Position();
        size=new Size();
    }
    public JLabel setButton(ButtonAttributes attributesButton, Dimensions dimensions) {
        attributesButton.button = new JLabel(Helper.getImage(attributesButton.before, dimensions.x, dimensions.y));
        attributesButton.button.setBounds(
                position.MoveXPercent(dimensions.positionX),
                position.MoveYPercent(dimensions.positionY),
                dimensions.x,
                dimensions.y
        );
        attributesButton.button = setActionButton(attributesButton, dimensions);
        return attributesButton.button;
    }
    public JLabel setButton(JPanel panel,ButtonAttributes attributesButton,
                            Dimensions dimensions,int order) {
        attributesButton.button = new JLabel(Helper.getImage(attributesButton.before, dimensions.x, dimensions.y));
        attributesButton.button.setBounds(
                position.MoveXPercent(panel.getWidth(),dimensions.positionX),
                position.MoveYPercent(panel.getHeight(),dimensions.positionY),
                size.SizeXPercent(panel.getWidth(),dimensions.x),
                size.SizeYPercent(panel.getHeight(),dimensions.x)
        );
        attributesButton.button = setActionButton(attributesButton, dimensions,order);
        return attributesButton.button;
    }
    private JLabel setActionButton(ButtonAttributes attributesButton,
                                   Dimensions dimensions) {
        attributesButton.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                attributesButton.button.setIcon(Helper.getImage(attributesButton.after, dimensions.x, dimensions.y));
                attributesButton.button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                attributesButton.button.setIcon(Helper.getImage(attributesButton.before, dimensions.x, dimensions.y));
                attributesButton.button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        return attributesButton.button;
    }
    private JLabel setActionButton(ButtonAttributes attributesButton,
                                   Dimensions dimensions,int order) {
        attributesButton.button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                attributesButton.button.setIcon(Helper.getImage(attributesButton.after, dimensions.x, dimensions.y));
                attributesButton.button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                attributesButton.button.setIcon(Helper.getImage(attributesButton.before, dimensions.x, dimensions.y));
                attributesButton.button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        });
        return attributesButton.button;
    }
}
