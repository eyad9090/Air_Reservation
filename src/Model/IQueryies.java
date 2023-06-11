package Model;
import java.sql.SQLException;
/**
 * This Interface Just For Common Queries  [CRUD Operation]
 *
 * @author Anwar
 */
public interface IQueryies {
    /**
     * This Method To Insert in DataBase
     * @return True if Inserted and False If Not
     * @throws SQLException
     */
    boolean insert() throws SQLException;

    /**
     * This Method To Select From DataBase
     * @return Int [Number Of Rows Matched The Query]
     * @throws SQLException
     */
    int select() throws SQLException;

    /**
     * This Method To Update data in  DataBase
     * @return True if Updated  Successfully and False If Not
     * @throws SQLException
     */
    boolean update() throws SQLException;

    /**
     * his Method To Delete data From  DataBase
     * @return True if Deleted  Successfully and False If Not
     * @throws SQLException
     */
    boolean delete() throws SQLException;
}
