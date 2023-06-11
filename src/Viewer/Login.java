package Viewer;


import Controller.UserExQuery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Objects;

public class Login extends JFrame {
    JPanel background, login;
    JLabel imageBackground;

    Position position;
    Size size;
    JLabel logo;
    ImageIcon image ;
    Image  imageSize;
    JLabel loginTitle, loginButton;
    JTextField name;
    JLabel nameText;
    JPasswordField pass;
    JLabel passText;
    JLabel registerPageLink;
    UserExQuery userexquery=new UserExQuery();

    public Login() {
        background = new JPanel();


        size = new Size();
        position = new Position();

        login = new JPanel();
        loginTitle = new JLabel("Sign In");
        name = new JTextField("anwer@gmail.com");
        nameText=new JLabel("Email");
        pass = new JPasswordField("Aa@123456");
        passText=new JLabel("Password");
        registerPageLink=new JLabel("Create an account");
        ShowLogin();
    }

    private void ShowLogin() {


        setComponentsBackground();
        addComponentsBackground();
        setComponentsLogin();
        addComponentsLogin();
        imageBackground.add(login);
        this.setVisible(true);
    }

    private void setComponentsBackground() {
        setBackgroundImage();
        setJFrame();
        setBackground();
    }
    private void addComponentsBackground() {
        addComponentBackground(imageBackground);
    }
    private void addComponentBackground(Object component) {
        if (component instanceof JButton) {
            background.add((JButton) component);
        } else if (component instanceof JPanel) {
            background.add((JPanel) component);
        } else if (component instanceof JTextField) {
            background.add((JTextField) component);
        }
        else if (component instanceof JLabel) {
            background.add((JLabel) component);
        }
    }

    private void setComponentsLogin() {
        setLogin();
        setLogo();
        setLoginTitle();
        setName();
        setNameText();
        setPass();
        setPassText();
        setLoginButton();
        setRegisterPageLink();
    }

    private void addComponentsLogin() {
        addComponentLogin(loginTitle);
        addComponentLogin(logo);
        addComponentLogin(loginButton);
        addComponentLogin(name);
        addComponentLogin(nameText);
        addComponentLogin(pass);
        addComponentLogin(passText);
        addComponentLogin(registerPageLink);
    }
    private void addComponentLogin(Object component) {
        if (component instanceof JButton) {
            login.add((JButton) component);
        } else if (component instanceof JPanel) {
            login.add((JPanel) component);
        } else if (component instanceof JTextField) {
            login.add((JTextField) component);
        } else if (component instanceof JLabel) {
            login.add((JLabel) component);
        }
    }
    private void setBackgroundImage() {
        String defaultPath="../image/background2.jpg";
        int x=size.SizeXPercent(100);
        int y=size.SizeYPercent(100);
        imageBackground = new JLabel(getImage(defaultPath,x,y));
        imageBackground.setBounds(
                0,0,
                size.SizeXPercent(100),
                size.SizeYPercent(100)
        );
    }
    private void setJFrame() {
        this.setTitle("Login form");
        this.setSize(size.SizeXPercent(102), size.SizeYPercent(102));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(position.MoveXPercent(0), position.MoveYPercent(0));
        this.setResizable(false);
        this.setLayout(null);
    }
    private void setBackground() {
        background.setBackground(Color.decode("#F4F4F4"));
        background.setSize(size.SizeXPercent(102), size.SizeYPercent(102));
        background.setLayout(null);
        this.add(background);
    }



    private void setLogin() {
        login.setBackground(Color.decode("#F7F4F1"));
        login.setSize(size.SizeXPercent(30), size.SizeYPercent(75));
        login.setLayout(null);
        login.setLocation(position.MoveXPercent(65), position.CenterY(login.getHeight()));
    }
    private void setLogo() {
        String defaultPath="../image/logo.Png";
        int x=size.SizeXPercent(login.getWidth(), 80);
        int y=size.SizeYPercent(login.getHeight(), 20);
        logo = new JLabel(getImage(defaultPath,x,y));
        logo.setBounds(
                position.MoveXPercent(login.getWidth(),7),
                position.MoveYPercent(login.getHeight(), 8),
                size.SizeXPercent(login.getWidth(), 80),
                size.SizeYPercent(login.getHeight(), 20)
        );
    }

    private void setLoginTitle() {
        Font font = new Font("", Font.BOLD, 42);
        loginTitle.setBounds(
                position.CenterX(login.getWidth(), size.SizeXPercent(login.getWidth(), 35)),
                position.MoveYPercent(login.getHeight(), 29),
                size.SizeXPercent(login.getWidth(), 35),
                size.SizeYPercent(login.getHeight(), 8)
        );
        loginTitle.setFont(font);
        loginTitle.setForeground(Color.decode("#2573B0"));
    }


    private void setName() {
        name.setBounds(
                position.CenterX(login.getWidth(), size.SizeXPercent(login.getWidth(), 80)),
                position.MoveYPercent(login.getHeight(), 45),
                size.SizeXPercent(login.getWidth(), 80),
                size.SizeYPercent(login.getHeight(), 5)
        );
        name.setBackground(Color.decode("#ffffff"));
        name.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        name.setFont(new Font("", Font.PLAIN, 20));
        name.setForeground(Color.BLACK);
    }
    private void setNameText() {
        nameText.setBounds(
                position.CenterX(login.getWidth(), size.SizeXPercent(login.getWidth(), 80)),
                position.MoveYPercent(login.getHeight(), 39),
                size.SizeXPercent(login.getWidth(), 80),
                size.SizeYPercent(login.getHeight(), 5)
        );
        nameText.setFont(new Font("", Font.PLAIN, 20));
        nameText.setForeground(Color.decode("#2573B0"));
    }


    private void setPass() {
        pass.setBounds(
                position.CenterX(login.getWidth(), size.SizeXPercent(login.getWidth(), 80)),
                position.MoveYPercent(login.getHeight(), 60),
                size.SizeXPercent(login.getWidth(), 80),
                size.SizeYPercent(login.getHeight(), 5)
        );
        pass.setBackground(Color.decode("#ffffff"));
        pass.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        pass.setFont(new Font("", Font.PLAIN, 20));
        pass.setForeground(Color.BLACK);
    }
    private void setPassText() {
        passText.setBounds(
                position.CenterX(login.getWidth(), size.SizeXPercent(login.getWidth(), 80)),
                position.MoveYPercent(login.getHeight(), 54),
                size.SizeXPercent(login.getWidth(), 80),
                size.SizeYPercent(login.getHeight(), 5)
        );
        passText.setFont(new Font("", Font.PLAIN, 20));
        passText.setForeground(Color.decode("#2573B0"));
    }

    private void setLoginButton() {
        String defaultPath="../image/login_before.png";
        int x=size.SizeXPercent(login.getWidth(),30);
        int y=size.SizeYPercent(login.getHeight(),6);
        loginButton = new JLabel(getImage(defaultPath,x,y));
        loginButton.setBounds(
                position.CenterX(login.getWidth(), size.SizeXPercent(login.getWidth(), 30)),
                position.MoveYPercent(login.getHeight(), 70),
                size.SizeXPercent(login.getWidth(), 30),
                size.SizeYPercent(login.getHeight(), 6)
        );
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                String path="../image/login_after.png";
                loginButton.setIcon(getImage(path,x,y));
                loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setIcon(getImage(defaultPath,x,y));
                loginButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e) {
                String password = String.valueOf(pass.getPassword());
                String email=name.getText();
                try {
                    String res=userexquery.logIn(email,password);
                    if(res.charAt(0)-'0'<10) {
                        int userId = Integer.parseInt(res);
                        JOptionPane.showMessageDialog(null, "Login Successes");
                        new Reservation(userId);
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, res);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    private void setRegisterPageLink()
    {
        registerPageLink.setFont(new Font("", Font.BOLD, 18));
        registerPageLink.setForeground(Color.decode("#396cc4"));
        registerPageLink.setBounds(
                position.CenterX(login.getWidth(), size.SizeXPercent(login.getWidth(), 38)),
                position.MoveYPercent(login.getHeight(), 88),
                size.SizeXPercent(login.getWidth(), 40),
                size.SizeYPercent(login.getHeight(), 6)
        );
        registerPageLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerPageLink.setForeground(Color.decode("#e61919"));
                registerPageLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerPageLink.setForeground(Color.decode("#396cc4"));
                registerPageLink.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mouseClicked(MouseEvent e) {
                new Register();
                dispose();
            }
        });
    }
    public ImageIcon getImage(String path, int x, int y) {
        image = new ImageIcon(Objects.requireNonNull(getClass().getResource(path)));
        imageSize = image.getImage();
        imageSize = imageSize.getScaledInstance(
                x,
                y,
                Image.SCALE_SMOOTH
        );
        image = new ImageIcon(imageSize);
        return image;
    }
}
