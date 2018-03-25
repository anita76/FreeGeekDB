package JDBC;

import java.sql.ResultSet;

public class GuestUser {

    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public int phoneNum;
    public String memStartDate;
    private JDBCDriver jdbcDriver = JDBCDriver.getInstance();

    public GuestUser(int ID){
        id = ID;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users where ");
        sb.append(id);
        String query = sb.toString();
        ResultSet result = jdbcDriver.executeDataQuery(query);
    }


}
