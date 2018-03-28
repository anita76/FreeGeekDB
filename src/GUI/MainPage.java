package GUI;

import JDBC.GuestUser;
import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JPanel{
    private JButton save = new JButton("Save");
    private JButton cancel = new JButton("Cancel");
    private JButton logout = new JButton("Log Out");
    private JButton deleteAcnt = new JButton("Delete Account");

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

    public MainPage(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        GuestUser cur = FreeGeekApp.currentUser;
        userIDField = new JLabel(Integer.toString(cur.getId()));
        setFieldValues();

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
        setBtn(save,1,5);
        setBtn(cancel,0,5);
        setBtn(logout,0,6);
        setBtn(deleteAcnt,1,6);
        handleCancel();
        handleSave();
        handleLogout();
        handleDeleteAccount();


    }

    private void setFieldValues(){
        GuestUser cur = FreeGeekApp.currentUser;
        firstNameField.setText(cur.getFirstName());
        lastNameField.setText(cur.getLastName());
        emailField.setText(cur.getEmail());
        phoneNumField.setText(cur.getPhoneNum());
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
        new Insets(20,10,20,10);
        add(btn,c);
    }

    private void handleCancel(){
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFieldValues();
            }
        });
    }

    private void handleSave(){
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = FreeGeekApp.currentUser.updateAccountInfo(firstNameField.getText(),lastNameField.getText()
                        ,emailField.getText(),phoneNumField.getText());
                if(message.equals("success")){
                    FreeGeekApp.currentUser.email = emailField.getText();
                    FreeGeekApp.currentUser.firstName = firstNameField.getText();
                    FreeGeekApp.currentUser.lastName = lastNameField.getText();
                    FreeGeekApp.currentUser.phoneNum = phoneNumField.getText();
                    JOptionPane.showMessageDialog(null,"Saved changes","",JOptionPane.INFORMATION_MESSAGE);

                }else{
                    JOptionPane.showMessageDialog(null,message, "Invalid Input",JOptionPane.ERROR_MESSAGE);
                    emailField.setText(FreeGeekApp.currentUser.email);
                    firstNameField.setText(FreeGeekApp.currentUser.firstName);
                    lastNameField.setText(FreeGeekApp.currentUser.lastName);
                    phoneNumField.setText(FreeGeekApp.currentUser.phoneNum );

                }
            }
        });
    }

    private void handleLogout(){
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FreeGeekApp.loginWindow.setVisible(true);
                FreeGeekApp.loginWindow.setEnabled(true);
                FreeGeekApp.loggedInWindow.setVisible(false);
                FreeGeekApp.loggedInWindow.setEnabled(false);
            }
        });
    }

    private void handleDeleteAccount(){
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
    }
}
