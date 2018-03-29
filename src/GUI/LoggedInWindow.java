package GUI;

import JDBC.Employee;
import JDBC.Volunteer;
import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;

public class LoggedInWindow extends JTabbedPane{

    JPanel mainPage= new MainPage();
    JPanel specialEventSearch = new specialEventsPage();
    JPanel employeeTab;
    JPanel volunteerTab;
    JPanel volunteering;
    JPanel wipTool;
    JPanel statistics;

    LoggedInWindow(){
        add("Main Page",mainPage);
        add("Special Events", specialEventSearch);
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setVisible(true);
        if(FreeGeekApp.currentUser instanceof Volunteer){
            volunteering = new VolunteeringPage();
            volunteerTab = new JPanel();
            add("Volunteer Tool", volunteerTab);
            add("Volunteering", volunteering);
        }
        if(FreeGeekApp.currentUser instanceof Employee){
            wipTool = new VolunteerStatistics();
            employeeTab = new EmployeeTool();
            statistics = new Statistics();
            add("Employee Tool",employeeTab);
            add("Volunteer Stat Page",wipTool);
            add("Statistics",statistics);
        }
    }
}
