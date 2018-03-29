package GUI;

import definitions.ConstantValues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class resultPopUpPage extends JPanel{

    JScrollPane scroll;
    JTable table;
    JButton exit = new JButton("Go Back");
    resultPopUpPage cur = this;
    JLabel message;

    public resultPopUpPage(ResultSet rs, String msg){
        ResultTable rt = new ResultTable(rs);
        setBackground(new Color(199,210,208));
        message = new JLabel(msg);
        message.setFont(new Font("Serif", Font.PLAIN, 20));
        table = rt.getResultTable();
        scroll = rt.getScrollPane();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());
        FreeGeekApp.loggedInWindow.setVisible(false);
        FreeGeekApp.loggedInWindow.setEnabled(false);
        c.gridy =0;
        c.gridx =0;
        c.insets = new Insets(20,10,20,10);
        add(message,c);
        c.gridy=1;
        c.gridx=0;
        add(scroll,c);
        c.gridy=2;
        c.gridx=0;
        add(exit,c);
        setVisible(true);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FreeGeekApp.loggedInWindow.setEnabled(true);
                FreeGeekApp.loggedInWindow.setVisible(true);
                FreeGeekApp.windowFrame.remove(cur);
            }
        });
    }

}
