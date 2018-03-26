package GUI;

import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;

public class FreeGeekApp {

    private JFrame windowFrame = new JFrame("FreeGeekManagementSystem");
    public LoginWindow loginWindow = new LoginWindow();

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

