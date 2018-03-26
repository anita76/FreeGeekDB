import JDBC.JDBCDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FreeGeekApp {

    public static void main(String[] args ) throws SQLException {
        JDBCDriver jdbcDriver = JDBCDriver.getInstance();
        jdbcDriver.executeDataUpdate("create table test(id char(30) primary key)");
        jdbcDriver.executeDataUpdate("insert into test values('hi')");
        ResultSet rs = jdbcDriver.executeDataQuery("select * from test");
        rs.next();
        String value = rs.getString("id");
        System.out.println("The query gave id: "+ value);
        jdbcDriver.disconnect();
    }
}
