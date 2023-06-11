package Compenents;
import java.util.List;
/**
 * @author Anwar
 */
public class Resevation {
    private int tripId;
    private int userId;
    private int seats;
    List<String>SeatsName;
    private String ResevationDate;
    public Resevation(){

    }

    public void setTripId(int tripId){
        this.tripId=tripId;

    }
    public void setUserId(int userId){
        this.userId=userId;

    }
    public void setResevationDate(String ResevationDate){
        this.ResevationDate=ResevationDate;

    }
    public int getTripId(){
         return this.tripId;
    }
    public int getUserId(){
        return this.userId;
    }


    public String getResevationDate() {
        return this.ResevationDate;
    }

    public void setNumberSeats(int seats) {
       this.seats=seats;

    }
    public int getNumberSeats() {
        return this.seats;

    }
    public void setSeatsnames(List<String>SeatsName) {
        this.SeatsName=SeatsName;

    }
    public List<String> getSeatsnames() {
        return this.SeatsName;

    }
}
