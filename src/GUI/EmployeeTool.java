package GUI;


import JDBC.Employee;

import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;


public class EmployeeTool extends JPanel{

    private GridBagConstraints c = new GridBagConstraints();

    JScrollPane scroll;
    JTable resultTable;

    // SUPERVISEE
    JLabel supervisorLabel;
    JCheckBox nameButton;
    JCheckBox emailButton;
    JCheckBox phoneButton;
    JButton searchButton;
    EmployeeTool em = this;

    // MIN/MAX ATTEND
    JLabel minLabel;
    JLabel maxLabel;
    JButton minButton;
    JButton maxButton;


    public EmployeeTool(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        // UI ==============================
        // SUPERVISEE INFO SEARCH
        supervisorLabel = new JLabel("MySupervisee's");
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
        handleSearch();

        /* MOVED TO STATISTIC TAB
        // MIN ATTEND
        minLabel = new JLabel("Minimum Attendance");
        setLabels(minLabel, 0,2);
        minButton = new JButton("Look Up Min");
        setBtn(minButton,1,2);
        handleMin();

        // MAX ATTEND
        maxLabel = new JLabel("Maximum Attedance");
        setLabels(maxLabel, 0, 4);
        maxButton = new JButton("Look Up Max");
        setBtn(maxButton, 1, 4);
        handleMax();
        */
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
                        resultPopUpPage showResults = new resultPopUpPage(rs,"Supervisee Information Query Results");
                        FreeGeekApp.windowFrame.add(showResults);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    /*
    private void handleMin(){
        minButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                Employee employeeUser = (Employee) FreeGeekApp.currentUser;
                ResultSet rs = null;
                try {
                    rs = employeeUser.minAttend();
                    resultPopUpPage showResults = new resultPopUpPage(rs,"Daily Minimum Attendance");
                    FreeGeekApp.windowFrame.add(showResults);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });
    }
    */
    /*
    private void handleMax(){
        maxButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                Employee employeeUser = (Employee) FreeGeekApp.currentUser;
                ResultSet rs = null;
                try {
                    rs = employeeUser.maxAttend();
                    resultPopUpPage showResults = new resultPopUpPage(rs,"Daily Maximum Attendance");
                    FreeGeekApp.windowFrame.add(showResults);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

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
