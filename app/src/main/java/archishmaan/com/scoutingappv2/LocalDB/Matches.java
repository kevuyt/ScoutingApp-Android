package archishmaan.com.scoutingappv2.LocalDB;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "matches")
public class Matches {

    @PrimaryKey
    private int id;

    private int  matchNumber;
    private int teamNumber;
    private int depot;
    private int lander;
    private boolean autoDrop;
    private boolean marker;
    private boolean autoPark;
    private boolean sample;
    private boolean doubleSample;
    private boolean endHang;
    private boolean endPartial;
    private boolean fullPark;
    private String tournament;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getDepot() {
        return depot;
    }

    public void setDepot(int depot) {
        this.depot = depot;
    }

    public int getLander() {
        return lander;
    }

    public void setLander(int lander) {
        this.lander = lander;
    }

    public boolean isAutoDrop() {
        return autoDrop;
    }

    public void setAutoDrop(boolean autoDrop) {
        this.autoDrop = autoDrop;
    }

    public boolean isMarker() {
        return marker;
    }

    public void setMarker(boolean marker) {
        this.marker = marker;
    }

    public boolean isAutoPark() {
        return autoPark;
    }

    public void setAutoPark(boolean autoPark) {
        this.autoPark = autoPark;
    }

    public boolean isSample() {
        return sample;
    }

    public void setSample(boolean sample) {
        this.sample = sample;
    }

    public boolean isDoubleSample() {
        return doubleSample;
    }

    public void setDoubleSample(boolean doubleSample) {
        this.doubleSample = doubleSample;
    }

    public boolean isEndHang() {
        return endHang;
    }

    public void setEndHang(boolean endHang) {
        this.endHang = endHang;
    }

    public boolean isEndPartial() {
        return endPartial;
    }

    public void setEndPartial(boolean endPartial) {
        this.endPartial = endPartial;
    }

    public boolean isFullPark() {
        return fullPark;
    }

    public void setFullPark(boolean fullPark) {
        this.fullPark = fullPark;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }
}
