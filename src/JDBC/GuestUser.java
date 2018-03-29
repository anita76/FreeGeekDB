package JDBC;

import GUI.FreeGeekApp;

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

    public ResultSet getUserEvents(){
        StringBuilder sb = new StringBuilder();
        String temp = "select se.name, to_char(cast(se.eventDate as date) , 'DD-MM-YYYY') as EventDate, se.startTime, se.endTime, se.entranceFee" +
                " from specialEvents se, reserveEvents re"+
                " where se.eventDate = re.eventDate and se.startTime = re.eventStartTime and re.userID =";
        sb.append(temp);
        sb.append(FreeGeekApp.currentUser.getId());
        sb.append(" order by se.eventDate desc");
        temp = sb.toString();
        return jdbcDriver.executeDataQuery(temp);
    }

    public String updateAccountInfo(String firstName, String lastName, String email, String phoneNum){
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

    public void deleteAccount(){
        StringBuilder sb = new StringBuilder();
        sb.append("delete from users where id=");
        sb.append(this.id);
        String query = sb.toString();
        jdbcDriver.executeDataUpdate(query);
    }

    private String checkInputs(String firstName, String lastName, String phoneNum){
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
