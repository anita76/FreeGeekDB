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

    public String updateData(String firstName, String lastName, String email, String phoneNum){
        JDBCDriver jdbcDriver = JDBCDriver.getInstance();
        String error=checkInputs(firstName,lastName,phoneNum);
        StringBuilder sb = new StringBuilder();
        if(error.equals("constraint satisfied")){
            sb.append("update users set firstName='");
            sb.append(firstName);
            sb.append("', lastName='");
            sb.append(lastName);
            sb.append("',email='");
            sb.append(email);
            sb.append("',phone='");
            sb.append(phoneNum);
            sb.append("' where id=");
            sb.append(this.getId());
        }else{
            return error;
        }
        String query = sb.toString();
        String outcome = jdbcDriver.executeDataUpdate(query);
        if(outcome.indexOf("value too large")>=0){
            return "input exceeds maximum size";
        }else if(outcome.indexOf("MAILCHCK")>=0) {
            return "invalid email address";
        }else if(outcome.indexOf("unique constraint")>=0){
            return "email address used with another account";
        }
        else if(outcome.indexOf("success")>=0) {
            return "success";
        }else{
            return "failed to add input";
        }
    }

    public String checkInputs(String firstName, String lastName, String phoneNum){
        if(!(firstName.matches("[a-zA-Z]+"))){
            return "Invalid firstName. Names should only include letters";
        }
        if(!(lastName.matches("[a-zA-Z]+"))){
            return "Invalid lastName. Names should only include letters";
        }
        try{
            long temp = Long.parseLong(phoneNum);
        }catch (Exception e){
            return "Invalid phone number";
        }
        return "constraint satisfied";

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
