package Viewer;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

import Controller.ReservationExQuery;
import Controller.ServiceExecutor;
import Controller.ThreadRunner;
import Controller.TripExQuery;

public class Reservation extends JFrame {
    private final int userId;
    private int tripId;
    Components components;
    JPanel background;
    JPanel sidebar;
    JPanel section;
    Position position;
    Size size;

    TripExQuery tripexquery =new TripExQuery();
    ReservationExQuery reservationexquery =new ReservationExQuery();
    String[] choices1;
    {
        try {
            choices1 = Arrays.copyOf(
                    tripexquery.GetFrom().toArray(), tripexquery.GetFrom().size(), String[].class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    String[] choices2 = {};
    JComboBox<String> selection;
    JComboBox<String> selection2;
    JComboBox<String> selection3;
    JLabel selectionText1, selectionText2, dateText;
    JLabel reserve;
    JLabel title;
    JLabel logo, text1, text2, text3, text4, text5;

    JScrollPane table;
    TableAttributes tableAttributes;
    TableFunction tableFunction;

    TextAttributes textAttributes;

//    JLabel seatsText;

    //----------------------------------
    ArrayList<String> seatsName ;
    List<String> selectedSeats;
    ArrayList<JLabel>seats;
    HashMap<String, Boolean> visSeats = new HashMap<>();

    ArrayList<MouseAdapter> a;

    //--------------------

    BookingInformation bookingInformation;

    public Reservation(int userId) {
        this.userId=userId;
        section=new JPanel();
        textAttributes = new TextAttributes();
        components = new Components();
        background = new JPanel();
        sidebar = new JPanel();
        size = new Size();
        position = new Position();
        selection = new JComboBox<>(choices1);
        selectionText1 = new JLabel("From");
        selection2 = new JComboBox<>(choices2);
        selectionText2 = new JLabel("To");
        selection3 = new JComboBox<>(choices2);
        dateText = new JLabel("Flight date");
        reserve = new JLabel();
        title = new JLabel("Flight Reservation");
        text1 = new JLabel("Book reservations");
        text2 = new JLabel("My reservations");
        text3 = new JLabel("Customize 3");
        text4 = new JLabel("Customize 4");
        text5 = new JLabel("logout");
        String[] columns = {"From", "To", "Date of arrival","ِAvailable seats"};
        tableAttributes = new TableAttributes(columns, "insert");
        table = new JScrollPane();
        tableFunction = new TableFunction();
//        seatsText=new JLabel("Number of seats");

        //---------------------------------
        seatsName=new ArrayList<>(Arrays.asList("A1","A2", "A3","A4"
                ,"A5","A6","A7","A8",
                "B9","B10","B11","B12",
                "B13","B14","B15","B16"
                ));



        visSeats.put("A1", true);
        visSeats.put("A2", true);
        visSeats.put("A3", true);
        visSeats.put("A4", true);
        visSeats.put("A5", true);
        visSeats.put("A6", true);
        visSeats.put("A7", true);
        visSeats.put("A8", true);

        visSeats.put("B9", true);
        visSeats.put("B10", true);
        visSeats.put("B11", true);
        visSeats.put("B12", true);
        visSeats.put("B13", true);
        visSeats.put("B14", true);
        visSeats.put("B15", true);
        visSeats.put("B16", true);

        a=new ArrayList<>();

        selectedSeats=new ArrayList<>();
        seats=new ArrayList<>();

        //---------------------------
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
        section.add(selection3);
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
        try {
            setTable();
        } catch (SQLException e) {
        }

        Timer timer = new Timer(0, e -> {
            try {
                refereshTable();
            } catch (SQLException ex) {
            }
        });
        timer.setDelay(500); // delay for 30 seconds
        timer.start();

    }

    private void setJFrame() {
        this.setTitle("Reservation");
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
        addComponentBackground(reserve);
        addComponentBackground(sidebar);
        addComponentBackground(table);
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



    private void setButtons() {
        reserve = components.createButton(
                new ButtonAttributes("../image/book_before.png",
                        "../image/book_after.png", "insert"),
                new Dimensions(size.SizeXPercent(8), size.SizeYPercent(5),
                        26, 30)
        );
    }

    private void setTable() throws SQLException {
        table = components.createTable(tableAttributes,
                new Dimensions(75, 40, 25, 40));
        String[] row;
        row = Arrays.copyOf(
                tripexquery.selectAll().toArray(), tripexquery.selectAll().size(), String[].class);
        for (int i = 0; i < row.length; i+=4) {
            tableFunction.addRow(tableAttributes, new String[]{row[i], row[i + 1], row[i + 2],row[i+3]});
        }
    }
    private void refereshTable() throws SQLException
    {
        tableFunction.clearTable(tableAttributes);
        String[] row;
        row = Arrays.copyOf(
                tripexquery.selectAll().toArray(), tripexquery.selectAll().size(), String[].class);
        for (int i = 0; i < row.length; i+=4) {
            tableFunction.addRow(tableAttributes, new String[]{row[i], row[i + 1], row[i + 2],row[i+3]});
        }
    }

    public void setTexts() {
        components.createText(section,textAttributes, selectionText1,
                new Dimensions(10, 10, 28, 36));
        components.createText(section,textAttributes, selectionText2,
                new Dimensions(10, 10, 40, 36));
        components.createText(section,textAttributes, dateText,
                new Dimensions(10, 10, 52, 36));

        components.createText(section, new TextAttributes(26, Font.PLAIN, "#ffffff"),
                title,
                new Dimensions(20, 20, 35, 8));

    }
    private void setSelections() {
        setSelection(selection,
                new Dimensions(10, 15, 28, 50), "from");
        setSelection(selection2, new Dimensions(10, 15, 40, 50), "to");
        setSelection(selection3, new Dimensions(10, 15, 52, 50), "date");
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
        text1.setForeground(Color.decode("#e61919"));
        text1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                text1.setCursor(new Cursor(Cursor.HAND_CURSOR));
                text1.setForeground(Color.decode("#e61919"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                text1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                text1.setForeground(Color.decode("#e61919"));
            }
            public void mouseClicked(MouseEvent e){
            }
        });
    }
    private void setSidebarLabel(JLabel text, int y, int order) {
        if(order==3)
            y=50;
        int moveY = 29 + y;
        Font font = new Font("", Font.BOLD, 22);
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

            public void mousePressed(MouseEvent e) {
                if (order == 1) {
                    new Reservation(userId);
                    dispose();
                } else if (order == 2) {
                    new SearchReservation(userId);
                    dispose();
                } else if (order == 3) {
                    new Login();
                    dispose();
                }
            }
        });
    }

    private void setSelection(JComboBox<?> selection, Dimensions dimensions, String action) {
        selection.setBackground(Color.white);
        selection.setFont(new Font("dialog", Font.PLAIN, 16));
        selection.setBounds(
                position.MoveXPercent(section.getWidth(),dimensions.positionX),
                position.MoveYPercent(section.getHeight(),dimensions.positionY),
                size.SizeXPercent(section.getWidth(),dimensions.x),
                size.SizeYPercent(section.getHeight(),dimensions.y)
        );
        setActionSelection(selection, action);
    }

    private void setActionSelection(JComboBox<?> s, String action) {
        s.addActionListener(event -> {

            String From = (String)selection.getSelectedItem();
            String To = (String)selection2.getSelectedItem();
            String Date = (String)selection3.getSelectedItem();
            removeSelectedSeats();
            if (From != null || To != null) {
                switch (action) {
                    case "from" -> {
                        String[] ToList;
                        try {
                            ToList = Arrays.copyOf(
                                    tripexquery.GetTo(From).toArray(), tripexquery.GetTo(From).size(), String[].class);
                            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(ToList);
                            String firstTo=ToList[0];
                            selection2.setModel(model);
                            selection2.setSelectedItem(firstTo);
                            //----------------------
                            String[] dates;
                            dates = Arrays.copyOf(
                                    tripexquery.GetDate(From,firstTo).toArray(), tripexquery.GetDate(From,firstTo).size(), String[].class);
                            model = new DefaultComboBoxModel<>(dates);
                            selection3.setModel(model);
                            selection3.setSelectedItem(dates[0]);
                        } catch (SQLException e) {
                        }

                    }
                    case "to" -> {
                        String[] dates;
                        try {
                            dates = Arrays.copyOf(
                                    tripexquery.GetDate(From,To).toArray(), tripexquery.GetDate(From,To).size(), String[].class);
                            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(dates);
                            selection3.setModel(model);
                            selection3.setSelectedItem(dates[0]);

                        } catch (SQLException e) {
                        }
                    }
                    case "date" -> {
                        try {
                            tripId=tripexquery.getId(From,To,Date);
                            ArrayList<String>selectedSeatsF=reservationexquery.ReservedSeats(tripId);
                            setSelectedSeats(selectedSeatsF);
                            //--------------------------------
                            bookingInformation.setTripId(tripId);
                            bookingInformation.setReservationDate(Date);

                        } catch (SQLException e) {
                        }
                    }
                }
            }
        });
    }

    private void setActionButton() {
        reserve.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Object selected3 = selection3.getSelectedItem();
                JLabel message = new JLabel();
                message.setFont(new Font("Arial", Font.BOLD, 16));
                message.setForeground(Color.BLACK);
                try {
                    if(selectedSeats.size()!=0&&selected3!=null)
                    {

                        bookingInformation.setSeats(selectedSeats.size());
                        bookingInformation.setSeatsName(selectedSeats);
                        ThreadRunner threadRunner=new ThreadRunner(bookingInformation,"insert");

                        Future<?> future = ServiceExecutor.service.submit(threadRunner);
                        future.get();

                        JOptionPane.showMessageDialog(null, bookingInformation.getMessage(), "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                        ArrayList<String> selectedSeatsF=new ArrayList<>(selectedSeats);
                        if(bookingInformation.getMessage().equals("Booked Successfully"))
                        {
                            setSelectedSeats(selectedSeatsF);
                        }
                        tableFunction.clearTable(tableAttributes);
                        String[] row;
                        row = Arrays.copyOf(
                                tripexquery.selectAll().toArray(), tripexquery.selectAll().size(), String[].class);
                        for (int i = 0; i < row.length; i+=4) {
                            tableFunction.addRow(tableAttributes, new String[]{row[i], row[i + 1], row[i + 2],row[i+3]});
                        }
                    }
                    else
                    {
                        message.setText("Please enter valid number seats and make sure you have choosed all choices (From,to,date)");
                        JOptionPane.showMessageDialog(null, message, "CONFIRMED", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    message.setText("This trip already exist you can update or delete the trip from your reservation section");
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
            section.add(seat);
        }
        setSeatStyle(seats);
    }
    private void setSeatStyle(ArrayList<JLabel>seats)
    {
        int width=4,height=14,startX=68,startY=6;
        int movingX=0,movingY=0;
        String backgroundColor="#1F6EAF";
        String fontColor="#ffffff";
        for(int i=0,column=1,row=1;i<seats.size();i++,column++)
        {
            seats.get(i).setBounds(
                    position.MoveXPercent(section.getWidth(),startX+movingX),
                    position.MoveYPercent(section.getHeight(),startY+movingY),
                    size.SizeXPercent(section.getWidth(),width),
                    size.SizeYPercent(section.getHeight(),height)
            );
            movingX+=8;
            if(column==4)
            {
                column=0;
                movingX=0;
                movingY+=18;
                row++;
                if(row==3)
                {
                    movingY+=21;
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

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Color currentColor=label.getBackground();

                Color selected=new Color(210,0,0);
                Color notSelected=new Color(31,110,175);

                String fontColorSelected="#ffffff";
                String fontColorNotSelected="#ffffff";


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
        };
        label.addMouseListener(adapter);
        a.add(adapter);
    }
    private void setSelectedSeats(ArrayList<String>selectedSeatsF)
    {
        Color selected=new Color(233,234,237);
        String fontColorSelected="#f00000";
        for(String Seatno:selectedSeatsF)
        {
            for(int i=0;i<seats.size();i++)
            {
                JLabel label=seats.get(i);
                if(Seatno.equals(label.getText()))
                {
                    label.setBackground(selected);
                    label.setForeground(Color.decode(fontColorSelected));
                    setActiveSelected(label,i);
                }
            }
        }
    }
    private void setActiveSelected(JLabel label,int i)
    {
        Color selected=new Color(233,234,237);
        String fontColorSelected="#f00000";
        label.removeMouseListener(a.get(i));
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                label.setBackground(selected);
                label.setForeground(Color.decode(fontColorSelected));
            }
        };
        label.addMouseListener(adapter);
        a.set(i,adapter);
    }

    private void resetActiveSelection(JLabel label,int i)
    {
        label.removeMouseListener(a.get(i));
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Color currentColor=label.getBackground();

                Color selected=new Color(210,0,0);
                Color notSelected=new Color(31,110,175);

                String fontColorSelected="#ffffff";
                String fontColorNotSelected="#ffffff";


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
        };
        label.addMouseListener(adapter);
        a.set(i,adapter);
    }
    private void removeSelectedSeats()
    {
        Color notSelected=new Color(31,110,175);
        String fontColorNotSelected="#ffffff";
        for(int i=0;i<seats.size();i++)
        {
            JLabel label=seats.get(i);
            resetActiveSelection(label,i);
            label.setForeground(Color.decode(fontColorNotSelected));
            label.setBackground(notSelected);
            selectedSeats.remove(label.getText());
        }
    }
}

