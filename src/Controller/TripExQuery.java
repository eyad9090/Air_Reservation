package Controller;
import Model.TirpQuery;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * This Class To Execute All Queries belongs to Trips
 * @author Anwar
 */

public class TripExQuery {
    TirpQuery tirpQuery=new TirpQuery();

    /**
     * This Method To Select [From] From DataBase
     * @return ArrayList of all from of all trips
     * @throws SQLException
     */
    public ArrayList<String> GetFrom() throws SQLException {
           return tirpQuery.selectFrom();
    }

    /**
     * This Method To Select [to] From DataBase
     * @param From to get all destinations belongs to from
     * @return ArrayList of all to of all trips
     * @throws SQLException
     */
    public ArrayList<String> GetTo(String From) throws SQLException {
        return tirpQuery.selectTo(From);
    }

    /**
     * This Method To Select [Date] From DataBase
     * @param From to determine source of the trip
     * @param To to determine destination of the trip
     * @return ArrayList of all Date of all trips
     * @throws SQLException
     */
    public ArrayList<String> GetDate(String From,String To) throws SQLException {
        return tirpQuery.selectDate(From,To);
    }

    /**
     * This Method To Get Trip Id
     * @param from to indicate source of trip
     * @param to to indicate destination of trip
     * @param date to indicate Date of trip
     * @return [int] --> id of the trip
     * @throws SQLException
     */
    public int getId(String from, String to, String date) throws SQLException{
        return tirpQuery.getId(from,to,date);
    }

    /**
     * This Method To Select From DataBase
     * @return ArrayList of all trips with all info about trip
     * @throws SQLException
     */
    public ArrayList<String> selectAll() throws SQLException{
        return tirpQuery.selectAll();
    }
}
