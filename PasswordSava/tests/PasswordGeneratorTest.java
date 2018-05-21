import Fields.Password;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.GenericArrayType;

import static org.junit.Assert.*;

public class PasswordGeneratorTest {

    @Test
    public void generate_01(){
        PasswordGenerator pg = new PasswordGenerator();
        assertEquals("Password has the wrong length",0,pg.generate(0).length());
        assertEquals("Password has the wrong length",5,pg.generate(5).length());

        // Testing Index out of Bounds
        for(int i = 0; i < 1000;i++){
            assertEquals("Password has the wrong length",20,pg.generate(20).length());
        }
    }
    @Test
    public void generate_02() {
        // testing with notToUse
        PasswordGenerator pw = new PasswordGenerator();
        String p;
        pw.setNotToUse("abc");
        for (int i = 0; i < 1000; i++) {
            p = pw.generate(10);
            assertFalse("This letter (a) should not be includet", p.contains("a"));
            assertFalse("This letter (b) should not be includet", p.contains("b"));
            assertFalse("This letter (c) should not be includet", p.contains("c"));
        }
    }
    @Test
    public void generate_03(){
            // testing with notToUse
            PasswordGenerator pw = new PasswordGenerator();
            String p;
            pw.setNotToUse("abcxyz");
            for(int i = 0;i<1000;i++){
                p = pw.generate(10);
                assertFalse("This letter (a) should not be includet",p.contains("a"));
                assertFalse("This letter (b) should not be includet",p.contains("b"));
                assertFalse("This letter (c) should not be includet",p.contains("c"));
                assertFalse("This letter (x) should not be includet",p.contains("x"));
                assertFalse("This letter (y) should not be includet",p.contains("y"));
                assertFalse("This letter (z) should not be includet",p.contains("z"));
            }
        }
    @Test
    public void generate_04(){
        // testing with notToUse
        PasswordGenerator pw = new PasswordGenerator();
        String p;
        pw.setNotToUse("abcdefghijklmnopqrstuvwxyz");
        for(int i = 0;i<1000;i++){
            p = pw.generate(10);
            assertFalse("This letter (a) should not be includet",p.contains("a"));
            assertFalse("This letter (b) should not be includet",p.contains("b"));
            assertFalse("This letter (c) should not be includet",p.contains("c"));
            assertFalse("This letter (x) should not be includet",p.contains("x"));
            assertFalse("This letter (y) should not be includet",p.contains("y"));
            assertFalse("This letter (z) should not be includet",p.contains("z"));
        }
    }
    @Test
    public void generate_05(){
        // set all chars to not use
        PasswordGenerator pw = new PasswordGenerator();
        pw.setNotToUse(pw.getAllSymbols(false));
        assertEquals("Wrong amount of Symbols in allSymbols",0,pw.getAllSymbols(true).length());
        assertEquals("Wrong password",0,pw.generate(10).length());
    }
    @Test
    public void generate_06(){
        // test not to use
        String notToUse = "sefAQÜö%&$§\"'-01o";
        //System.out.println(notToUse);
        PasswordGenerator pg = new PasswordGenerator(notToUse);
        assertFalse("allSymbols is wrong",containsChar(pg.getAllSymbols(true),notToUse));
        assertTrue("allSymbols is worng",containsChar(pg.getAllSymbols(false),notToUse));
        for(int i = 0; i < 10000; i++){
            assertFalse("There is a pw that contains a char that is in notToUse",containsChar(pg.generate(10),notToUse));
        }

    }
    @Ignore
    public void generate_advanced_07(){
        PasswordGenerator pg = new PasswordGenerator();
        String password;
        pg.setNotToUse("0oOlIabcABC");
        int n = 0;
        for(int i = 0; i<10;i++){
            password = pg.generate(40,false,true,false,false,false);
            System.out.println(password);
            if(password.equals("")){
                n++;
            }
        }
        System.out.println("n: "+ n);
    }

    @Test
    public void generatePin_01(){
        PasswordGenerator pg = new PasswordGenerator();
        assertEquals("Wrong length of the pin",10,pg.generatePin(10).length());
        assertEquals("Wrong length of the pin",0,pg.generate(0).length());
    }

    @Test
    public void containsChar_01(){
        assertTrue("Error in containsChar",containsChar("hallo","oh"));
        assertTrue("Error in containsChar",containsChar("hasjdfiojeifjksdvkaöregi4872097528739§$§%§$%$§allo","ö"));
        assertFalse("Error in containsChar",containsChar("das ist ein pw: 123","u45;\\"));
    }
    public boolean containsChar(String string,String notToUse){
        if(!(string.equals("") || notToUse.equals(""))){
            for(int i = 0; i < string.length();i++){
                if(notToUse.contains(""+string.charAt(i))){
                    // there is at least on char in string that is also in notToUse
                    // we dont want that
                    return true;
                }
            }
        }
        // there is not a singel char in string and in notToUse
        // we want that
        return false;
    }
}