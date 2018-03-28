package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestUser {

    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public long phoneNum;
    public String memStartDate;
    private JDBCDriver jdbcDriver = JDBCDriver.getInstance();
    private boolean created= false;

    public GuestUser(int ID){
        id = ID;
        StringBuilder sb = new StringBuilder();
        sb.append("select * from users where id=");
        sb.append(id);
        String query = sb.toString();
        ResultSet result = jdbcDriver.executeDataQuery(query);

        boolean next=false;
        try {
            next = result.next();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        if(next){
            try {
                id = result.getInt("id");
                firstName = result.getString("firstName");
                lastName = result.getString("lastName");
                email = result.getString("email");
                phoneNum = result.getLong("phone#");
                if(result.wasNull()){
                    phoneNum=-1;
                }
                memStartDate = result.getString("membershipStartData");
                created=true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }
    }

    public int getId() {
        return id;
    }
    public long getPhoneNum() {
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

    public boolean isCreated(){
        return created;
    }
}
