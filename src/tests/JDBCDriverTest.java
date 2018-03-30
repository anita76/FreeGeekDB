package tests;

import JDBC.Employee;
import JDBC.GuestUser;
import JDBC.JDBCDriver;
import JDBC.Volunteer;
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
        Volunteer e = new Volunteer(30);
        ResultSet rs = e.getFullSpots("2018-08-09","2018-9-9");
        while(rs.next()){
            System.out.println(rs.getString("shiftType"));
            System.out.println(rs.getDate("shiftDate"));
        }

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
        ResultSet rs = null;
/*
        info.add("ID");
        info.add("firstName");
        info.add("lastName");
         rs = employee.superviseInfo(info);
        while (rs.next()){
            for(int i = 0; i < info.size(); i++){
                System.out.printf(info.get(i)+": ");
                System.out.printf(rs.getString(i+1)+"\n");
            }
        }
        rs = employee.allVolunteersDiffVSType();
        System.out.printf("volunteers who attend shifts with all different shiftTypes:\n");
        while (rs.next()){
            System.out.printf(rs.getString(1)+"\n");
        }

        rs = employee.minAttend();
        while (rs.next()){
            System.out.printf("Minimum attend for special event: ");
            System.out.printf(rs.getString(1)+" " + rs.getString(2)+"\n");
        }
        System.out.printf("\n");

        rs = employee.maxAttend();
        while (rs.next()){
            System.out.printf("Maximum attend for special event: ");
            System.out.printf(rs.getString(1)+" " + rs.getString(2)+"\n");
        }
        System.out.printf("\n");
        rs = employee.averageAttend();
        while (rs.next()){
            System.out.printf("Average attend for special event: ");
            System.out.printf(rs.getString(1)+" " + rs.getString(2)+"\n");
        }
        System.out.printf("\n");
        rs = employee.leastPopularEvent();
        while (rs.next()) {
            System.out.printf("least average of attendence: " + rs.getString(1) + "\n");
        }

        rs = employee.mostPopularEvent();
        while (rs.next()){
            System.out.printf("most average of attendence: " + rs.getString(1) + "\n");
        }*/
    }
}