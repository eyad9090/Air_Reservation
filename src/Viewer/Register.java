package Viewer;

import Controller.UserExQuery;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Objects;


public class Register extends JFrame {
    JPanel background, register;
    JLabel imageBackground;
    Position position;
    Size size;
    JLabel logo;
    ImageIcon image;
    Image imageSize;
    JLabel registerTitle, registerButton;
    JLabel loginPageLink;
    JTextField name;
    JTextField email;
    JPasswordField pass;
    JPasswordField confirmPass;
    JLabel nameText;
    JLabel emailText;
    JLabel passText;
    JLabel confirmPassText;

    UserExQuery userexquery=new UserExQuery();

    public Register() {
        background = new JPanel();
        size = new Size();
        position = new Position();
        register = new JPanel();
        registerTitle = new JLabel("Register");
        name = new JTextField("");
        email = new JTextField("");
        pass = new JPasswordField("");
        confirmPass = new JPasswordField("");
        nameText = new JLabel("Name");
        emailText = new JLabel("Email");
        passText = new JLabel("Password");
        confirmPassText = new JLabel("Confirm password");
        loginPageLink = new JLabel("Already has an account");
        showRegister();
    }

    private void showRegister() {

        setComponentsBackground();
        addComponentsBackground();
        setComponentsRegister();
        addComponentsRegister();
        imageBackground.add(register);
        this.setVisible(true);
    }


    private void setComponentsBackground() {
        setBackgroundImage();
        setJFrame();
        setBackground();
    }

    private void setJFrame() {
        this.setTitle("Register form");
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

    private void setComponentsRegister() {
        setRegister();
        setRegisterTitle();
        setName();
        setNameText();
        setEmail();
        setEmailText();
        setPassword();
        setPasswordText();
        setConfirmPass();
        setConfirmPassText();
        setLogo();
        setRegisterButton();
        setLoginPageLink();
    }

    private void addComponentsRegister() {
        addComponentRegister(registerTitle);
        addComponentRegister(logo);
        addComponentRegister(registerButton);
        addComponentRegister(loginPageLink);
        addComponentRegister(name);
        addComponentRegister(nameText);
        addComponentRegister(pass);
        addComponentRegister(passText);
        addComponentRegister(email);
        addComponentRegister(emailText);
        addComponentRegister(confirmPass);
        addComponentRegister(confirmPassText);
    }

    private void addComponentRegister(Object component) {
        if (component instanceof JButton) {
            register.add((JButton) component);
        } else if (component instanceof JPanel) {
            register.add((JPanel) component);
        } else if (component instanceof JTextField) {
            register.add((JTextField) component);
        } else if (component instanceof JLabel) {
            register.add((JLabel) component);
        }
    }

    private void setRegister() {
        register.setBackground(Color.decode("#F7F4F1"));
        register.setSize(size.SizeXPercent(30), size.SizeYPercent(75));
        register.setLayout(null);
        register.setLocation(position.CenterX(register.getWidth()),
                position.CenterY(register.getHeight())
        );
    }

    private void setLogo() {
        String defaultPath="../image/logo.Png";
        int x=size.SizeXPercent(register.getWidth(), 80);
        int y=size.SizeYPercent(register.getHeight(), 20);
        logo = new JLabel(getImage(defaultPath,x,y));
        logo.setBounds(
                position.MoveXPercent(register.getWidth(),7),
                position.MoveYPercent(register.getHeight(), 3),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 20)
        );
    }

    private void setBackgroundImage() {
        String defaultPath="../image/background4.jpg";
        int x=size.SizeXPercent(100);
        int y=size.SizeYPercent(100);
        imageBackground = new JLabel(getImage(defaultPath,x,y));
        imageBackground.setBounds(
                0,0,
                size.SizeXPercent(100),
                size.SizeYPercent(100)
        );
    }
    private void setRegisterTitle() {
        Font font = new Font("", Font.BOLD, 42);
        registerTitle.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 35)),
                position.MoveYPercent(register.getHeight(), 20),
                size.SizeXPercent(register.getWidth(), 50),
                size.SizeYPercent(register.getHeight(), 8)
        );
        registerTitle.setFont(font);
        registerTitle.setForeground(Color.decode("#2573B0"));
    }

    private void setName() {
        name.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 80)),
                position.MoveYPercent(register.getHeight(), 36),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 5)
        );
        name.setBackground(Color.decode("#ffffff"));
        name.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        name.setFont(new Font("", Font.PLAIN, 20));
        name.setForeground(Color.BLACK);
    }

    private void setNameText() {
        nameText.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 80)),
                position.MoveYPercent(register.getHeight(), 30),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 5)
        );
        nameText.setFont(new Font("", Font.PLAIN, 20));
        nameText.setForeground(Color.decode("#2573B0"));
    }

    private void setEmail() {
        email.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 80)),
                position.MoveYPercent(register.getHeight(), 48),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 5)
        );
        email.setBackground(Color.decode("#ffffff"));
        email.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        email.setFont(new Font("", Font.PLAIN, 20));
        email.setForeground(Color.BLACK);
    }

    private void setEmailText() {
        emailText.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 80)),
                position.MoveYPercent(register.getHeight(), 42),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 5)
        );
        emailText.setFont(new Font("", Font.PLAIN, 20));
        emailText.setForeground(Color.decode("#2573B0"));
    }

    private void setPassword() {
//        pass.setEchoChar((char) 0);
        pass.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 80)),
                position.MoveYPercent(register.getHeight(), 60),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 5)
        );
        pass.setBackground(Color.decode("#ffffff"));
        pass.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        pass.setFont(new Font("", Font.PLAIN, 20));
        pass.setForeground(Color.BLACK);
    }

    private void setPasswordText() {
        passText.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 80)),
                position.MoveYPercent(register.getHeight(), 54),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 5)
        );
        passText.setFont(new Font("", Font.PLAIN, 20));
        passText.setForeground(Color.decode("#2573B0"));
    }

    private void setConfirmPass() {
//        confirmPass.setEchoChar((char) 0);
        confirmPass.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 80)),
                position.MoveYPercent(register.getHeight(), 72),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 5)
        );
        confirmPass.setBackground(Color.decode("#ffffff"));
        confirmPass.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.lightGray));
        confirmPass.setFont(new Font("", Font.PLAIN, 20));
        confirmPass.setForeground(Color.BLACK);
    }

    private void setConfirmPassText() {
        confirmPassText.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 80)),
                position.MoveYPercent(register.getHeight(), 66),
                size.SizeXPercent(register.getWidth(), 80),
                size.SizeYPercent(register.getHeight(), 5)
        );
        confirmPassText.setFont(new Font("", Font.PLAIN, 20));
        confirmPassText.setForeground(Color.decode("#2573B0"));
    }

    private void setRegisterButton() {
        String defaultPath = "../image/register_before.png";
        int x = size.SizeXPercent(register.getWidth(), 45);
        int y = size.SizeYPercent(register.getHeight(), 8);
        registerButton = new JLabel(getImage(defaultPath, x, y));
        registerButton.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 60)),
                position.MoveYPercent(register.getHeight(), 84),
                size.SizeXPercent(register.getWidth(), 60),
                size.SizeYPercent(register.getHeight(), 6)
        );
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                String path = "../image/register_after.png";
                registerButton.setIcon(getImage(path, x, y));
                registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setIcon(getImage(defaultPath, x, y));
                registerButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mousePressed(MouseEvent e) {
                //-----------------------------
                String password1 = String.valueOf(pass.getPassword());
                String password2 = String.valueOf(confirmPass.getPassword());
                String username  = name.getText();
                String userMail  = email.getText();
                try {
                    String res       = userexquery.SignUp(username,userMail,password1,password2);
                    JOptionPane.showMessageDialog(null, res);
                    if(res.equals("Sign Up Successfully")) {
                        int userId=userexquery.selectUser(userMail,password1);
                        new Reservation(userId);
                        dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "This E-mail already Used .");
                }
            }
        });
    }

    private void setLoginPageLink() {
        loginPageLink.setFont(new Font("", Font.BOLD, 18));
        loginPageLink.setForeground(Color.decode("#396cc4"));
        loginPageLink.setBounds(
                position.CenterX(register.getWidth(), size.SizeXPercent(register.getWidth(), 45)),
                position.MoveYPercent(register.getHeight(), 90),
                size.SizeXPercent(register.getWidth(), 50),
                size.SizeYPercent(register.getHeight(), 6)
        );
        loginPageLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginPageLink.setForeground(Color.decode("#e61919"));
                loginPageLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginPageLink.setForeground(Color.decode("#396cc4"));
                loginPageLink.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            public void mouseClicked(MouseEvent e) {
                new Login();
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
