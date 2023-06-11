package Model;
import Compenents.Trip;
import java.sql.*;
import java.util.ArrayList;
/**
 * This Class For Trip Queries --> [CRUD Operation]
 * @author Anwar
 */
public class TirpQuery  implements IQueryies {
    /**
     * @param connect To Call Connection Method of database
     * @param trip To Call methods of set and get data from objects passed from Gui
     */
    Trip trip=new Trip();
    Connect connect;
    public TirpQuery(){
        connect=new Connect();
    }
    /**
     * This Method To insert New Trip and then update info database
     * @return True if Inserted and False If Not
     * @throws SQLException
     */
    @Override
    public boolean insert() throws SQLException {
        String from=trip.getFrom();
        String to =trip.getTo();
        String date=trip.getDate();
        Connection c = connect.connection();
        String sql = "insert into Trip (TripFrom,TripTo,TripDate)" + "values(?,?,?)";
        PreparedStatement statement=c.prepareStatement(sql);
        statement.setString(1, from);
        statement.setString(2, to);
        statement.setString(3, date);
        int rows = statement.executeUpdate();
        c.close();
        return rows > 0;
    }
    /**
     * This Method To Select From DataBase
     * @return Int [Number Of Rows Matched The Query]
     * @throws SQLException
     */
    @Override
    public int select() throws SQLException {
        Connection c= connect.connection();
        String sql="select tripFrom,TripTo,TripDate from Trip";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next())
        {
            result.getInt("id");
        }
        c.close();
        return 1;
    }

    /**
     * This Method To Select From DataBase
     * @return ArrayList of all trips with all info about trip
     * @throws SQLException
     */
    public ArrayList<String> selectAll() throws SQLException {
        ArrayList<String> row=new ArrayList<>();
        Connection c= connect.connection();
        String sql="select TripFrom,TripTo,TripDate,NoSeats from Trip";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next())
        {
            String from =result.getString("tripFrom");
            String to=result.getString("TripTo");
            String date=result.getString("TripDate");
            String seats=result.getInt("NoSeats")+"";
            row.add(from);
            row.add(to);
            row.add(date);
            row.add(seats);

        }
        c.close();
        return row;
    }
    /**
     * This Method To Select [From] From DataBase
     * @return ArrayList of all from of all trips
     * @throws SQLException
     */
    public ArrayList<String> selectFrom() throws SQLException {
        ArrayList<String> From = new ArrayList<>();
        Connection c= connect.connection();
        String sql="select distinct TripFrom from Trip";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next())
        {
            String res=result.getString("TripFrom");
            From.add(res);
        }
        c.close();
        return From;
    }
    /**
     * This Method To Select [to] From DataBase
     * @param from to get all destinations belongs to from
     * @return ArrayList of all to of all trips
     * @throws SQLException
     */
    public ArrayList<String> selectTo(String from) throws SQLException {
        ArrayList<String> To = new ArrayList<>();
        Connection c= connect.connection();
        String sql="select distinct TripTo from Trip where TripFrom='"+ from+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next())
        {
            String res=result.getString("TripTo");
            To.add(res);
        }
        c.close();
        return To;
    }
    /**
     * This Method To Select [Date] From DataBase
     * @param from to determine source of the trip
     * @param to to determine destination of the trip
     * @return ArrayList of all Date of all trips
     * @throws SQLException
     */
    public ArrayList<String> selectDate(String from,String to) throws SQLException {
        ArrayList<String> Date = new ArrayList<>();
        Connection c= connect.connection();
        String sql="select TripDate from Trip where TripFrom='"+from+"' and TripTo='"+to+"' order by TripDate";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        while(result.next())
        {
            String res =result.getString("TripDate");
             Date.add(res);
        }
        c.close();
        return Date;
    }

    /**
     * This Method To Get Trip Id
     * @param from to indicate source of trip
     * @param to to indicate destination of trip
     * @param date to indicate Date of trip
     * @return [int] --> id of the trip
     * @throws SQLException
     */
    public int getId(String from, String to, String date) throws SQLException {
        Connection c= connect.connection();
        String sql="select TripId from Trip where TripFrom='"+from+"' and TripTo='"+to+"' and TripDate='"+date+"'";
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int id=-1;
        while(result.next())
        {
            id =result.getInt("TripId");
        }
        c.close();
        return id;
    }

    /**
     * This Method To know Number of available seat
     * @param TripId to indicate which trip you need to know info
     * @return [int] --> Number of available seat
     * @throws SQLException
     */
    public int getNoSeats(int TripId) throws SQLException{
        Connection c=connect.connection();
        String sql = "select  NoSeats from Trip where TripId="+TripId;
        Statement statement=c.createStatement();
        ResultSet result=statement.executeQuery(sql);
        int NoSeats=0;
        while(result.next())
        {
            NoSeats =result.getInt("NoSeats");
        }

        return NoSeats;
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
     * This Function to remove number of booked seats
     * @param tripId to indicate which trip will modify
     * @param qnt to remove number of booked seats
     * @throws SQLException
     */
    public void updateNoSeatsBooked(int tripId,int qnt) throws SQLException{
        Connection c= connect.connection();
        String sql = "update Trip set NoSeats-=" + qnt + " where TripId=" + tripId ;
        Statement statement = c.createStatement();
        statement.executeUpdate(sql);
    }

    /**
     * This Function to add number of booked seats
     * @param tripId to indicate which trip will modify
     * @param reservationQnt to add number of booked seats
     * @throws SQLException
     */
    public void updateNoSeatsCanceld(int tripId, int reservationQnt) throws SQLException{
        Connection c= connect.connection();
        String sql = "update Trip set NoSeats+=" + reservationQnt + " where TripId=" + tripId ;
        Statement statement = c.createStatement();
        statement.executeUpdate(sql);
    }
}
