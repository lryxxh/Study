/**
 * ClassMagicNumber_Test.java
 * Created by liurenyong at 2013-10-8
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author liurenyong 2013-10-8
 */
public class ClassMagicNumber_Test {

    public static void main(String[] args) {
        try {
            int a = 0xCAFEBABE;
            System.out.println((byte) a);
            System.out.println((byte) (a >> 8));
            System.out.println((byte) (a >> 16));
            System.out.println((byte) (a >> 24));
            System.err.println(-54 & 0xFF << 24);
            System.err.println((-54 & 0xFF) << 24);
            InputStream is = new FileInputStream(new File("D:/Work/eclipse/D5000_WorkSpace2/HMI/bin/Test.class"));
            byte[] magicNumberBytes = new byte[4];
            is.read(magicNumberBytes);
            int number = ((magicNumberBytes[0] & 0xFF) << 24) + ((magicNumberBytes[1] & 0xFF) << 16) + ((magicNumberBytes[2] & 0xFF) << 8)
                    + (magicNumberBytes[3] & 0xFF);
            System.out.println(number + "    " + 0xCAFEBABE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
