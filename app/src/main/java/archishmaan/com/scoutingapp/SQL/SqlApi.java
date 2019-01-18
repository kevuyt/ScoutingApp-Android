package archishmaan.com.scoutingapp.SQL;

import java.sql.*;
import archishmaan.com.scoutingapp.Models.ScoutingModel;

import static archishmaan.com.scoutingapp.Activities.MainActivity.scoutingModelDatabase;
import static java.sql.DriverManager.getConnection;

public class SqlApi {
    public static void createRow(ScoutingModel entry) {

       /* try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            String URL = "jdbc:mysql://scouting-app.cs9uuqx2q1fu.us-east-1.rds.amazonaws.com/Scouting_App";
            String username = "masterUsername";
            String password = "Reason99";
            Connection con = getConnection(URL, username, password);
            Statement stmt = con.createStatement();
            int autoDrop;
            if (entry.isAutoDrop()) {autoDrop = 1;}
            else {autoDrop = 0;}
            int sample;
            if (entry.isSample()) {sample = 2;}
            else {sample = 0;}
            int doubleSample;
            if (entry.isDoubleSample()) {doubleSample = 1;}
            else {doubleSample = 0;}
            int marker;
            if (entry.isMarker()) {marker = 1;}
            else {marker = 0;}
            int autoPark;
            if (entry.isAutoPark()) {autoPark = 1;}
            else {autoPark = 0;}
            int endHang;
            if (entry.isEndHang()) {endHang = 1;}
            else {endHang = 0;}
            int endPartPark;
            if (entry.isEndPartial()) {endPartPark = 1;}
            else {endPartPark = 0;}
            int endFullPark;
            if (entry.isFullPark()) {endFullPark = 1;}
            else {endFullPark = 0;}
            stmt.executeUpdate( "INSERT INTO scouting_app.matches " +
                    "VALUES (null, " +
                    entry.getTournament() + ", " +
                    String.valueOf(entry.getMatchNumber()) +
                    ", " + String.valueOf(entry.getTeamNumber()) +
                    ", " + String.valueOf(autoDrop) + ", " + String.valueOf(sample) +
                    ", " + String.valueOf(doubleSample) + ", " +
                    String.valueOf(marker) + ", " + String.valueOf(autoPark) +
                    ", " + String.valueOf(entry.getDepot()) + ", " + String.valueOf(entry.getLander()) +
                    ", " + String.valueOf(endHang) + ", " +
                    String.valueOf(endPartPark) + ", " +
                    String.valueOf(endFullPark));
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/



    }


    }

