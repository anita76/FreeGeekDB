package GUI;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.ResultSet;

public class ResultTable {

    private JScrollPane scroll;
    private JTable resultTable;
    private ResultSet rs;

    public ResultTable(ResultSet rs){
        this.rs =rs;
        setResultTable();
    }

    public JScrollPane getScrollPane(){
        return scroll;
    }


    private void setResultTable(){
        resultTable = new JTable(){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        resultTable.setFont(new Font("Serif",Font.PLAIN,20));
        resultTable.setVisible(true);
        resultTable.setModel(DbUtils.resultSetToTableModel(rs));
        resultTable.setRowHeight(30);
        int totalWidth = setTableWidth();
        scroll = new JScrollPane(resultTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(totalWidth,200));
    }

    public JTable getResultTable() {
        return resultTable;
    }

    //modified code from:
    // resize code from https://tips4java.wordpress.com/2008/11/10/table-column-adjuster/
    private int setTableWidth(){
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int totalWidth =0;
        for (int column = 0; column < resultTable.getColumnCount(); column++)
        {
            TableColumn tableColumn = resultTable.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < resultTable.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = resultTable.getCellRenderer(row, column);
                Component c = resultTable.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + resultTable.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }

                Object value = tableColumn.getHeaderValue();
                TableCellRenderer renderer = tableColumn.getHeaderRenderer();

                if (renderer == null)
                {
                    renderer = resultTable.getTableHeader().getDefaultRenderer();
                }

                Component cd = renderer.getTableCellRendererComponent(resultTable, value, false, false, -1, column);
                int headerSize = cd.getPreferredSize().width;
                if (headerSize>preferredWidth){preferredWidth=headerSize;}
            }

            tableColumn.setPreferredWidth( preferredWidth+10);
            totalWidth = totalWidth+10+preferredWidth;
        }
        return totalWidth;
    }
}
