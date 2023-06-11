package Model;
import Compenents.Resevation;
import java.sql.*;
/**
 * This Class For Resevation Queries --> [CRUD Operation]
 *
 * @author Anwar
 */
public class ReservationQuery implements IQueryies {
    /**
     * @param connect To Call Connection Method of database
     * @param reservation To Call methods of set and get data from objects passed from Gui
     * @param tirpQuery To Access Queries of Trip and Update Data according to the transaction
     * @param userquery To Access Queries of User and Update Data according to the transaction
     */
    Connect connect;
    Resevation reservation=new Resevation();
    TirpQuery tirpQuery=new TirpQuery();
    UserQuery userquery=new UserQuery();
    public ReservationQuery(){

        connect = new Connect();
    }
    public ReservationQuery(Resevation reservation){
        this.reservation=reservation;
        connect = new Connect();
    }

    /**
     * This Method To insert New Reservation TO the User and then update info database
     * @return True if Inserted and False If Not
     * @throws SQLException
     */
    @Override
    public boolean insert() throws SQLException {
        int userId=reservation.getUserId();
        int TripId=reservation.getTripId();
        int ReservationQnt=reservation.getNumberSeats();
        String resevationDate=reservation.getResevationDate();
        Connection c=connect.connection();
        if(ReservationQnt<=tirpQuery.getNoSeats(TripId) && ReservationQnt>0) {
            String sql = "insert into Resevation (TripId,UserId,ResevationDate,ReservationQnt)" + "values(?,?,?,?)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, TripId);
            statement.setInt(2, userId);
            statement.setString(3, resevationDate);
            statement.setInt(4, ReservationQnt);
            int rows = statement.executeUpdate();
            c.close();
            if (rows > 0) {
                tirpQuery.updateNoSeatsBooked(TripId, ReservationQnt);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int select() {
        return 1;
    }

    @Override
    public boolean update() {
        return false;
    }

    /**
     * This Method For Update The Reservation
     * @param r is the resevation data passed from GUI
     * @return True if Updated  Successfully and False If Not
     * @throws SQLException
     */
    public boolean updateReservation(Resevation r) throws SQLException {
        Connection c= connect.connection();
        int MyNOSeats=userquery.getMyNOSeats(r.getUserId(),r.getTripId());
        userquery.cancelSeats(r.getUserId(),r.getTripId());
        tirpQuery.updateNoSeatsBooked(r.getTripId(), r.getNumberSeats());
        tirpQuery.updateNoSeatsCanceld(r.getTripId(), MyNOSeats);
        updateNoSeats(r.getTripId(),r.getUserId(),r.getResevationDate(),r.getNumberSeats());
        for(String seat:r.getSeatsnames()) {
            String sql = "insert into Seats (TripId,UserId,SeatNo)" + "values(?,?,?)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, r.getTripId());
            statement.setInt(2, r.getUserId());
            statement.setString(3, seat);
            statement.executeUpdate();
        }
        c.close();
        return true;
    }

    /**
     * This Method to Update the number of seats after cancel/update the resevation
     * @param tripId to indicate which trip will modified
     * @param userId to indicate who the user will modify the trip
     * @param newDate to change the date
     * @param newQnt to change the QNT
     * @throws SQLException
     */
    private void updateNoSeats(int tripId, int userId, String newDate,int newQnt) throws SQLException{
        Connection c= connect.connection();
        String sql = "update Resevation set ReservationQnt=" + newQnt + " where TripId=" + tripId +" and UserId="+userId+" and ResevationDate='" + newDate + "'";
        Statement statement = c.createStatement();
        statement.executeUpdate(sql);

    }

    @Override
    public boolean delete() {
        return  false;
    }

    /**
     * This Method To delete Reservation
     * @param userId to indicate who the user will delete the trip
     * @param TripId to indicate which trip will modified
     * @param Date to know the specific trip
     * @param newqnt to change the QNT
     * @return True if Deleted  Successfully and False If Not
     * @throws SQLException
     */
    public boolean deleteReservation(int userId, int TripId,String Date,int newqnt) throws SQLException {
        Connection c= connect.connection();
        int ReservationQnt=userquery.getMyNOSeats(userId,TripId);
        String sql="DELETE FROM Resevation where TripId="+TripId+" and UserId="+userId+" and ResevationDate='"+Date+"'";
        Statement statement=c.createStatement();
        int rows=statement.executeUpdate(sql);
        System.out.println("Rows = "+rows);
        c.close();
        if(rows>0)
        {
            userquery.cancelSeats(userId,TripId);
            tirpQuery.updateNoSeatsCanceld(TripId, ReservationQnt);
            return true;
        }
        return  false;
    }

    /**
     * this Method to mark this seat to the user
     * @param reservation to get info about the trip's seat belongs to
     * @throws SQLException
     */
    public void bookseat(Resevation reservation) throws SQLException {
        Connection c = connect.connection();
        for(String SeatNo:reservation.getSeatsnames()) {
            String sql = "insert into Seats (TripId,UserId,SeatNo)" + "values(?,?,?)";
            PreparedStatement statement = c.prepareStatement(sql);
            statement.setInt(1, reservation.getTripId());
            statement.setInt(2, reservation.getUserId());
            statement.setString(3, SeatNo);
            int rows = statement.executeUpdate();
            System.out.println("book seat rows "+rows);
        }
        c.close();
    }

}
