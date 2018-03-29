package GUI;

import JDBC.GuestUser;
import JDBC.Employee;
import JDBC.JDBCDriver;
import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javafx.util.Pair;

public class EmployeeTool extends JPanel{

    // SUPERVISOR INFO SEARCH
    /*
    private JButton search = new JButton("Search for info");
    private JLabel supervisorInfoLabel = new JLabel("Which Supervisor Info:");
    private JTextField searchField = new JTextField(25);

    // ALL VOLUNTEERS WHO HAVE DONE ALL SHIFT TYPES
    private JButton get = new JButton("Get Volunteers");
    private JLabel allShiftsVolunteers = new JLabel("Experienced Volunteers:");
    */
    private GridBagConstraints c = new GridBagConstraints();

    JScrollPane scroll;
    JTable resultTable;

    JLabel supervisorLabel;
    JCheckBox nameButton;
    JCheckBox emailButton;
    JCheckBox phoneButton;
    JButton searchButton;

    /*
    // MIN ATTEND EVNETS PER DAY
    private JButton lookup = new JButton("Look It Up");
    private JLabel minAttendEvents = new JLabel("Daily Minimum Attendance");

    // MAX ATTEND EVNETS PER DAY
    private JButton lookup2 = new JButton("Look It Up");
    private JLabel maxAttendEvents = new JLabel("Daily Maximum Attendance");
    */
    
    public EmployeeTool(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        // UI ==============================
        // SUPERVISOR INFO SEARCH
        /*
        setLabels(supervisorInfoLabel,0,1);
        setBtn(search,2,1);
        */
        supervisorLabel = new JLabel("MySupervisor's");
        setLabels(supervisorLabel,0,0);

        nameButton = new JCheckBox("Name");
        setBox(nameButton, 1,0);
        nameButton.setSelected(true);

        emailButton = new JCheckBox("Email");
        setBox(emailButton, 2,0);
        emailButton.setSelected(true);

        phoneButton = new JCheckBox("Phone#");
        setBox(phoneButton, 3,0);
        phoneButton.setSelected(true);

        searchButton = new JButton("Search");
        setBtn(searchButton,4,0);

        // ALL VOLUNTEERS WHO HAVE DONE ALL SHIFT TYPES
        /*
        setLabels(allShiftsVolunteers,0,2);
        setBtn(get,1,2);
        */
        /*
        // MIN ATTEND EVENTS PER DAY
        setLabels(minAttendEvents,0,3);
        setBtn(lookup,1,3);

        // MAX ATTEND EVENTS PER DAY
        setLabels(maxAttendEvents,0,4);
        setBtn(lookup2,1,4);
        */
        handleSearch();
    }

    // HANDLE SUPERVISOR INFO SEARCH
    private void handleSearch(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                List<String> inputList = new ArrayList<String>();
                if (nameButton.isSelected()){
                    inputList.add("FIRSTNAME");
                    inputList.add("LASTNAME");
                }
                if (emailButton.isSelected()) {
                    inputList.add("EMAIL");
                }
                if (phoneButton.isSelected()) {
                    inputList.add("PHONE");
                }
                if (inputList.isEmpty()){
                    return;
                }
                else{
                    Employee employeeUser = (Employee) FreeGeekApp.currentUser;
                    ResultSet rs = null;
                    try {
                        rs = employeeUser.superviseInfo(inputList);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ResultTable rt = new ResultTable(rs);
                    scroll= rt.getScrollPane();
                    resultTable = rt.getResultTable();
                }
            }
        });
    }
    /*
    // HANDLE EXPERIENCED VOLUNTEER GET
    private void handleGet(){
        get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee cur = (Employee) FreeGeekApp.currentUser;
                try {
                    //added cast anita
                    List<String> result = (List<String>) cur.allVolunteersDiffVSType();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                // DO SOMETHING TO DISPLAY THE RESULT
            }
        });
    }

    /*
    // HANDLE MIN ATTENDANCE FOR SPEICAL EVENTS
    private void handleMinAttend(){
        lookup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee cur = (Employee) FreeGeekApp.currentUser;
                try {
                    List<Pair<String, Integer>> result = cur.minAttend();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                // DO SOMETHING TO DISPLY THE RESULT;
            }
        });
    }

    // HANDLE MIN ATTENDANCE FOR SPEICAL EVENTS
    private void handleMaxAttend(){
        lookup2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee cur = (Employee) FreeGeekApp.currentUser;
                try {
                    List<Pair<String, Integer>> result = cur.maxAttend();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                // DO SOMETHING TO DISPLY THE RESULT;
            }
        });
    }
    */

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

    private void setFields(JTextField field, int x, int y){/*
        field.setFont(new Font("Serif", Font.PLAIN,20));
        c.gridx = x;
        c.gridy = y;
        c.gridheight=1;
        c.gridwidth=1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(20,10,20,100);
        add(field, c);*/
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

    private void setBox(JCheckBox btn, int x, int y){
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

}
