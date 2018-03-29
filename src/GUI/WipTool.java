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
import java.sql.ResultSet;
import java.sql.SQLException;


public class WipTool extends JPanel{

    JScrollPane scroll;
    JTable resultTable;
    JLabel volunteerLabel = new JLabel("Experienced Volunteers");
    private GridBagConstraints c = new GridBagConstraints();

    public WipTool(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        resultTable = new JTable(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        Employee cur = (Employee) FreeGeekApp.currentUser;
        ResultSet rs = null;
        try {
            rs = cur.minAttend();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultTable rt = new ResultTable(rs);
        scroll= rt.getScrollPane();
        resultTable = rt.getResultTable();
        volunteerLabel.setFont(new Font("Serif",Font.PLAIN,40));
        c.gridx=0;
        c.gridy=0;
        add(volunteerLabel,c);
        c.gridx=0;
        c.gridy=1;
        add(scroll,c);


    }
}
