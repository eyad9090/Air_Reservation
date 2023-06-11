package Viewer;
import java.awt.*;
public class Position {
    private final int sizeX;
    private final int sizeY;
    Toolkit mySize;
    public Position()
    {
        this.mySize=Toolkit.getDefaultToolkit();
        this.sizeX=mySize.getScreenSize().width;
        this.sizeY=mySize.getScreenSize().height;
    }
    public int MoveXPercent(double percent)
    {
        percent/=100;
        return (int)(sizeX*percent);
    }
    public int MoveXPercent(int size,double percent)
    {
        percent/=100;
        return (int)(size*percent);
    }
    public int MoveYPercent(double percent)
    {
        percent/=100;
        return (int)(sizeY*percent);
    }
    public int MoveYPercent(int size,double percent)
    {
        percent/=100;
        return (int)(size*percent);
    }
    public int CenterX(int width)
    {
        return (sizeX/2)-(width/2);
    }
    public int CenterY(int height)
    {
        return (sizeY/2)-(height/2);
    }

    public int CenterX(int size,int width)
    {
        return (size/2)-(width/2);
    }
}
