package GUI;

import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;

public class LoggedInWindow extends JTabbedPane{

    JPanel mainPage= new MainPage();
    JPanel specialEventSearch = new JPanel();

    LoggedInWindow(){
        add("Main Page",mainPage);
        add("Special Events", specialEventSearch);
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setVisible(true);
    }
}
