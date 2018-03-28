package GUI;

import JDBC.Employee;
import JDBC.Volunteer;
import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;

public class LoggedInWindow extends JTabbedPane{

    JPanel mainPage= new MainPage();
    JPanel specialEventSearch = new JPanel();
    JPanel employeeTab = new JPanel();
    JPanel volunteerTab = new JPanel();
    JPanel volunteerShiftTab = new JPanel();
    JPanel eventReservationsTab = new JPanel();

    LoggedInWindow(){
        add("Main Page",mainPage);
        add("Special Events", specialEventSearch);
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setVisible(true);
        if(FreeGeekApp.currentUser instanceof Employee || FreeGeekApp.currentUser instanceof Volunteer){
            add("Volunteer Tool", volunteerTab);
            add("Volunteer Shifts",volunteerShiftTab);
        }
        if(FreeGeekApp.currentUser instanceof Employee){
            add("Reservations", eventReservationsTab);
            add("Employee Tool",employeeTab);
        }
    }
}
