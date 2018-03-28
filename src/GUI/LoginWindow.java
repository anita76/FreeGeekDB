package GUI;
import JDBC.Employee;
import JDBC.GuestUser;
import JDBC.JDBCDriver;
import JDBC.Volunteer;
import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginWindow extends JPanel{

    private JButton loginBtn = new JButton("Log In");
    private JButton signupBtn = new JButton("Create Account");
    private JLabel userIDLabel = new JLabel("User ID:");
    private JLabel firstNameLabel = new JLabel("First Name:");
    private JLabel lastNameLabel = new JLabel("Last Name:");
    private JLabel emailLabel = new JLabel("E-mail:");
    private JLabel phoneNumLabel = new JLabel("Phone Number:");
    private JLabel accountTypeLabel = new JLabel("Account Type:");
    private GridBagConstraints c = new GridBagConstraints();
    private JTextField userIDField = new JTextField(25);
    private JTextField firstNameField = new JTextField(25);
    private JTextField lastNameField = new JTextField(25);
    private JTextField emailField = new JTextField(25);
    private JTextField phoneNumField = new JTextField(25);
    private JComboBox accountTypeBox;

    public LoginWindow() {
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setVisible(true);
        setLayout(new GridBagLayout());

        setSignUpLabel(firstNameLabel,0,0);
        setSignUpLabel(lastNameLabel,0,1);
        setSignUpLabel(emailLabel,0,2);
        setSignUpLabel(phoneNumLabel,0,3);
        setSignUpLabel(accountTypeLabel,0,4);
        setSignUpFields(firstNameField,1,0);
        setSignUpFields(lastNameField,1,1);
        setSignUpFields(emailField,1,2);
        setSignUpFields(phoneNumField,1,3);
        setComboBox();
        setSignInLabel();
        setSignInField();
        setBtn(loginBtn,3,1);
        setBtn(signupBtn,1,5);

        loginHandle();

    }

    private void setSignUpLabel(JLabel label, int x, int y){
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

    private void setSignInLabel(){
        userIDLabel.setFont(new Font("Serif",Font.PLAIN,20));
        userIDLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets = new Insets(20,100,20,10);
        add(userIDLabel, c);
    }

    private void setSignUpFields(JTextField field, int x, int y){
        field.setFont(new Font("Serif", Font.PLAIN,20));
        c.gridx = x;
        c.gridy = y;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets = new Insets(20,10,20,100);
        add(field, c);
    }

    private void setComboBox(){
        String[] options = {"Guest","Volunteer", "Employee"};
        accountTypeBox = new JComboBox(options);
        accountTypeBox.setFont(new Font("Serif", Font.PLAIN,20));
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth=1;
        c.gridheight=1;
        c.insets =new Insets(20,10,20,100);
        add(accountTypeBox,c);
    }

    private void setSignInField(){
        userIDField.setFont(new Font("Serif", Font.PLAIN,20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight=1;
        c.gridwidth=1;
        c.insets = new Insets(20,10,20,100);
        add(userIDField,c);
    }

    private void setBtn(JButton btn, int x, int y){
        btn.setFont(new Font("Serif", Font.PLAIN, 20));
        Dimension d = new Dimension(200,30);
        btn.setPreferredSize(d);
        c.gridx = x;
        c.gridy = y;
        c.fill = GridBagConstraints.NONE;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.EAST;
        new Insets(20,10,20,100);
        add(btn,c);
    }

    private void loginHandle(){
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputIDValue = userIDField.getText();
                JDBCDriver jdbcDriver = JDBCDriver.getInstance();
                /*try{
                    int id = Integer.parseInt(inputIDValue);
                    GuestUser gu = new GuestUser(id);
                    //Todo: once volunteer and empolyee are done you have to add the correct user here
                    FreeGeekApp.currentUser=gu;
                    if(gu.isCreated()) {
                        LoggedInWindow loggedInWindow = new LoggedInWindow();
                        FreeGeekApp.loggedInWindow = loggedInWindow;
                        FreeGeekApp.windowFrame.add(loggedInWindow);
                        setVisible(false);
                        setEnabled(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"No such account exits.", "Invalid ID",JOptionPane.ERROR_MESSAGE);
                    }

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"No such account exits","Invalid ID", JOptionPane.ERROR_MESSAGE);
                }*/

                try{
                    int id = Integer.parseInt(inputIDValue);
                    // Employee Check?
                    StringBuilder sb = new StringBuilder();
                    sb.append("select * from employees where id=");
                    sb.append(id);
                    String query = sb.toString();
                    ResultSet rse = jdbcDriver.executeDataQuery(query);
                    boolean isEmployee=false;
                    try {
                        isEmployee = rse.next();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    sb = new StringBuilder();
                    sb.append("select * from volunteers where id=");
                    sb.append(id);
                    query = sb.toString();
                    rse = jdbcDriver.executeDataQuery(query);
                    boolean isVolunteer=false;
                    try {
                        isVolunteer = rse.next();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                    if (isEmployee){
                        FreeGeekApp.currentUser = new Employee(id);
                    }else if(isVolunteer) {
                        FreeGeekApp.currentUser = new Volunteer(id);
                    }else{
                        FreeGeekApp.currentUser = new GuestUser(id);
                    }


                    if(FreeGeekApp.currentUser.isCreated()) {
                        LoggedInWindow loggedInWindow = new LoggedInWindow();
                        FreeGeekApp.loggedInWindow = loggedInWindow;
                        FreeGeekApp.windowFrame.add(loggedInWindow);
                        userIDField.setText("");
                        setVisible(false);
                        setEnabled(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"No such account exits.", "Invalid ID",JOptionPane.ERROR_MESSAGE);
                    }

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"No such account exits","Invalid ID", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

}
