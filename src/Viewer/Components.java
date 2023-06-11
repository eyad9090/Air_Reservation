package Viewer;

import javax.swing.*;
import java.util.ArrayList;

public class Components extends JFrame {
    Button buttonHelper;
    Table table;
    Text text;
    TextField textField;
    Sidebar sidebar;
    SelectionBox selectionBox;
    public Components(){
        buttonHelper=new Button();
        table =new Table();
        text=new Text();
        sidebar=new Sidebar();
        textField=new TextField();
        selectionBox=new SelectionBox();
    }
    public JLabel createButton(ButtonAttributes buttonAttributes, Dimensions dimensions)
    {
        return buttonHelper.setButton(buttonAttributes,dimensions);
    }

    public JScrollPane createTable(TableAttributes tableAttributes,Dimensions dimensions)
    {
        return table.setTable(tableAttributes,dimensions);
    }
    public void createText(TextAttributes textAttributes,JLabel text,Dimensions dimensions)
    {
        this.text.setText(textAttributes,text,dimensions);
    }
    public void createText(JPanel sizeSection,TextAttributes textAttributes,JLabel text,Dimensions dimensions)
    {
        this.text.setText(sizeSection,textAttributes,text,dimensions);
    }

    public void createSidebar(JPanel sidebar, ArrayList<JLabel>labels)
    {
        this.sidebar.setSideBar(sidebar,labels);
    }


}
