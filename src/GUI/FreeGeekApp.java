package GUI;

import JDBC.GuestUser;
import definitions.ConstantValues;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

public class FreeGeekApp {

    public static JFrame windowFrame = new JFrame("FreeGeekManagementSystem");
    public static LoginWindow loginWindow = new LoginWindow();
    public static GuestUser currentUser = null;
    public static MainWindow mainWindow = null;

    public FreeGeekApp(){
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = new Dimension(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        windowFrame.getContentPane().setPreferredSize(d);
        windowFrame.pack();
        windowFrame.setLocationRelativeTo(null);
        windowFrame.setVisible(true);
        windowFrame.setResizable(false);
        windowFrame.add(loginWindow);

    }

    public static void main(String[] args ){


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FreeGeekApp application = new FreeGeekApp();
            }
        });
    }
}

