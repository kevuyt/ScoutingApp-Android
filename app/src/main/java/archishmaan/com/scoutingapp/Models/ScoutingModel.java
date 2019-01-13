package archishmaan.com.scoutingapp.Models;

public class ScoutingModel {
    private int matchNumber, teamNumber, depot, lander;
    private boolean autoDrop, marker, autoPark, sample, doubleSample, endHang, endPartial, fullPark;
    private String tournament;

    public ScoutingModel(String tournament, int matchNumber, int teamNumber, int depot, int lander, boolean autoDrop, boolean marker,
                         boolean autoPark, boolean sample, boolean doubleSample, boolean endHang, boolean endPartial, boolean fullPark) {
        this.tournament = tournament;
        this.matchNumber = matchNumber;
        this.teamNumber = teamNumber;
        this.depot = depot;
        this.lander = lander;
        this.autoDrop = autoDrop;
        this.marker = marker;
        this.autoPark = autoPark;
        this.sample = sample;
        this.doubleSample =  doubleSample;
        this.endHang = endHang;
        this.endPartial = endPartial;
        this.fullPark = fullPark;
    }


    public String getTournament() {return tournament;}

    public void setTournament(String tournament) {this.tournament = tournament;}

    public int getMatchNumber() {return matchNumber;}

    public void setMatchNumber(int matchNumber) { this.matchNumber = matchNumber; }

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

    public boolean isDoubleSample() {return doubleSample;}

    public void setDoubleSample(boolean doubleSample) {this.doubleSample = doubleSample;}

    public boolean isEndHang() {return endHang;}

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
}
