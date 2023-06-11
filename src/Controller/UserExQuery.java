package Controller;
import Compenents.User;
import Model.UserQuery;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * This Class To Execute All Queries belongs to Trips
 * @author Anwar
 */
public class UserExQuery {
    private UserQuery userquery=new UserQuery();
    private User user=new User();
    /**
     * This Method To Show all Trips Belongs to a Specific User
     * @param tripId to indicate which trip you need to know info
     * @param userId to indicate who the user
     * @return ArrayList Of All Resevation of this user and  this trip
     * @throws SQLException
     */
    public ArrayList<String> myReservation(int tripId, int userId) throws SQLException {
        return userquery.myReservation(tripId,userId);

    }

    /**
     * This Method to Validate This User Exist Or Not and If Exist Return ID
     * @param email Indicate User Mail
     * @param password Indicate User Password
     * @return ID if Exist
     * @throws SQLException
     */
    public String logIn(String email, String password) throws SQLException{
        boolean ok1=user.setMail(email);
        if(!ok1) return "Enter Your E-mail Correct Please";
        boolean ok2=user.setPassword(password);
        if(!ok2) return "Enter Your Password Correct Please";
        int ok3=userquery.selectUser(user);
        if(ok3==-1) return "This User not Sign Up Before ";
        return ok3+"";
    }

    /**
     * This Method Validates info User entered and if this info valid set user in database
     * @param username specify user's name
     * @param userMail specify user's Mail
     * @param password1 specify user's Password
     * @param password2 specify user's Confirm Password
     * @return MSG either Sign Up Successfully or This User already signed up
     * @throws SQLException
     */
    public String SignUp(String username, String userMail, String password1, String password2) throws SQLException {
        boolean ok1=user.setName(username);
        if(!ok1)return "Enter Only Characters or Characters followed by numbers Like: anwer or anwer2022 .";
        boolean ok2=user.setMail(userMail);
        if(!ok2)return "Enter Valid an e-mail Like: anwer390@gmail.com";
        boolean ok3=user.setPassword(password1);
        if(!ok3)return "Password Must has one lower/upper/numbers/[@ # $ %] and its length more than 7 .";
        boolean ok4=user.setConfirmPassword(password2);
        if(!ok4)return "Password and Confirmed Password Should be same .";
        boolean ok5=userquery.insertUser(user);
        if(!ok5) return "This User already signed up";
        return "Sign Up Successfully";

    }

    /**
     * This Method Get User ID
     * @param userMail specify user's name
     * @param password1 specify user's Mail
     * @return ID of User
     * @throws SQLException
     */
    public int selectUser(String userMail, String password1) throws SQLException {
        user.setMail(userMail);
        user.setPassword(password1);
        return userquery.selectUser(user);
    }
}
