package Controller;
import Compenents.Resevation;
import Model.ReservationQuery;
import Model.TirpQuery;
import Model.UserQuery;
import Viewer.BookingInformation;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This Class For execute Queries
 * @author Anwar
 */
public class ReservationExQuery {
    private final Resevation r=new Resevation();
    ReservationQuery reservationquery =new ReservationQuery();
    UserQuery userquery=new UserQuery();

    /**
     * This method is Synchronized method for booking trip and seats and it's Synchronized to more than User To access the same seat at a time
     * @param r Refer To the Resevation Details
     * @param bookingInformation Refer To the BookingInformation
     * @throws SQLException
     */
    public synchronized void bookTrip(Resevation r, BookingInformation bookingInformation) throws SQLException {
            ReservationQuery res=new ReservationQuery(r);
            boolean ok_insert=res.insert();
            if(ok_insert) {
                reservationquery.bookseat(r);
                bookingInformation.setMessage("Booked Successfully");
            }else {
                TirpQuery tirpquery = new TirpQuery();
                int avQnt = tirpquery.getNoSeats(this.r.getTripId());
                bookingInformation.setMessage("Unfortunately: There is  only " + avQnt + "Available Seats Now");
            }
    }

    /***
     * This Method To delete Reservation
     * @param userId to indicate who the user will delete the trip
     * @param tripId2 to indicate which trip will modified
     * @param Date to know the specific reservation
     * @param newqnt to change the QNT
     * @return True if Deleted  Successfully and False If Not
     * @throws SQLException
     */
    public boolean deleteReservation(int userId, int tripId2,String Date,int newqnt) throws SQLException{
        return reservationquery.deleteReservation(userId,tripId2,Date,newqnt);
    }

    /**
     * This Method For Update The Reservation
     * @param r is the resevation data passed from GUI
     * @param bookingInformation Refer To the BookingInformation
     * @return True if Updated Successfully and False If Not
     * @throws SQLException
     */
    public synchronized void updateReservation(Resevation r, BookingInformation bookingInformation) throws SQLException {
        bookingInformation.setOk(reservationquery.updateReservation(r));
    }

    /**
     * This Method to Know User's Resevation
     * @param userId to indicate who the user
     * @return ArrayList of User's Resevation
     * @throws SQLException
     */
    public ArrayList<String> myReservations(int userId) throws SQLException {
        return userquery.myReservations(userId);
    }

    /**
     * This Method to Know Trip's Seats
     * @param tripId to indicate which trip you need to know info
     * @return ArrayList of Trip's Seats
     * @throws SQLException
     */
    public ArrayList<String> ReservedSeats(int tripId) throws SQLException {
        return userquery.myReservationSeats(tripId);
    }

    /**
     * This Method to Know User's Resevation's Seat
     * @param userid to indicate which trip you need to know info
     * @param tripId to indicate who the user
     * @return ArrayList of User's Resevation Seats
     * @throws SQLException
     */
    public ArrayList<String> myReservationSeats(int userid,int tripId) throws SQLException {
        return userquery.myReservationSeats(userid,tripId);
    }

    /**
     * This Method to Know Booked Seat of Specific Trip and user
     * @param userId to indicate who the user will cancel resevation
     * @param tripId2 to indicate which trip you need to know info
     * @return ArrayList of booked seats
     * @throws SQLException
     */
    public ArrayList<String> myNotReservationSeats(int userId, int tripId2) throws SQLException {
        return userquery.myNotReservationSeats(userId,tripId2);
    }
}
