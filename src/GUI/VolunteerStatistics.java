package GUI;

import JDBC.JDBCDriver;
import JDBC.Employee;
import definitions.ConstantValues;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;


public class VolunteerStatistics extends JPanel{

    // ALL VOLUNTEERS WHO HAVE DONE ALL DIFFERENT TYPES OF SHIFTS !!!!!!

    private GridBagConstraints c = new GridBagConstraints();
    JButton searchButton;
    JLabel volunteerLabel;

    public VolunteerStatistics(){

        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        volunteerLabel = new JLabel("Experienced Volunteers:");
        setLabels(volunteerLabel,0,0);

        searchButton = new JButton("Search");
        setBtn(searchButton,4,0);
        handleSearch();

    }

    // HANDLE SUPERVISOR INFO SEARCH
    private void handleSearch(){
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                Employee employeeUser = (Employee) FreeGeekApp.currentUser;
                ResultSet rs = null;
                try {
                    rs = employeeUser.allVolunteersDiffVSType();
                    resultPopUpPage showResults = new resultPopUpPage(rs,"Experienced Volunteers");
                    FreeGeekApp.windowFrame.add(showResults);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

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

}
