package Compenents;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Anwar
 */
public class Validate {

    Validate(){

    }
    public boolean isValidName(String name){
        String regexPatternName = "^[a-zA-Z]{3,18}[0-9]{0,10}";
        Pattern pattern = Pattern.compile(regexPatternName);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean isValidEmail(String emailAddress){
        String regexPatternMail = "^(.+)@(\\S+)$";
        return Pattern.compile(regexPatternMail)
                .matcher(emailAddress)
                .matches();
    }
    public boolean isValidPassword(String password){
        String regexPatternPassword = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regexPatternPassword);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public boolean isValidConfirmPassword(String password1,String password2){
        return password1.equals(password2);
    }


}
