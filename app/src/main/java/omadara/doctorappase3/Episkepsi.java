package omadara.doctorappase3;

/**
 * Created by plzdo on 22/1/2017.
 */

public class Episkepsi implements Availables {
    private String name;
    private String hour;
    private String date;
    private boolean reserved;
    public Episkepsi(String name, String hour, String date, boolean reserved) {
        super();
        this.name = name;
        this.hour = hour;
        this.date = date;
        this.reserved = reserved;
    }

    public void setName() { this.name = name; }
    public void setHour() {
        this.hour = hour;
    }
    public void setDate() {
        this.date = date;
    }
    public void setReservation() {
        this.reserved = reserved;
    }

    public String getName() {
        return name;
    }
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
