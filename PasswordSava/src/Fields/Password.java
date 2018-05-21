package Fields;

public class Password extends Field {

    // says if you can see the password or just stars
    boolean visible = false;

    String password = "";

    public Password(){
        super("Passwort");
    }

    public Password(String password) {
        super("Passwort");
        this.password = password;
    }

    public void generatePassword(){
        // we need the password generator from Save to generate a password
    }

    // setter

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void setPassword(String password){
        this.password = password;
    }
    // getter

    public boolean getVisible() {
        return visible;
    }
    public String getPassword(){
        if(visible){
            return password;
        }else {
            String ret = "";
            for(int i = 0; i<password.length();i++){
                ret += "*";
            }
            return ret;
        }
    }
}
