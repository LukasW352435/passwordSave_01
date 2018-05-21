package Fields;

public class User extends Field {

    private String userName;

    public User(String showName, String userName){
        super("Benutzername");
        this.userName = userName;
    }

    // setter

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // getter

    public String getUserName() {
        return userName;
    }
}
