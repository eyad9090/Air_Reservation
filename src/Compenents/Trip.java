package Compenents;
/**
 * @author Anwar
 */
public class Trip {
    private int numOfSeats;
    private String from;
    private String to;
    private String date;
    public Trip(){

    }
    public int getSeats(){
         return this.numOfSeats;
    }
    public String getFrom(){
        return this.from;
    }
    public String getTo(){
        return this.to;
    }
    public String getDate(){
        return this.date;
    }

}
