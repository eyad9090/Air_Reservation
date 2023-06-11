package Viewer;

import java.awt.*;

public class TextAttributes {
    Font font;
    int fontSize=20;
    int fontType= Font.BOLD;
    String color;
    public TextAttributes()
    {
        font=new Font("dialog", fontType, fontSize);
        color="#ffffff";
    }
    public TextAttributes(int fontSize,int fontType,String color)
    {
        this.font=new Font("dialog", fontType, fontSize);
        this.color=color;
    }
}
