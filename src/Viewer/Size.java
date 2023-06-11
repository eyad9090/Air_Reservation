package Viewer;
import java.awt.*;
public class Size {
    private final   int sizeX;
    private final int sizeY;
    Toolkit mySize;
    public Size()
    {
        this.mySize=Toolkit.getDefaultToolkit();
        this.sizeX=mySize.getScreenSize().width;
        this.sizeY=mySize.getScreenSize().height;
    }
    public int SizeXPercent(double percent)
    {
        percent/=100;
        return (int)(sizeX*percent);
    }
    public int SizeXPercent(int size,double percent)
    {
        percent/=100;
        return (int)(size*percent);
    }
    public int SizeYPercent(double percent)
    {
        percent/=100;
        return (int)(sizeY*percent);
    }
    public int SizeYPercent(int size,double percent)
    {
        percent/=100;
        return (int)(size*percent);
    }
}
