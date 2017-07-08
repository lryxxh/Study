/**
 * RegexFormatt_Test.java
 * Created by liurenyong at 2013-11-6
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author liurenyong 2013-11-6
 */
public class RegexFormatt_Test {
    
    public static void main(String[] args) {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            try {
                line = reader.readLine();
                if(line != null && !"".equals(line)) {
                    Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{1,3})?$");
                    Matcher matcher = pattern.matcher(line);
                    boolean matches = matcher.matches();
                    System.out.println(matches);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

}
