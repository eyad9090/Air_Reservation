package Viewer;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Sidebar {
    Position position;
    Size size;
    public Sidebar()
    {
        position=new Position();
        size=new Size();
    }
    public void setSideBar(JPanel sidebar,ArrayList<JLabel> labels)
    {
        sidebar.setBackground(Color.decode("#F7F4F1"));
        sidebar.setSize(size.SizeXPercent(25), size.SizeYPercent(100));
        sidebar.setLayout(null);
        sidebar.setLocation(position.MoveXPercent(0), position.MoveYPercent(0));
        setComponentsSideBar(sidebar,labels);
    }
    private void setComponentsSideBar(JPanel sidebar,ArrayList<JLabel>labels)
    {
        for (JLabel label : labels) {
            addComponentsSidebar(sidebar, label);
        }
    }
    private void addComponentsSidebar(JPanel sidebar,JLabel label)
    {
        addComponentSidebar(sidebar,label);
    }
    private void addComponentSidebar(JPanel sidebar,Object component)
    {
        if(component instanceof JButton)
        {
            sidebar.add((JButton)component);
        }
        else if(component instanceof JLabel)
        {
            sidebar.add((JLabel)component);
        }
    }
}
