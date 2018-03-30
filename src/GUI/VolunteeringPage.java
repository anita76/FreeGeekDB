package GUI;

import JDBC.Volunteer;
import definitions.ConstantValues;
import javafx.scene.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class VolunteeringPage extends JPanel{
    private JLabel volunteerHrLabel = new JLabel("Your total Volunteer Hours:");
    private JTextField volunteerHrFeild = new JTextField(25);
    private GridBagConstraints c = new GridBagConstraints();
    private JButton getHr = new JButton("Compute Hours");
    private JLabel descriptionSignup = new JLabel("Input date range in YYYY-MM-DD format to find available shifts:");
    private JLabel fromDate = new JLabel("From:");
    private JLabel toDate = new JLabel(("To:"));
    private JTextField fromDateField = new JTextField(25);
    private JTextField toDateField = new JTextField(25);
    private JButton findShifts = new JButton("Find Shifts");

    VolunteeringPage(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        setupComputeHrUI();
        setupSignUpForShiftUI();
        findShiftsHandler();

    }

    private void setupComputeHrUI(){
        add(volunteerHrLabel, c);
        setLabels(volunteerHrLabel,0,0);
        setFields(volunteerHrFeild,1,0);
        setBtn(getHr,1,1);
        computeHrHandler();
    }

    private void computeHrHandler(){
        getHr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Volunteer v = (Volunteer) FreeGeekApp.currentUser;
                int hr = v.getVolunteerHr();
                volunteerHrFeild.setText(Integer.toString(hr*3));
            }
        });
    }

    private void findShiftsHandler(){
        findShifts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromDate = fromDateField.getText();
                String toDate = toDateField.getText();
                boolean valid = checkDateValidity(fromDate,toDate);
                if(valid){
                    Volunteer v = (Volunteer) FreeGeekApp.currentUser;
                    ResultSet rs = v.getFullSpots(fromDate,toDate);
                    FreeGeekApp.shiftQuery=true;
                    resultPopUpPage showResults = new resultPopUpPage(rs,"These shifts conflict with your selected time range:");
                    FreeGeekApp.windowFrame.add(showResults);

                }
            }
        });
    }

    private boolean checkDateValidity(String fromDate, String toDate){
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            Date date1 = df.parse(fromDate);
            Date date2 = df.parse(toDate);
            if(date1.after(date2)){
                JOptionPane.showMessageDialog(null,"from date should be before to date", "Invalid Input",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            LocalDate localDate = LocalDate.now();
            if(date1.before(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))){
                JOptionPane.showMessageDialog(null,"enter dates in the future", "Invalid Input",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (ParseException exception) {
            JOptionPane.showMessageDialog(null,"Please enter a valid date", "Invalid Input",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void setupSignUpForShiftUI(){
        descriptionSignup.setFont(new Font("Serif",Font.PLAIN,20));
        descriptionSignup.setHorizontalTextPosition(SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth =2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20,10,5,10);
        add(descriptionSignup, c);

        setLabels(toDate,0,4);
        setLabels(fromDate,0,3);
        setFields(toDateField,1,4);
        setFields(fromDateField,1,3);
        setBtn(findShifts,1,5);

    }

    private void setLabels(JLabel label, int x, int y){
        label.setFont(new Font("Serif",Font.PLAIN,20));
        label.setHorizontalTextPosition(SwingConstants.RIGHT);
        c.gridx = x;
        c.gridy = y;
        c.gridheight=1;
        c.gridwidth =1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,10,5,10);
        add(label, c);
    }

    private void setFields(JTextField field, int x, int y){
        field.setFont(new Font("Serif", Font.PLAIN,20));
        c.gridx = x;
        c.gridy = y;
        c.gridheight=1;
        c.gridwidth=1;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5,10,5,20);
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
        new Insets(5,10,20,10);
        add(btn,c);
    }
}
