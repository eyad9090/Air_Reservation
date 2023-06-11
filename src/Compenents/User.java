package Compenents;
/**
 * @author Anwar
 */
public class User {
    private String name;
    private int UserId;
    private String password;
    private String email;
    private String confirmPass;
    Validate validate=new Validate();

    public User(){

    }
    public boolean setName(String name){
        boolean ok = validate.isValidName(name);
        if(ok) {
            this.name = name;
            return true;
        }
        return false;
    }
    public boolean setPassword(String password){
        boolean ok = validate.isValidPassword(password);
        if(ok) {
            this.password = password;
            return true;
        }
        return false;
    }
    public boolean setConfirmPassword(String confirmPass){
        boolean ok = validate.isValidConfirmPassword(confirmPass,this.password);
        if(ok) {
            this.confirmPass = confirmPass;
            return true;
        }
        return false;
    }
    public boolean setMail(String email){
         boolean ok=validate.isValidEmail(email);
         if(ok) {
             this.email = email;
             return true;
         }
        return false;
    }
    public String getName(){

        return  this.name;
    }
    public String getPassword(){

        return  this.password;
    }
    public String getMail(){

        return  this.email;
    }
    public int getId(){
        return  this.UserId;
    }
}
