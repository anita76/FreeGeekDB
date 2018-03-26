package JDBC;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by 37919 on 2018/3/25.
 */
public class Employer extends GuestUser {
    public Employer(int ID){
        super(ID);
    }

    // functionalities 2
    Time findFreeVOTime(){
        //TODO
        return null;
    }

    // return true when successfully insert
    boolean addVO(Date date, Time startTime, Time endTime, int instructID){
        try{
            //TODO
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //4)
    List<Date> findUpComingVO(Date now){
        return null;
    }

    // 4)
    int num_peopleVO(Date VOdate, Time VOstartTime){
        return 0;
    }

    //5
    List<String> superviseInfo(List<String> wantedInfo){
        return null;
    }

    //6
    List<String> allVStoTrain(){
        //Todo
        return null;
    }

    //7
    List<String> allVSsigned(){
        return null;
    }

    //8
    List<Integer> allVolunteersDiffVSType(){
        return null;
    }

    //9
    int minAttend(){
        return 0;
    }

    int maxAttend(){
        return 0;
    }

    int averageAttend(){
        return 0;
    }

    List<String> leastPopularEvent(){
        return null;
    }

    List<String> mostPopularEvent(){
        return null;
    }

    //10
    int totalNumVolunHours(String shiftTypes){
        if(shiftTypes == null){
            // no specific shiftType
            return 1;
        }
        // with specific shiftType
        return 0;
    }

    int totalNumPeople(String shiftTypes){
        //find distinct people num
        if(shiftTypes == null){
            //no specific shiftType
            return 1;
        }
        //with specific shiftType
        return 0;
    }




}
