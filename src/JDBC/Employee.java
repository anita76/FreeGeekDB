package JDBC;

import javafx.util.Pair;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by 37919 on 2018/3/25.
 */
public class Employee extends GuestUser {
    private JDBCDriver jdbcDriver = JDBCDriver.getInstance();
    public Employee(int ID)throws SQLException {
        super(ID);
    }

    // select a day and return all free start time blocks(1 hour duration)
   /* List<String> findFreeVOTime(Date Date) throws SQLException {
        String dateString = Date.toString();
        List<Time> startTimes = new ArrayList<Time>();
        List<Time> endTimes = new ArrayList<Time>();
        List<String> freeTime = new ArrayList<>();
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT startTIME, endTIME"+"FROM VolunteerOrientation_TaughtBy"+"WHERE date="+dateString);
        while (rs.next()){
            startTimes.add(rs.getTime(1));
            endTimes.add(rs.getTime(2));
        }
        for (int hour = 0; hour <= 23; hour++){
            int all = 1;
            for(int range = 0; range < startTimes.size(); range++){
                Time currentTime = new Time(hour, 0, 0);
                if (currentTime.getTime() >= startTimes.get(range).getTime() && currentTime.getTime() <= endTimes.get(range).getTime()) all = 0;
            }
            if (all == 1) freeTime.add(Integer.toString(hour)+":00:00");
        }
        return freeTime;
    }

    // return true when successfully insert
    boolean addVO(Date date, Time startTime, Time endTime, int instructID){
        try{
            jdbcDriver.executeDataUpdate("INSERT INTO VolunteerOrientation_TaughtBy VALUES (" + date +","+ startTime +","+ endTime +","+instructID + ")");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //4)
    List<String> findUpComingVO() throws SQLException {
        long now = System.currentTimeMillis();
        List<String> upComingVO = new ArrayList<>();
        java.sql.Date nowDate = new java.sql.Date(now);
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT date, startTIME"+"FROM VolunteerOrientation_TaughtBy"+"WHERE instructorID ="+id+"AND date >=" + nowDate);

        while (rs.next()){
            upComingVO.add("Date: " + rs.getString(1) + ", Time: " + rs.getString(2));
        }
        return upComingVO;
    }*/


    //5    query checked
    List<String> superviseInfo(List<String> wantedInfo) throws SQLException {
        List<List> allData = new ArrayList<List>(); // infoList 1, infoList 2, infoList 3
        List<String> ans = new ArrayList<String>();
        for(String info: wantedInfo){ //Each info is a list
            List<String> outData = new ArrayList<String>();
            ResultSet rs = jdbcDriver.executeDataQuery("SELECT distinct"+ info + "FROM Employees e, users u"+"WHERE e.supervisorID ="+id+"AND e.ID = u.ID");
            while (rs.next()){
                outData.add(rs.getString(1));
            }
            allData.add(outData);
        }
        for(int i = 0; i < allData.get(0).size(); i++){ //get pairs of info
            String outInfo = "";
            for(List<String> s: allData){
                outInfo += " "+ s.get(i);
            }
            ans.add(outInfo);
        }
        return ans;
    }

    //6
    /*List<String> allVStoTrain() throws SQLException {
        List<String> ans = new ArrayList<>();
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT station, shiftDate, morningShift, shiftType FROM VolunteerShift WHERE instructEmpID is NULL AND trainingReq = 'yes'");
        while (rs.next()){
            ans.add("station: "+rs.getString(1)+" shiftDate: " +rs.getString(2)+" morningShift: " +rs.getString(3)+" shiftType: " +rs.getString(4));
        }
        return ans;
    }

    //7
    List<String> allVSsigned() throws SQLException {
        List<String> ans = new ArrayList<>();
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT station, shiftDate, morningShift, shiftType FROM VolunteerShift WHERE instructEmpID ="+id);
        while (rs.next()){
            ans.add("station: "+rs.getString(1)+" shiftDate: " +rs.getString(2)+" morningShift: " +rs.getString(3)+" shiftType: " +rs.getString(4));
        }
        return ans;
    }*/

    //8
    List<String> allVolunteersDiffVSType() throws SQLException {
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT distinct u.firstName, u.lastName FROM volunteers v, users u, VOLUNTEERSHIFTS vs WHERE v.ID = u.ID and vs.VOLUNTEERID = v.ID AND NOT EXISTS ((SELECT shiftType FROM volunteerShifts vs1) minus  (SELECT shiftType FROM volunteerShifts vs2 WHERE vs2.volunteerID = v.ID))");
        List<String> ans = new ArrayList<String>();
        while (rs.next()){
            ans.add(rs.getString(1)+ " " + rs.getString(2));
        }
        return ans;
    }

    //9   find min attend events per day instead of year
    List<Pair<String, Integer>> minAttend() throws SQLException {
        List<Pair<String, Integer>> ans = new ArrayList<Pair<String, Integer>>();
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT se.name, COUNT(*) FROM SpecialEvent se, ReserveEvent re WHERE se.Date = re.eventDate AND se.startTIME = re.eventStartTIME GROUP BY se.name, se.Date");
        while (rs.next()){
            ans.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
        }
        List<Pair<String, Integer>> out = new ArrayList<Pair<String, Integer>>();
        for (Pair<String, Integer> p: ans){
            int all = 1;
            for(Pair<String, Integer> q: ans){
                if(p.getValue()>q.getValue()) all = 0;
            }
            if(all ==1) out.add(p);
        }
        return out;
    }

    List<Pair<String, Integer>> maxAttend() throws SQLException {
        List<Pair<String, Integer>> ans = new ArrayList<Pair<String, Integer>>();
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT se.name, COUNT(*) FROM SpecialEvent se, ReserveEvent re WHERE se.Date = re.eventDate AND se.startTIME = re.eventStartTIME GROUP BY se.name, se.Date");
        while (rs.next()){
            ans.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
        }
        List<Pair<String, Integer>> out = new ArrayList<Pair<String, Integer>>();
        for (Pair<String, Integer> p: ans){
            int all = 1;
            for(Pair<String, Integer> q: ans){
                if(p.getValue()<q.getValue()) all = 0;
            }
            if(all ==1) out.add(p);
        }
        return out;
    }

    List<Pair<String, Integer>> averageAttend() throws SQLException {
        List<Pair<String, Integer>> ans = new ArrayList<Pair<String, Integer>>();
        String aggregation = "SELECT se.name As sename, COUNT(*) AS count FROM SpecialEvent se, ReserveEvent re WHERE se.Date = re.eventDate AND se.startTIME = re.eventStartTIME GROUP BY se.name, se.Date";
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT TEMP.sename, AVG(TEMP.count) FROM ("+aggregation+") AS TEMP");
        while (rs.next()){
            ans.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
        }
        return ans;
    }

    List<Pair<String,Integer>> leastPopularEvent() throws SQLException {
        String aggregation = "SELECT se.name AS sename, COUNT(*) AS count FROM SpecialEvent se, ReserveEvent re WHERE se.Date = re.eventDate AND se.startTIME = re.eventStartTIME GROUP BY se.name, se.Date";
        String nested = "SELECT TEMP.sename AS name, AVG(TEMP.count) AS avg FROM ("+aggregation+") AS TEMP";
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT TEMP1.name, MIN(TEMP1.avg) FROM ("+nested+") AS TEMP1");
        List<Pair<String, Integer>> out = new ArrayList<Pair<String, Integer>>();
        while (rs.next()){
            out.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
        }
        return out;
    }

    List<Pair<String,Integer>> mostPopularEvent() throws SQLException {
        String aggregation = "SELECT se.name AS sename, COUNT(*) AS count FROM SpecialEvent se, ReserveEvent re WHERE se.Date = re.eventDate AND se.startTIME = re.eventStartTIME GROUP BY se.name, se.Date";
        String nested = "SELECT TEMP.sename AS name, AVG(TEMP.count) AS avg FROM ("+aggregation+") AS TEMP";
        ResultSet rs = jdbcDriver.executeDataQuery("SELECT TEMP1.name, MAX(TEMP1.avg) FROM ("+nested+") AS TEMP1");
        List<Pair<String, Integer>> out = new ArrayList<Pair<String, Integer>>();
        while (rs.next()){
            out.add(new Pair<String, Integer>(rs.getString(1), rs.getInt(2)));
        }
        return out;
    }

    //10
     /*List<Pair<String, Integer>>totalNumVolunTimes(String year) throws SQLException {
        List<Pair<String, Integer>> ans = new ArrayList<>();
        String select = "SELECT users.firstName, users.lastName, temp.count";
        String from = "FROM (SELECT ID_Volunteer AS id, COUNT(*) as count FROM Volunteer_Attends AS va, VolunteerShift AS vs WHERE va.ID_Volunteer = vs.volunteerID AND vs.shiftDate LIKE '" + year +"%' GROUP BY va.ID_Volunteer) AS temp, users";
        String where = "temp.id = users.ID";
        ResultSet rs = jdbcDriver.executeDataQuery(select+from+where);
        while (rs.next()){
            String name = rs.getString(2) + "," + rs.getString(1);
            ans.add(new Pair<String, Integer>(name, rs.getInt(3)));
        }
        return ans;
    }*/

}
