package JDBC;

import java.sql.ResultSet;

public class Volunteer extends GuestUser {
    private int volunteerHr;
    private JDBCDriver jdbcDriver = JDBCDriver.getInstance();

    public Volunteer(int ID){
        super(ID);
    }
}
