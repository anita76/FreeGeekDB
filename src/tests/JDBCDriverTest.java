package tests;

import JDBC.Employee;
import JDBC.GuestUser;
import JDBC.JDBCDriver;
import javafx.util.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;

public class JDBCDriverTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getInstance() throws SQLException {
        GuestUser gu = new GuestUser(1);
        System.out.println(gu.getFirstName());
        //JDBCDriver jdbcDriver = JDBCDriver.getInstance();
    }

    @org.junit.Test
    public void disconnect() {
    }

    @org.junit.Test
    public void executeDataUpdate() throws SQLException {


    }

    @org.junit.Test
    public void executeDataQuery() throws SQLException {
        Employee employee = new Employee(00001);
        List<String> info = new ArrayList<String>();
        List<String> out = new ArrayList<String>();

        info.add("ID");
        info.add("firstName");
        info.add("lastName");
        out = employee.superviseInfo(info);
        System.out.printf("Info that you want: "+info.get(0)+ " "+info.get(1)+" "+info.get(2)+"\n");
        for (String s: out){
            System.out.printf(s);
            System.out.printf("\n");
        }
        out = employee.allVolunteersDiffVSType();
        System.out.printf("volunteers who attend shifts with all different shiftTypes:\n");
        for (String s: out){
            System.out.printf(s);
            System.out.printf("\n");
        }
        List<Pair<String, Integer>> outs = new ArrayList<Pair<String, Integer>>();
        outs = employee.minAttend();
        for (Pair<String, Integer> s: outs){
            String ans = s.getKey() + " is " + s.getValue();
            System.out.printf("Minimum attend for special event: "+ans);
            System.out.printf("\n");
        }
        outs = employee.maxAttend();
        for (Pair<String, Integer> s: outs){
            String ans = s.getKey() + " is " + s.getValue();
            System.out.printf("Maximum attend for special event: "+ans);
            System.out.printf("\n");
        }
        outs = employee.averageAttend();
        for (Pair<String, Integer> s: outs){
            String ans = s.getKey() + " is " + s.getValue();
            System.out.printf("Average attend for special event: "+ans);
            System.out.printf("\n");
        }
        int anss = employee.leastPopularEvent();
        System.out.printf("least average of attendence: " + anss + "\n");
        anss = employee.mostPopularEvent();
        System.out.printf("most average of attendence: " + anss + "\n");
    }
}