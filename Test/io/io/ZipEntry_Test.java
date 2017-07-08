package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipEntry_Test {
	
	public static void main(String[] args) {
	    
	    
//	    String byteStr = "10, 0, 18, 3, 112, 100, 114, 26, 5, 115, 99, 97, 100, 97, 32, -32, -40, -63, -114, 5";
//	    String[] array = byteStr.split(",");
//	    byte[] byteArray = new byte[array.length];
//	    int i = 0;
//	    for(String temp : array) {
//	        byte tt = Byte.parseByte(temp.trim());
//	        byteArray[i] = tt;
//	        i++;
//	    }
//	    System.out.println(new String(byteArray));
//	    
	    
	    
	    
	    /*
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		Vector<String> temp1 = new Vector<String>();
		temp1.add("11");
		temp1.add("122");
		
		Vector<String> temp2 = new Vector<String>();
		temp2.add("111");
		temp2.add("1222");
		
//		Vector<String> ttt = (Vector<String>) temp2.clone(); 
//		temp2.setElementAt("tttt", 0);
//		System.out.println(ttt);
		
		data.add(temp1);
		data.add(temp2);
		
//		Vector<Vector<String>> cloneVector = new Vector<Vector<String>>(); 
//		cloneVector.add((Vector<String>) temp1.clone());
//		cloneVector.add((Vector<String>) temp2.clone());
		Vector<Vector<String>> cloneVector =  (Vector<Vector<String>>) data.clone();
		data.get(0).setElementAt("222", 0);
		data.get(0).add("242");
		data.remove(1);
//		Vector<Vector<String>> cloneVector = new Vector<Vector<String>>();
//		Vector<String> temp11 = new Vector<String>();
//		temp11.add("11");
//		temp11.add("122");
//		
//		Vector<String> temp21 = new Vector<String>();
//		temp21.add("111");
//		temp21.add("1222");
//		
//		cloneVector.add(temp11);
//		cloneVector.add(temp21);
		
		System.out.println(cloneVector +" ==  " + data);
	*/
//		Date date = new Date();
//		Timestamp timestamp = new Timestamp(date.getTime());
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		System.err.println(dateFormat.format(date));
//		System.out.println();
//		System.out.println(timestamp + "  " + timestamp.getNanos());
//		Date date = new Date(1367112677123l);
//		String temp = dateFormat.format(date);
//		System.out.println(temp);
	    
	    try {
            ZipInputStream ziStream = new ZipInputStream(new FileInputStream(new File("C:/Users/HHD/Desktop/commons-net-1.4.0.jar")));
            ZipEntry ze= ziStream.getNextEntry();
            while (ze !=null) {
                System.out.println(ze.getName());
//                if("META-INF/MANIFEST.MF".equals(ze.getName())) {
//                    Properties props = new Properties();
//                    props.load(ziStream);
//                    System.out.println(props.getProperty(Constants.BUNDLE_NAME));
////                    BufferedReader bis = new BufferedReader(new InputStreamReader(ziStream));
////                    String line = null;
////                    while ((line = bis.readLine()) != null) {
////                        System.out.println(line);
////                    }
//                    break;
//                }
                ze = ziStream.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	

}


