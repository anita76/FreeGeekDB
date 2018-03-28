package JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestUser {

    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNum;
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
                phoneNum = result.getString("phone");
                created=true;
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }
    }

    public boolean updateData(String firstName, String lastName, String email, String phoneNum){
        JDBCDriver jdbcDriver = JDBCDriver.getInstance();
        StringBuilder sb = new StringBuilder();
        //todo
        return true;
    }

    public int getId() {
        return id;
    }
    public String getPhoneNum() {
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

    public boolean isCreated(){
        return created;
    }
}
