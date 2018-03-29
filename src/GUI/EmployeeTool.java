package GUI;

import JDBC.GuestUser;
import JDBC.Employee;
import JDBC.JDBCDriver;
import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javafx.util.Pair;

public class EmployeeTool extends JPanel{

    // SUPERVISOR INFO SEARCH
    private JButton search = new JButton("Search for info");
    private JLabel supervisorInfoLabel = new JLabel("Which Supervisor Info:");
    private JTextField searchField = new JTextField(25);

    // ALL VOLUNTEERS WHO HAVE DONE ALL SHIFT TYPES
    private JButton get = new JButton("Get Volunteers");
    private JLabel allShiftsVolunteers = new JLabel("Experienced Volunteers:");

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
        setLabels(supervisorInfoLabel,0,1);
        setFields(searchField,1,1);
        setBtn(search,2,1);

        // ALL VOLUNTEERS WHO HAVE DONE ALL SHIFT TYPES
        setLabels(allShiftsVolunteers,0,2);
        setBtn(get,1,2);

        /*
        // MIN ATTEND EVENTS PER DAY
        setLabels(minAttendEvents,0,3);
        setBtn(lookup,1,3);

        // MAX ATTEND EVENTS PER DAY
        setLabels(maxAttendEvents,0,4);
        setBtn(lookup2,1,4);
        */

    }

    // HANDLE SUPERVISOR INFO SEARCH
    private void handleSearch(){
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchFieldValue = searchField.getText();
                List<String> foi = new ArrayList<String>(Arrays.asList(searchFieldValue.split(",")));
                Employee cur = (Employee) FreeGeekApp.currentUser;
                try {
                    List<String> result = cur.superviseInfo(foi);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                // DO SOMETHING TO DISPLAY THE RESULT
            }
        });
    }

    // HANDLE EXPERIENCED VOLUNTEER GET
    private void handleGet(){
        get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee cur = (Employee) FreeGeekApp.currentUser;
                try {
                    List<String> result = cur.allVolunteersDiffVSType();
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

}
