package omadara.doctorappase3;

/**
 * Created by plzdo on 22/1/2017.
 */

public class Episkepsi implements Availables {
    private String hour;
    private String date;
    private boolean reserved;
    public Episkepsi(String hour, String date, boolean reserved) {
        super();
        this.hour = hour;
        this.date = date;
        this.reserved = reserved;
    }

    public void setHour() {
        this.hour = hour;
    }
    public void setDate() {
        this.date = date;
    }

    public void setReservation() {
        this.reserved = reserved;
    }

    public String getHour() {
        return hour;
    }
    public String getDate() {
        return date;
    }

    public boolean getReservation() {
        return reserved;
    }
}
