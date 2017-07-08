package lang;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Preference_Test.java
 * Created by HHD at 2013-6-6
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author HHD 2013-6-6
 */
public class Preference_Test {
    

    public static void main(String[] args) {
        Preferences preferences = Preferences.userNodeForPackage(Preference_Test.class);
//        preferences.put("123", "456");
//        preferences.putBoolean("333", false);
//        preferences.putByteArray("3333", "sdfsfs".getBytes());
        String[] keys;
        try {
            keys = preferences.keys();
            preferences.exportNode(new FileOutputStream(new File("C:/Users/HHD/Desktop/1.txt")));
            preferences.flush();
            for (String key : keys) {
                if(key.equals("3333")) {
                    System.err.println(new String(preferences.getByteArray(key, null)));
                }
                System.out.println(key + " " + preferences.get(key, ""));
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(".........");
    }
}
