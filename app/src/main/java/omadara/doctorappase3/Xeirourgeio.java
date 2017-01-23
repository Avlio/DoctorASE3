package omadara.doctorappase3;

/**
 * Created by plzdo on 22/1/2017.
 */

public class Xeirourgeio implements Availables{
    private String name;
    private String hour;
    private String date;
    private boolean reserved;
    private String difficulty;
    public Xeirourgeio(String name, String hour, String date, boolean reserved, String difficulty) {
        super();
        this.name = name;
        this.hour = hour;
        this.date = date;
        this.reserved = reserved;
        this.difficulty = difficulty;
    }

    public void setHour() {
        this.hour = hour;
    }
    public void setDate() {
        this.date = date;
    }
    public void setName() { this.name = name; }
    public void setReservation() {
        this.reserved = reserved;
    }
    public void setDifficulty() {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public String getName() { return name; }
    public String getHour() {
        return hour;
    }
    public String getDate() {
        return date;
    }

    public Boolean getReservation() {
        return reserved;
    }
}
