package Viewer;

import java.util.List;

public class BookingInformation {
    private int tripId;
    private int userId;
    private int seats;
    List<String> seatsName;
    private String reservationDate;
    private String message;


    private boolean ok;


    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }


    public BookingInformation()
    {

    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<String> getSeatsName() {
        return seatsName;
    }

    public void setSeatsName(List<String> seatsName) {
        this.seatsName = seatsName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

}
