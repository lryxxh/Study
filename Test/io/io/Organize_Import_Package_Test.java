/**
 * Organize_Import_Package_Test.java
 * Created by liurenyong at 2013-8-7
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 
 * @author liurenyong 2013-8-7
 */
public class Organize_Import_Package_Test {
    
    private static final String LIB_PATH = "D:/Work/eclipse/D5000_WorkSpace2/zhongshiyou_cc2000/";
    
    private static final String LIB_DIR_NAME = "jars/";
    
    private static final String PRJS_DIR = "mmi/prjs/";
    
    HashMap<String, String> map = generateClassJarMap();
    
    public static void main(String[] args) {
        File file = new File("D:/Work/eclipse/D5000_WorkSpace2/zhongshiyou_cc2000/mmi/prjs/app/src");
        for (File tempFile : file.listFiles()) {
            System.out.println(tempFile);
            List<String> list = getJarFormAppDir(tempFile);
            System.out.println(tempFile +" ===  "+list);
        }
    }
    
    public static List<String> getJarFormAppDir(File file) {
        List<String> importClassList = new ArrayList<String>();
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for (File tempFile : files) {
                importClassList.addAll(getJarFormAppDir(tempFile));
            }
        } else {
//            System.out.println(file.getName());
            if(!file.getName().endsWith(".java")) {
                return importClassList;
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line = null;
                boolean flag = false;
                while (!flag) {
                    line = reader.readLine();
                    if(line != null && line.trim().startsWith("import")) {
                        String clazzString = line.trim().replace("import ", "").replace(";", "");
                        importClassList.add(clazzString);
                    } else if (line != null && line.trim().startsWith("public")) {
                        flag = true;
                    }
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File[] fiels = file.listFiles();
        return importClassList;
    }
    
    public static HashMap<String, String> generateClassJarMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        File file = new File(LIB_PATH + LIB_DIR_NAME);
        for(File tempFile : file.listFiles()) {
            try {
                ZipInputStream ziStream = new ZipInputStream(new FileInputStream(tempFile));
                ZipEntry ze= ziStream.getNextEntry();
                while (ze !=null) {
                   if(ze.getName().endsWith(".class")) {
                       System.out.println(ze.getName());
                       map.put(ze.getName().replace("/", "."), tempFile.getName());
                   }
                    ze = ziStream.getNextEntry();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }  
        }
      
        return map;
        
    }
    
    

}
