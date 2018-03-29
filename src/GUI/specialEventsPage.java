package GUI;

import JDBC.JDBCDriver;
import definitions.ConstantValues;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.ResultSet;

public class specialEventsPage extends JPanel {

    JScrollPane scroll;
    JTable resultTable;
    JLabel userEventLabel = new JLabel("Your Events");
    private GridBagConstraints c = new GridBagConstraints();

    public specialEventsPage(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);
        setUpUserEventDisplay();
        setUpEventSearchDisplay();



    }

    private void setUpEventSearchDisplay(){
        
    }



    private void setUpUserEventDisplay(){

        ResultSet rs = FreeGeekApp.currentUser.getUserEvents();
        ResultTable rt = new ResultTable(rs);
        scroll= rt.getScrollPane();
        resultTable = rt.getResultTable();
        userEventLabel.setFont(new Font("Serif",Font.PLAIN,40));
        c.gridx=0;
        c.gridy=0;
        add(userEventLabel,c);
        c.gridx=0;
        c.gridy=1;
        add(scroll,c);
    }
}
