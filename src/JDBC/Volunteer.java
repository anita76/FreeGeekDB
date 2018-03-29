package JDBC;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public int getVolunteerHr() {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from volunteers v, volunteerShifts vs where v.id = vs.volunteerId and id = ");
        sb.append(id);
        sb.append( " and vs.ShiftDate < '");
        sb.append(localDate);
        sb.append("'");
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
