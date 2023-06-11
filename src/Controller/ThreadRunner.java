package Controller;
import Compenents.Resevation;
import Viewer.BookingInformation;

import java.sql.SQLException;

/**
 * @author Anwar
 */
public class ThreadRunner implements Runnable{
    private String Type;
    Resevation r=new Resevation();
    public BookingInformation b;
    ReservationExQuery reservationexquery=new ReservationExQuery();

    public ThreadRunner(BookingInformation r, String Type){
            b=r;
            this.r.setTripId(r.getTripId());
            this.r.setUserId(r.getUserId());
            this.r.setNumberSeats(r.getSeats());
            this.r.setResevationDate(r.getReservationDate());
            this.r.setSeatsnames(r.getSeatsName());
            DefineType(Type);
    }
    public synchronized void DefineType(String Type) { /// book - update
         this.Type=Type;
    }
    @Override
    public void run() {
          switch (Type){
              case "insert":
                  try {
                      reservationexquery.bookTrip(this.r,this.b);
                  } catch (Exception e) {
                      throw new RuntimeException(e);
                  }
              break;
              case "update": {
                  try {
                      reservationexquery.updateReservation(this.r,this.b);
                  } catch (SQLException e) {
                      throw new RuntimeException(e);
                  }
                  break;
              }
              case "delete": {
              }
              case "log": {
              }

          }
    }
}
