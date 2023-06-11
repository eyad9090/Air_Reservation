package Model;
import Compenents.User;

import java.sql.*;
import java.util.ArrayList;
/**
 * This Class For User Queries --> [CRUD Operation]
 * @author Anwar
 */
public class UserQuery implements IQueryies{
    /**
     * @param connect To Call Connection Method of database
     */
    Connect connect;
    public UserQuery()
    {
        connect =new Connect();
    }
    @Override
    public boolean insert() {
        return true;
    }

    @Override
    public int select() {
        return 0;

    }

    /**
     * This Method To Show all Trips Belongs to a Specific User
     * @param TripId to indicate which trip you need to know info
     * @param UserId to indicate who the user
     * @return ArrayList Of All Resevation of this user and  this trip
     * @throws SQLException
     */
    public ArrayList<String> myReservation(int TripId,int UserId) throws SQLException{
        ArrayList<String> myreservations= new ArrayList<>();
        //System.out.println(TripId+" Anwer "+UserId);
        Connection c= connect.connection();
        String sql="select TripFrom,TripTo,TripDate,ReservationQnt from Resevation R inner join Trip T " +
                "on R.TripId=T.TripId where R.UserId="+UserId + " and R.TripId="+TripId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()) {
            String From=result.getString("TripFrom");
            String To=result.getString("TripTo");
            String Date=result.getString("TripDate");
            String reservationQnt=result.getInt("ReservationQnt")+"";
            myreservations.add(From);
            myreservations.add(To);
            myreservations.add(Date);
            myreservations.add(reservationQnt);
            //System.out.println(id);
        }
        //System.out.println(cnt);
        return myreservations;
    }


    @Override
    public boolean update() {

        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    /**
     * This Method Get User ID
     * @param user To Know info about User To Get his ID Like: his name , email
     * @return [int] --> User ID
     * @throws SQLException
     */
    public int selectUser(User user) throws SQLException{
        String user_email=user.getMail();
        String user_pass =user.getPassword();
        Connection c= connect.connection();
        //System.out.println(user_email+" "+user_pass);
        String sql="select UserId from Sys_user where UserMail='"+user_email +"' and UserPass='"+user_pass+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int id=-1;
        while(result.next()) {
            id =result.getInt("UserId");
        }
        return id;
    }

    /**
     * This Method Related To Sign up Info
     * @param user To Set Registered info he entered
     * @return True If this entered data valied and False if Not
     * @throws SQLException
     */
    public boolean insertUser(User user) throws SQLException {
        String user_name=user.getName();
        String user_email=user.getMail();
        String user_pass =user.getPassword();
        Connection c = connect.connection();
        String sql = "insert into Sys_user (UserName,UserMail,UserPass)" + "values(?,?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1, user_name);
        statement.setString(2, user_email);
        statement.setString(3, user_pass);
        int rows = statement.executeUpdate();
        c.close();
        return rows > 0;

    }

    /**
     * This Method to Know Number Of User Seats in a specific trip
     * @param uesrId to indicate who the user
     * @param TripId to indicate which trip you need to know info
     * @return [INT] --> Number Of User Seats in a specific trip
     * @throws SQLException
     */
    public int getMyNOSeats(int uesrId,int TripId)throws SQLException {
        Connection c= connect.connection();
        String sql="select ReservationQnt from Resevation R inner join Trip T " +
                "on R.TripId=T.TripId where R.UserId="+uesrId + " and R.TripId="+TripId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int reservationQnt=0;
        while(result.next()) {
            reservationQnt=result.getInt("ReservationQnt");
        }
        return reservationQnt;
    }

    /**
     * This Method to Know User's Resevation
     * @param UserId to indicate who the user
     * @return ArrayList of User's Resevation
     * @throws SQLException
     */
    public ArrayList<String> myReservations(int UserId) throws SQLException{
        ArrayList<String> myreservations= new ArrayList<>();
        Connection c= connect.connection();
        String sql="select TripFrom,TripTo,TripDate,ReservationQnt from Resevation R inner join Trip T " +
                "on R.TripId=T.TripId where R.UserId="+UserId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()) {
            String From=result.getString("TripFrom");
            String To=result.getString("TripTo");
            String Date=result.getString("TripDate");
            String reservationQnt=result.getInt("ReservationQnt")+"";
            myreservations.add(From);
            myreservations.add(To);
            myreservations.add(Date);
            myreservations.add(reservationQnt);
        }
        return myreservations;
    }

    /**
     * This Method to Know Trip's Seats
     * @param tripId to indicate which trip you need to know info
     * @return ArrayList of Trip's Seats
     * @throws SQLException
     */
    public ArrayList<String> myReservationSeats(int tripId) throws SQLException{
        ArrayList<String> myreservationSeats= new ArrayList<>();
        Connection c= connect.connection();
        String sql="select SeatNo from Seats where TripId="+tripId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next()) {
            String name=result.getString("SeatNo");
            myreservationSeats.add(name);
        }
        return myreservationSeats;
    }
    /**
     * This Method to Know User's Resevation's Seat
     * @param tripId to indicate which trip you need to know info
     * @param userId to indicate who the user
     * @return ArrayList of User's Resevation Seats
     * @throws SQLException
     */
    public ArrayList<String> myReservationSeats(int userId,int tripId) throws SQLException {
        ArrayList<String> myreservationSeats = new ArrayList<>();
        Connection c = connect.connection();
        String sql = "select SeatNo from Seats where TripId=" + tripId + "and UserId=" + userId;
        Statement statement = c.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            String name = result.getString("SeatNo");
            myreservationSeats.add(name);
        }
        return myreservationSeats;
    }

    /**
     * This Method to Cancel Booked Seat of Specific Trip and user
     * @param userId to indicate who the user will cancel resevation
     * @param tripId to indicate which trip you need to know info
     * @throws SQLException
     */
    public void cancelSeats(int userId, int tripId)throws SQLException {
        Connection c= connect.connection();
        String sql="DELETE FROM Seats where TripId="+tripId+" and UserId="+userId;
        Statement statement=c.createStatement();
        statement.executeUpdate(sql);
        c.close();
    }
    /**
     * This Method to Know Booked Seat of Specific Trip and user
     * @param userId to indicate who the user will cancel resevation
     * @param tripId to indicate which trip you need to know info
     * @return ArrayList of booked seats
     * @throws SQLException
     */
    public ArrayList<String> myNotReservationSeats(int userId,int tripId) throws SQLException {
        ArrayList<String> myreservationSeats = new ArrayList<>();
        Connection c = connect.connection();
        String sql = "select SeatNo from Seats where TripId=" + tripId + "and UserId!=" + userId;
        Statement statement = c.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            String name = result.getString("SeatNo");
            myreservationSeats.add(name);
        }
        return myreservationSeats;
    }

}
