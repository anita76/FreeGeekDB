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

    public specialEventsPage(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        resultTable = new JTable(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        JDBCDriver jdbcDriver = JDBCDriver.getInstance();
        ResultSet rs = jdbcDriver.executeDataQuery("select TO_CHAR(eventDATE, 'YYYY-MM-DD'), name, entranceFee, startTIME, endTIME, capacity from specialEvents");
        ResultTable rt = new ResultTable(rs);
        scroll= rt.getScrollPane();
        resultTable = rt.getResultTable();
        add(scroll);


    }
}
