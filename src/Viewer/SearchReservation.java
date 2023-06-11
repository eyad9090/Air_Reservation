package Viewer;
import Controller.*;
import Model.TirpQuery;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

public class SearchReservation extends JFrame {
    int userId,tripId,tripId2;
    JPanel section;
    Components components;
    JPanel background;
    JPanel sidebar;
    Position position;
    Size size;
    TirpQuery tripQuery = new TirpQuery();
    UserExQuery userexquery =new UserExQuery();
    TripExQuery tripexquery =new TripExQuery();
    ReservationExQuery reservationexquery =new ReservationExQuery();
    String[] choices1 ;
    {
        try {
            choices1 = Arrays.copyOf(
                    tripQuery.selectFrom().toArray(), tripQuery.selectFrom().size(), String[].class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    JComboBox<String> selection;
    JComboBox<String> selection2;
    JLabel selectionText1, selectionText2, dateText;
    JComboBox<String> selectionDate1;
    JComboBox<String> selectionDate2;
    JComboBox<String> selection3;
    JComboBox<String> selection4;
    JLabel selectionText3, selectionText4, dateText2;
    JLabel reserve, update, delete;
    JLabel title;
    JLabel logo, text1, text2, text3, text4, text5;

    JScrollPane scrollTable;
    String []columns={"From","To","Date of arrival","Number of seats"};
    TableAttributes tableAttributes;
    TableFunction tableFunction;

    TextAttributes textAttributes;

    //----------------------------------
    ArrayList<String> seatsName ;
    List<String> selectedSeats;
    ArrayList<JLabel>seats;
    //--------------
    BookingInformation bookingInformation;
    public SearchReservation(int userId) {
        this.userId=userId;
        section=new JPanel();
        textAttributes=new TextAttributes();
        components = new Components();
        background = new JPanel();
        sidebar = new JPanel();
        size = new Size();
        position = new Position();
        selection = new JComboBox<>(choices1);
        selectionText1 = new JLabel("From");
        selection2 = new JComboBox<>();
        selectionDate1=new JComboBox<>();
        selectionText2 = new JLabel("To");
        dateText = new JLabel("Flight date");
        selection3 = new JComboBox<>(choices1);
        selectionText3 = new JLabel("From");
        selection4 = new JComboBox<>();
        selectionDate2=new JComboBox<>();
        selectionText4 = new JLabel("To");
        dateText2 = new JLabel("Flight date");

        reserve = new JLabel();
        update = new JLabel();
        delete = new JLabel();
        title = new JLabel("Flight Reservation");
        text1 = new JLabel("Book reservations");
        text2 = new JLabel("My reservations");
        text3 = new JLabel("Customize 3");
        text4 = new JLabel("Customize 4");
        text5 = new JLabel("logout");
        tableAttributes =new TableAttributes(columns,"insert");
        tableFunction =new TableFunction();

        //---------------------------------
        seatsName=new ArrayList<>(Arrays.asList("A1","A2", "A3","A4"
                ,"A5","A6","A7","A8",
                "B9","B10","B11","B12",
                "B13","B14","B15","B16"
        ));
        selectedSeats=new ArrayList<>();
        seats=new ArrayList<>();

        bookingInformation =new BookingInformation();
        bookingInformation.setUserId(userId);
        showReservation();
    }

    private void showReservation() {

        setComponentsBackground();
        addComponentsBackground();
        section.add(title);
        section.add(selection);
        section.add(selectionText1);
        section.add(selection2);
        section.add(selectionText2);
        section.add(selectionDate1);
        section.add(dateText);
        createSeats();
        this.setVisible(true);
    }

    private void setComponentsBackground() {
        Helper.setOptionpaneStyle();
        setJFrame();
        setBackground();
        setSideBar();
        setSection();
        setSelections();



        setTexts();
        setButtons();
        setActionButton();
        setTable();
    }

    private void setJFrame() {
        this.setTitle("My Reservation");
        this.setSize(size.SizeXPercent(102), size.SizeYPercent(102));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(position.MoveXPercent(0), position.MoveYPercent(0));
        this.setResizable(false);
        this.setLayout(null);
    }
    private void setSection(){
        section.setBackground(Color.decode("#7294a8"));
        section.setBounds(
                position.MoveXPercent(0),
                position.MoveYPercent(0),
                size.SizeXPercent(100),
                size.SizeYPercent(25)
        );
        section.setLayout(null);
    }

    private void setBackground() {
        background.setBackground(Color.decode("#DCE3EE"));
        background.setSize(size.SizeXPercent(102), size.SizeYPercent(102));
        background.setLayout(null);
        this.add(background);
    }


    private void addComponentsBackground() {
        addComponentBackground(title);
        addComponentBackground(selection3);
        addComponentBackground(selectionText3);
        addComponentBackground(selection4);
        addComponentBackground(selectionText4);
        addComponentBackground(selectionDate2);
        addComponentBackground(dateText2);
        addComponentBackground(reserve);
        addComponentBackground(update);
        addComponentBackground(delete);
        addComponentBackground(sidebar);
        addComponentBackground(scrollTable);
        addComponentBackground(section);
    }

    private void addComponentBackground(Object component) {
        if (component instanceof JButton) {
            background.add((JButton) component);
        } else if (component instanceof JPanel) {
            background.add((JPanel) component);
        } else if (component instanceof JTextField) {
            background.add((JTextField) component);
        } else if (component instanceof JLabel) {
            background.add((JLabel) component);
        } else if (component instanceof JComboBox<?>) {
            background.add((JComboBox<?>) component);
        } else if (component instanceof JScrollPane) {
            background.add((JScrollPane) component);
        }
    }


    private void setSelections()
    {
        setSelection(selection,
                new Dimensions(15, 15, 28, 60), "from");
        setSelection(selection2, new Dimensions(15, 15, 48, 60), "to");
        setSelection(selectionDate1, new Dimensions(15, 15, 67, 60), "date");

        setSelection(selection3,
                new Dimensions(10,5,26,75),"from2");
        setSelection(selection4,
                new Dimensions(10,5,38,75),"to2");
        setSelection(selectionDate2,
                new Dimensions(10,5,51,75),"date2");

    }
    public void setTexts()
    {
        components.createText(section,textAttributes, selectionText1,
                new Dimensions(10, 10, 28, 46));
        components.createText(section,textAttributes, selectionText2,
                new Dimensions(10, 10, 48, 46));
        components.createText(section,textAttributes, dateText,
                new Dimensions(10, 10, 67, 46));
        components.createText(section, new TextAttributes(26, Font.PLAIN, "#ffffff"),
                title,
                new Dimensions(20, 20, 58, 8));


        TextAttributes textAttributes1=new TextAttributes(20,Font.PLAIN,"000000");
        components.createText(textAttributes1,selectionText3,
                new Dimensions(10,5,26,70));
        components.createText(textAttributes1,selectionText4,
                new Dimensions(10,5,38,70));
        components.createText(textAttributes1,dateText2,
                new Dimensions(10,5,51,70));
    }
    private void setSideBar()
    {
        ArrayList<JLabel>labels=new ArrayList<>(Arrays.asList(text1,text2,text5));
        components.createSidebar(sidebar,labels);
        setLogo();
        for(int i=0,y=0;i<labels.size();i++,y+=12)
        {
            setSidebarLabel(labels.get(i),y,i+1);
        }
        setActiveSidebar();
    }
    private void setLogo(){
        String path="../image/logo.Png";
        int x=size.SizeXPercent(sidebar.getWidth(),80);
        int y=size.SizeYPercent(sidebar.getHeight(),20);
        logo = new JLabel(Helper.getImage(path,x,y));
        sidebar.add(logo);
        logo.setBounds(
                position.MoveXPercent(sidebar.getWidth(),10),
                position.MoveYPercent(sidebar.getHeight(), 8) ,
                size.SizeXPercent(sidebar.getWidth() , 80 ) ,
                size.SizeYPercent(sidebar.getHeight() , 20)
        );
    }
    private void setActiveSidebar()
    {
        text2.setForeground(Color.decode("#e61919"));
        text2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                text2.setCursor(new Cursor(Cursor.HAND_CURSOR));
                text2.setForeground(Color.decode("#e61919"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                text2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                text2.setForeground(Color.decode("#e61919"));
            }
            public void mouseClicked(MouseEvent e){
            }
        });
    }
    private void setSidebarLabel(JLabel text,int y,int order)
    {
        if(order==3)
            y=50;
        int moveY=29+y;
        Font font=new Font("",Font.BOLD,22);
        text.setBounds(
                position.CenterX(sidebar.getWidth(), size.SizeXPercent(sidebar.getWidth(), 80)),
                position.MoveYPercent(sidebar.getHeight(), moveY),
                size.SizeXPercent(sidebar.getWidth(), 80),
                size.SizeYPercent(sidebar.getHeight(), 8)
        );
        text.setFont(font);
        text.setForeground(Color.decode("#2573B0"));
        text.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                text.setCursor(new Cursor(Cursor.HAND_CURSOR));
                text.setForeground(Color.decode("#e61919"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                text.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                text.setForeground(Color.decode("#2573B0"));
            }
            public void mousePressed(MouseEvent e){
                if(order==1)
                {
                    new Reservation(userId);
                    dispose();
                }
                else if(order==2)
                {
                    new SearchReservation(userId);
                    dispose();
                }
                else if(order==3)
                {
                    new Login();
                    dispose();
                }
                else if(order==4)
                {
                    new Login();
                    dispose();
                }
                else if(order==5)
                {
                    new Login();
                    dispose();
                }
            }
        });
    }

    private void setSelection(JComboBox<?> selection, Dimensions dimensions,String action) {
//        selection.setEditable(false);
        if(action.equals("from2")||action.equals("to2")||action.equals("date2"))
        {
            selection.setEnabled(false);
        }
        if(action.equals("from")||action.equals("to")||action.equals("date"))
        {
            selection.setBackground(Color.white);
            selection.setFont(new Font("dialog", Font.PLAIN, 16));
            selection.setBounds(
                    position.MoveXPercent(section.getWidth(),dimensions.positionX),
                    position.MoveYPercent(section.getHeight(),dimensions.positionY),
                    size.SizeXPercent(section.getWidth(),dimensions.x),
                    size.SizeYPercent(section.getHeight(),dimensions.y)
            );
        }
        else{
            selection.setBackground(Color.white);
            selection.setFont(new Font("dialog", Font.PLAIN, 16));
            selection.setBounds(
                    position.MoveXPercent(dimensions.positionX),
                    position.MoveYPercent(dimensions.positionY),
                    size.SizeXPercent(dimensions.x),
                    size.SizeYPercent(dimensions.y)
            );
        }
        setActionSelection(selection, action);
    }

    private void setTableAction() {
        tableAttributes.searchTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int r =tableAttributes.searchTable.getSelectedRow();
                selection3.setSelectedItem(tableAttributes.searchTable.getValueAt(r, 0));
                selection4.setSelectedItem( tableAttributes.searchTable.getValueAt(r, 1));
                selectionDate2.setSelectedItem(tableAttributes.searchTable.getValueAt(r, 2));
                try {
                    tripId2= tripexquery.getId((String) tableAttributes.searchTable.getValueAt(r, 0),
                            (String)tableAttributes.searchTable.getValueAt(r, 1),
                            (String)tableAttributes.searchTable.getValueAt(r, 2));
                    removeSelectedSeats();
                    ArrayList<String>selectedSeatsF=reservationexquery.myReservationSeats(userId,tripId2);
                    setSelectedSeats(selectedSeatsF);

                    ArrayList<String>selectedSeatsF2=reservationexquery.myNotReservationSeats(userId,tripId2);
                    setOtherSelectedSeats(selectedSeatsF2);
                } catch (SQLException ex) {
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
    }
    private void setActionSelection(JComboBox<?> s, String action) {
        s.addActionListener(event -> {

            String from = (String)selection.getSelectedItem();
            String to   = (String)selection2.getSelectedItem();
            String date = (String)selectionDate1.getSelectedItem();

            String from2 = (String)selection3.getSelectedItem();
            String to2   = (String)selection4.getSelectedItem();
            String date2 = (String)selectionDate2.getSelectedItem();
            if (from != null || to != null) {
                switch (action) {
                    case "from" -> {
                        String[] choices2;
                        try {
                            choices2 = Arrays.copyOf(
                                    tripexquery.GetTo(from).toArray(), tripexquery.GetTo(from).size(), String[].class);
                            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(choices2);
                            selection2.setModel(model);
                            selection2.setSelectedItem(choices2[0]);
                            //----------------------
                            String[] dates;
                            to=choices2[0];
                            dates = Arrays.copyOf(
                                    tripexquery.GetDate(from,to).toArray(), tripexquery.GetDate(from,to).size(), String[].class);
                            model = new DefaultComboBoxModel<>(dates);
                            selectionDate1.setModel(model);
                        } catch (SQLException e) {
                        }

                    }
                    case "to" -> {
                        String[] dates;
                        try {
                            dates = Arrays.copyOf(
                                    tripexquery.GetDate(from,to).toArray(), tripexquery.GetDate(from,to).size(), String[].class);
                            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(dates);
                            selectionDate1.setModel(model);
                            selectionDate1.setSelectedItem(dates[0]);
                        } catch (SQLException e) {
                        }
                    }
                    case "date" -> {
                        try {
                            tripId=tripexquery.getId(from,to,date);
                        } catch (SQLException e) {
                        }
                    }
                    //-----------------------------------------
                    case "from2" -> {
                        String[] choices2;
                        try {
                            choices2 = Arrays.copyOf(
                                    tripexquery.GetTo(from2).toArray(), tripexquery.GetTo(from2).size(), String[].class);
                            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(choices2);
                            selection4.setModel(model);

                            String[] dates;
                            dates = Arrays.copyOf(
                                    tripexquery.GetDate(from2,to2).toArray(), tripexquery.GetDate(from2,to2).size(), String[].class);
                            model = new DefaultComboBoxModel<>(dates);
                            selectionDate2.setModel(model);
                        } catch (SQLException e) {
                        }

                    }
                    case "to2" -> {
                        String[] dates;
                        try {
                            dates = Arrays.copyOf(
                                    tripexquery.GetDate(from2,to2).toArray(), tripexquery.GetDate(from2,to2).size(), String[].class);
                            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(dates);
                            selectionDate2.setModel(model);
                            selectionDate2.setSelectedItem(dates[0]);
                        } catch (SQLException e) {
                        }
                    }
                    case "date2" -> {
                        try {
                            tripId2= tripexquery.getId(from2,to2,date2);
                        } catch (SQLException e) {
                        }
                    }
                }
            }
        });
    }
    private void setTable()
    {
        scrollTable=components.createTable(tableAttributes,
                new Dimensions(75,30,25,40));

        String[] row;
        try {
            row = Arrays.copyOf(
                    reservationexquery.myReservations(userId).toArray(), reservationexquery.myReservations(userId).size(), String[].class);
            for (int i = 0; i < row.length; i+=4) {
                tableFunction.addRow(tableAttributes, new String[]{row[i], row[i + 1], row[i + 2],row[i+3]});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "You don't have trips", "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
        }
        setTableAction();
    }

    private void setButtons()
    {
        reserve = components.createButton(
                new ButtonAttributes("../image/search_before.png",
                        "../image/search_after.png", "search"),
                new Dimensions(size.SizeXPercent(14), size.SizeYPercent(5),
                        50, 28)
        );

        update = components.createButton(
                new ButtonAttributes("../image/update_before.png",
                        "../image/update_after.png", "update"),
                new Dimensions(size.SizeXPercent(8), size.SizeYPercent(5),
                        26, 86)
        );

        delete = components.createButton(
                new ButtonAttributes("../image/delete_before.png",
                        "../image/delete_after.png", "delete"),
                new Dimensions(size.SizeXPercent(8), size.SizeYPercent(5),
                        36, 86)
        );
    }
    private void setActionButton() {
        JLabel message = new JLabel();
        message.setFont(new Font("Arial", Font.BOLD, 16));
        message.setText("No trips found");
        message.setForeground(Color.BLACK);
        reserve.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                try
                {
                    if(selection2.getSelectedItem()!=null)
                    {
                        tableFunction.clearTable(tableAttributes);
                        String[] row;
                        row = Arrays.copyOf(
                                userexquery.myReservation(tripId,userId).toArray(), userexquery.myReservation(tripId,userId).size(), String[].class);
                        for (int i = 0; i < row.length; i+=4) {
                            tableFunction.addRow(tableAttributes, new String[]{row[i], row[i + 1], row[i + 2],row[i+3]});
                        }
                        if(row.length==0)
                        {
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }

                }catch (SQLException exception)
                {
                    message.setText("System error please referesh the page");
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        delete.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JLabel message = new JLabel();
                message.setText("Are you sure you want to delete reservation");
                message.setForeground(Color.decode("#C82333"));
                int choice = JOptionPane.showConfirmDialog(null, message, "ERROR", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    message.setText("Your Reservation deleted successfully");
                    message.setForeground(Color.black);
                    try {
                        if(selection4.getSelectedItem()!=null)
                        {
                            boolean check=reservationexquery.deleteReservation(userId, tripId2,  (String)selectionDate2.getSelectedItem() ,selectedSeats.size());///date and qnt
                            if(check)
                            {
                                String from2 = (String)selection3.getSelectedItem();
                                String to2   = (String)selection4.getSelectedItem();
                                String date2 = (String)selectionDate2.getSelectedItem();
                                for(int i=0;i<tableAttributes.searchModel.getRowCount();i++)
                                {
                                    if (date2 != null && from2 != null && from2.equals(tableAttributes.searchTable.getValueAt(i, 0)) &&
                                            to2.equals(tableAttributes.searchTable.getValueAt(i, 1)) &&
                                            date2.equals(tableAttributes.searchTable.getValueAt(i, 2))) {
                                        tableAttributes.searchModel.removeRow(i);
                                    }
                                }
                                String []clear={};
                                DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(clear);
                                selection4.setModel(model);
                                selectionDate2.setModel(model);
                                removeSelectedSeats();
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                            {
                                message.setText("please make sure you choose the trip from the table");
                                JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        else
                        {
                            message.setText("please make sure you choose the trip from the table");
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        message.setText("System error please referesh the page");
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (choice == JOptionPane.NO_OPTION) {
                    message.setText("Your reservation is safe");
                    message.setForeground(Color.BLACK);
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        update.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                JLabel message = new JLabel();
                message.setFont(new Font("Arial", Font.BOLD, 16));
                message.setText("updated successfully");
                message.setForeground(Color.BLACK);
                try {
                    if(selection4.getSelectedItem()!=null)
                    {
                        bookingInformation.setReservationDate((String)selectionDate2.getSelectedItem());
                        bookingInformation.setTripId(tripId2);
                        bookingInformation.setSeats(selectedSeats.size());
                        bookingInformation.setSeatsName(selectedSeats);

                        ThreadRunner threadRunner=new ThreadRunner(bookingInformation,"update");

                        Future<?> future = ServiceExecutor.service.submit(threadRunner);
                        future.get();

                        if(bookingInformation.isOk())
                        {
                            tableFunction.clearTable(tableAttributes);
                            String[] row;
                            row = Arrays.copyOf(
                                    reservationexquery.myReservations(userId).toArray(), reservationexquery.myReservations(userId).size(), String[].class);
                            for (int i = 0; i < row.length; i+=4) {
                                tableFunction.addRow(tableAttributes, new String[]{row[i], row[i + 1], row[i + 2],row[i+3]});
                            }
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            message.setText("Please enter valid number of seats");
                            JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else
                    {
                        message.setText("Please choose the trip from the table .");
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    message.setText("System error please referesh the page");
                    JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
    }


    //----------------------------------seats in the trip--------------------------------------------

    private void createSeats()
    {
        JLabel label;
        for (String s : seatsName) {
            label = new JLabel(s, SwingConstants.CENTER);
            seats.add(label);
        }
        addSeatstoSection(seats);
    }
    private void addSeatstoSection(ArrayList<JLabel>seats)
    {
        for (JLabel seat : seats) {
            background.add(seat);
        }
        setSeatStyle(seats);
    }
    private void setSeatStyle(ArrayList<JLabel>seats)
    {
        int width=4,height=4,startX=70,startY=71;
        int movingX=0,movingY=0;
        String backgroundColor="#1F6EAF";
        String fontColor="#ffffff";
        for(int i=0,column=1,row=1;i<seats.size();i++,column++)
        {
            seats.get(i).setBounds(
                    position.MoveXPercent(startX+movingX),
                    position.MoveYPercent(startY+movingY),
                    size.SizeXPercent(width),
                    size.SizeYPercent(height)
            );
            movingX+=8;
            if(column==4)
            {
                column=0;
                movingX=0;
                movingY+=5;
                row++;
                if(row==3)
                {
                    movingY+=6;
                }
            }
            //------
            seats.get(i).setForeground(Color.decode(fontColor));
            seats.get(i).setFont(new Font("", Font.BOLD, 18));
            seats.get(i).setOpaque(true);
            seats.get(i).setBackground(Color.decode(backgroundColor));
        }
        setSeatsAction(seats);
    }
    private void setSeatsAction(ArrayList<JLabel>seats)
    {
        for (JLabel seat : seats) {
            setActionSeat(seat);
        }
    }
    private void setActionSeat(JLabel label)
    {
        Color selected=new Color(210,0,0);
        Color notSelected=new Color(31,110,175);

        String fontColorSelected="#ffffff";
        String fontColorNotSelected="#ffffff";
        label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Color currentColor=label.getBackground();
                if(currentColor.getRed()==selected.getRed()&&
                        currentColor.getBlue()==selected.getBlue()&&
                        currentColor.getGreen()==selected.getGreen())
                {
                    label.setBackground(notSelected);
                    label.setForeground(Color.decode(fontColorNotSelected));
                    selectedSeats.remove(label.getText());
                }
                else
                {
                    label.setBackground(selected);
                    label.setForeground(Color.decode(fontColorSelected));
                    selectedSeats.add(label.getText());
                }
            }
        });
    }
    private void setSelectedSeats(ArrayList<String>selectedSeatsF)
    {
        Color selected=new Color(210,0,0);
        String fontColorSelected="#ffffff";
        for(String Seatno:selectedSeatsF)
        {
            for(JLabel label:seats)
            {
                if(Seatno.equals(label.getText()))
                {
                    label.setBackground(selected);
                    label.setForeground(Color.decode(fontColorSelected));
                    selectedSeats.add(label.getText());
                }
            }
        }
    }
    private void setOtherSelectedSeats(ArrayList<String>selectedSeatsF)
    {
        Color selected=new Color(233,234,237);
        String fontColorSelected="#f00000";
        for(String Seatno:selectedSeatsF)
        {
            for(JLabel label:seats)
            {
                if(Seatno.equals(label.getText()))
                {
                    label.setBackground(selected);
                    label.setForeground(Color.decode(fontColorSelected));
                    setActiveSelected(label);
                }
            }
        }
    }
    private void setActiveSelected(JLabel label)
    {
        Color selected=new Color(233,234,237);
        String fontColorSelected="#f00000";
        label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                label.setBackground(selected);
                label.setForeground(Color.decode(fontColorSelected));
            }
        });
    }
    private void removeSelectedSeats()
    {
        Color notSelected=new Color(31,110,175);
        String fontColorNotSelected="#ffffff";
        for (JLabel seat : seats) {
            seat.setForeground(Color.decode(fontColorNotSelected));
            seat.setBackground(notSelected);
            selectedSeats.remove(seat.getText());
        }
    }


}
