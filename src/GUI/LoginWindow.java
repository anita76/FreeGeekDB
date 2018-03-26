package GUI;
import JDBC.JDBCDriver;
import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JPanel{

    private JButton loginBtn = new JButton("Login");
    private JLabel userIDLabel = new JLabel("UserID:");
    private JTextField userIDField = new JTextField(10);
    private GridBagConstraints c = new GridBagConstraints();

    public LoginWindow() {

        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(125,210,191));
        setVisible(true);
        setLayout(new GridBagLayout());
        setUserIDLabel();
        setUserIDField();
        setLoginBtn();
        loginHandle();

    }

    private void setUserIDLabel(){
        userIDLabel.setFont(new Font("Serif",Font.PLAIN,20));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20,20,20,20);
        add(userIDLabel, c);
    }

    private void setUserIDField(){
        c.gridx = 1;
        c.gridy = 0;
        add(userIDField, c);
    }

    private void setLoginBtn(){
        loginBtn.setFont(new Font("Serif", Font.PLAIN, 20));
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 2;
        c.gridwidth = 2;
        add(loginBtn,c);
    }

    private void loginHandle(){
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputIDValue = userIDField.getText();
                JDBCDriver jdbcDriver = JDBCDriver.getInstance();
                try{
                    Integer.parseInt(inputIDValue);

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Please Enter a Number","Error!", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

}
