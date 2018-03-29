package GUI;

import JDBC.Employee;
import definitions.ConstantValues;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Statistics extends JPanel{

    private JLabel searchLabel = new JLabel("Find the most/least popular events:");
    private JButton eventSearch = new JButton("Search");
    private JTextArea resultArea = new JTextArea();
    private GridBagConstraints c = new GridBagConstraints();
    JRadioButton max = new JRadioButton("Most popular",true);
    JRadioButton min = new JRadioButton("Least popular",false);

    public Statistics(){
        setSize(ConstantValues.WIDTH,ConstantValues.HEIGHT);
        setBackground(new Color(199,210,208));
        setLayout(new GridBagLayout());
        setVisible(true);

        searchLabel.setFont(new Font("Serif",Font.PLAIN,20));
        searchLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight=1;
        c.gridwidth =1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(20,10,20,10);
        add(searchLabel, c);

        max.setFont(new Font("Serif",Font.PLAIN,20));
        max.setBackground(new Color(199,210,208));
        min.setFont(new Font("Serif",Font.PLAIN,20));
        min.setBackground(new Color(199,210,208));
        ButtonGroup groupOptions = new ButtonGroup();
        groupOptions.add(max);
        groupOptions.add(min);
        c.gridx = 1;
        this.add(max,c);
        c.gridx = 2;
        this.add(min,c);
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        eventSearch.setFont(new Font("Serif",Font.PLAIN,20));
        add(eventSearch,c);
        resultArea.setFont(new Font("Serif",Font.PLAIN,20));
        resultArea.setEditable(false);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        this.add(resultArea,c);
        handleEventSearchButton();
    };

    private void handleEventSearchButton(){
        eventSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(min.isSelected()){
                    Employee employee = (Employee) FreeGeekApp.currentUser;
                    try {
                        List<Pair<String, Integer>> events = employee.leastPopularEvent();
                        StringBuilder sb = new StringBuilder();
                        for(Pair<String, Integer> pair:events){
                            sb.append(pair.getKey());
                            sb.append("\n");
                            resultArea.setText(sb.toString());
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    Employee employee = (Employee) FreeGeekApp.currentUser;
                    try {
                        List<Pair<String, Integer>> events = employee.mostPopularEvent();
                        StringBuilder sb = new StringBuilder();
                        for(Pair<String, Integer> pair:events){
                            sb.append(pair.getKey());
                            sb.append("\n");
                            resultArea.setText(sb.toString());
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
