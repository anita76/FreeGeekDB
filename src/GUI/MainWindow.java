package GUI;

import JDBC.GuestUser;
import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JPanel{
    private JButton loginBtn = new JButton("Log In");
    private JButton signupBtn = new JButton("Create Account");

    private JLabel userIDLabel = new JLabel("User ID:");
    private JLabel firstNameLabel = new JLabel("First Name:");
    private JLabel lastNameLabel = new JLabel("Last Name:");
    private JLabel emailLabel = new JLabel("E-mail:");
    private JLabel phoneNumLabel = new JLabel("Phone Number:");
    private GridBagConstraints c = new GridBagConstraints();
    private JLabel userIDField;
    private JTextField firstNameField = new JTextField(25);
    private JTextField lastNameField = new JTextField(25);
    private JTextField emailField = new JTextField(25);
    private JTextField phoneNumField = new JTextField(25);

    public MainWindow(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        GuestUser cur = FreeGeekApp.currentUser;
        firstNameField.setText(cur.getFirstName().trim());
        lastNameField.setText(cur.getLastName().trim());
        emailField.setText(cur.getEmail().trim());
        phoneNumField.setText(Long.toString(cur.getPhoneNum()));
        userIDField = new JLabel(Integer.toString(cur.getId()));

        setLabels(firstNameLabel,0,1);
        setLabels(lastNameLabel,0,2);
        setLabels(emailLabel,0,3);
        setLabels(userIDLabel,0,0);
        setLabels(phoneNumLabel,0,4);
        setIDField();
        setFields(firstNameField,1,1);
        setFields(lastNameField,1,2);
        setFields(emailField,1,3);
        setFields(phoneNumField,1,4);




    }

    private void setLabels(JLabel label, int x, int y){
        label.setFont(new Font("Serif",Font.PLAIN,20));
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        c.gridx = x;
        c.gridy = y;
        c.gridheight=1;
        c.gridwidth =1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20,10,20,10);
        add(label, c);
    }

    private void setIDField(){
        userIDField.setOpaque(true);
        userIDField.setBackground(new Color(255,255,255));
        userIDField.setPreferredSize(new Dimension(350,30));
        userIDField.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        userIDField.setFont(new Font("Serif",Font.PLAIN,20));
        userIDField.setHorizontalTextPosition(SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight=1;
        c.gridwidth =1;
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(20,10,20,10);
        add(userIDField, c);
    }

    private void setFields(JTextField field, int x, int y){
        field.setFont(new Font("Serif", Font.PLAIN,20));
        c.gridx = x;
        c.gridy = y;
        c.gridheight=1;
        c.gridwidth=1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(20,10,20,100);
        add(field, c);
    }
}
