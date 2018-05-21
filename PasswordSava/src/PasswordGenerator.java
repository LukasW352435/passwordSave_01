import java.util.LinkedList;
import java.util.Random;

public final class PasswordGenerator {

    private final String a = "abcdefghijklmnopqrstuvwxyz";
    private final String A = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String n = "1234567890";
    private final String uU = "äöüÄÖÜ";
    private final String s = "^°!\"²§³$%&/{([)]=}?\\´`@€+*~#'<>|,;.:-_";

    private String a_notToUse;
    private String A_notToUse;
    private String n_notToUse;
    private String uU_notToUse;
    private String s_notToUse;

    private String notToUse;

    public PasswordGenerator(){
        notToUse = "";
        updatenotToUse();
    }

    public PasswordGenerator(String notToUse){
        this.notToUse = notToUse;
        updatenotToUse();
    }

    public String generate(int length){
        String allSymbols = a_notToUse + A_notToUse + n_notToUse + uU_notToUse + s_notToUse;
        if(!allSymbols.equals("")) {
            String password = "";
            Random r = new Random();
            for (int i = 0; i < length; i++) {
                password += allSymbols.charAt(r.nextInt(allSymbols.length()));
            }
            return password;
        }else {
            return "";
        }
    }

    public String generatePin(int length){
        String pin = "";
        Random r = new Random();
        for(int i = 0; i < length;i++){
            pin += n.charAt(r.nextInt(n.length()));
        }
        return pin;
    }

    public String generate(int length,boolean a,boolean A,boolean n,boolean uU,boolean s){
        // this generater is good if you need a password with at least on number and or one big letter etc.
        // if you get a boolean ( a == true) there is at least on char out of that String ( at least on smale letter, with a == true)

        // check if there is at least one boolean true
        if(!(a || A || n || uU || s)){
            // there is no password witch could be created
            return "";
        }

        // check if there is one of the booleans true but no chars in notToUse
        if(a && a_notToUse.equals("")){
            // we need a letter out of a_notToUse, but it is emty
            return "";
        }else if(A && A_notToUse.equals("")){
            return "";
        }else if(n && n_notToUse.equals("")){
            return "";
        }else if(uU && uU_notToUse.equals("")){
            return "";
        }else if(s && s_notToUse.equals("")){
            return "";
        }

        // there is at least one letter in each notToUse

        // check if we can create a password with every char in it
        int booleanTest = 0;
        if(a) {booleanTest++;}
        if(A) {booleanTest++;}
        if(n) {booleanTest++;}
        if(uU) {booleanTest++;}
        if(s) {booleanTest++;}
        if(booleanTest > length){
            // we are not able to create a password, because the lenght is to small
            return "";
        }

        // we are abel to create a password

        // create Strings with all chars which are needed to create a password
        String a_special = "";
        String A_special = "";
        String n_special = "";
        String uU_special = "";
        String s_special = "";
        if(a){
            a_special = a_notToUse;
        }
        if(A){
            A_special = A_notToUse;
        }
        if(n){
            n_special = n_notToUse;
        }
        if(uU){
            uU_special = uU_notToUse;
        }
        if(s){
            s_special = s_notToUse;
        }
        // get a char out of each String, this char will be in the password ( so the password has definitly from every String we set to true)
        String saveChar = "";
        Random r = new Random();
        if(a){
            saveChar += a_special.charAt(r.nextInt(a_special.length()));
        }
        if(A){
            saveChar += A_special.charAt(r.nextInt(A_special.length()));
        }
        if(n){
            saveChar += n_special.charAt(r.nextInt(n_special.length()));
        }
        if(uU){
            saveChar += uU_special.charAt(r.nextInt(uU_special.length()));
        }
        if(s){
            saveChar += s_special.charAt(r.nextInt(s_special.length()));
        }

        // create a random password

        String password = "";
        String allSymbols = a_special + A_special + n_special + uU_special + s_special;
        if(!allSymbols.equals("")) {
            for (int i = 0; i < length; i++) {
                password += allSymbols.charAt(r.nextInt(allSymbols.length()));
            }
        }else {
            return "";
        }
        // replace random places with chars out of the String saveChar

        // creat a int[] with every possible index
        // take one random index, clear it and replace password at that index with one char out of saveChar

        LinkedList<Integer> index = new LinkedList<Integer>();
        for(int i = 0; i< length; i++){
            index.add(i);
        }

        int randomIndex = 0;
        int save_random = 0;
        for(int i = 0; i<saveChar.length();i++){
            // get a index that is smaler than the size of the list
            randomIndex = r.nextInt(index.size());
            save_random = randomIndex;
            randomIndex = index.get(save_random);
            index.remove(save_random);
            if(randomIndex == password.length()){
                password = password.substring(0,randomIndex) + saveChar.charAt(i);
            }else {
                password = password.substring(0, randomIndex) + saveChar.charAt(i) + password.substring(randomIndex + 1);
            }
        }
        // clear the list
        index.clear();

        return password;
    }

    public void updatenotToUse(){
        a_notToUse = a;
        A_notToUse = A;
        n_notToUse = n;
        uU_notToUse = uU;
        s_notToUse = s;

        if(!notToUse.equals("")){
            for(int i = 0; i< notToUse.length(); i++){
                char c = notToUse.charAt(i);
                if(a_notToUse.contains(""+c)) {
                    a_notToUse = a_notToUse.substring(0, a_notToUse.indexOf(c)) + a_notToUse.substring(a_notToUse.indexOf(c) + 1, a_notToUse.length());
                }
                if(A_notToUse.contains(""+c)) {
                    A_notToUse = A_notToUse.substring(0, A_notToUse.indexOf(c)) + A_notToUse.substring(A_notToUse.indexOf(c) + 1, A_notToUse.length());
                }
                if(n_notToUse.contains(""+c)){
                    n_notToUse = n_notToUse.substring(0,n_notToUse.indexOf(c))+n_notToUse.substring(n_notToUse.indexOf(c)+1,n_notToUse.length());
                }
                if(uU_notToUse.contains(""+c)) {
                    uU_notToUse = uU_notToUse.substring(0, uU_notToUse.indexOf(c)) + uU_notToUse.substring(uU_notToUse.indexOf(c) + 1, uU_notToUse.length());
                }
                if(s_notToUse.contains(""+c)){
                    s_notToUse = s_notToUse.substring(0,s_notToUse.indexOf(c))+s_notToUse.substring(s_notToUse.indexOf(c)+1,s_notToUse.length());
                }
            }
        }
    }
    // setter

    public void setNotToUse(String notToUse) {
        this.notToUse = notToUse;
        updatenotToUse();
    }

    // getter

    public String getNotToUse() {
        return notToUse;
    }

    public String getAllSymbols(boolean notToUse){
        if(notToUse){
            return a_notToUse + A_notToUse + n_notToUse + uU_notToUse + s_notToUse;
        }else {
            return a + A + n + uU + s;
        }
    }

}
