package tests;

import JDBC.GuestUser;
import JDBC.JDBCDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void executeDataUpdate() {
    }

    @org.junit.Test
    public void executeDataQuery() {
    }
}