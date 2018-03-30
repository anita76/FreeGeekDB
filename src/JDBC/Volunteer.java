package JDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Volunteer extends GuestUser {
    private int volunteerHr;
    private JDBCDriver jdbcDriver = JDBCDriver.getInstance();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.now();

    public Volunteer(int ID){
        super(ID);
    }

    public ResultSet getFullSpots(String from, String to) {

        String query = "select to_char(cast(shiftDate as date) , 'DD-MM-YYYY') as ShiftDate, morningShift, shiftType from volunteerShifts where shiftDate <= ? and shiftDate >= ?";
        try {
            PreparedStatement prs = JDBCDriver.con.prepareStatement(query);
            prs.setDate(1,Date.valueOf(to));
            prs.setDate(2, Date.valueOf(from));
            return prs.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public int getVolunteerHr() {
        String query = "select count(*) from volunteers v, volunteerShifts vs where v.id = vs.volunteerId and id = ? and vs.ShiftDate < ?";
        try {
            PreparedStatement prs = JDBCDriver.con.prepareStatement(query);
            prs.setInt(1,id);
            prs.setDate(2, Date.valueOf(localDate));
            ResultSet rs = prs.executeQuery();
            boolean next = false;
            try {
                next = rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(next){
                try {
                    return rs.getInt(1);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
