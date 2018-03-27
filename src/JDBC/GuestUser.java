package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestUser {

    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public int phoneNum;
    public String memStartDate;
    private JDBCDriver jdbcDriver = JDBCDriver.getInstance();

    public GuestUser(int ID) throws SQLException {
        id = ID;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users");
        //sb.append(id);
        String query = sb.toString();
        ResultSet result = jdbcDriver.executeDataQuery(query);
        while(result.next()){
            try {
                id = result.getInt("id");
                firstName = result.getString("firstName");
                lastName = result.getString("lastName");
                email = result.getString("email");
                phoneNum = result.getInt("phone#");
                if(result.wasNull()){
                    phoneNum=-1;
                }
                memStartDate = result.getString("memStartDate");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public int getId() {
        return id;
    }
    public int getPhoneNum() {
        return phoneNum;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getMemStartDate() {
        return memStartDate;
    }
}
