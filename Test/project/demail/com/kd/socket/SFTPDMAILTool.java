/**
 * SFTPDMAILTool.java
 * Created by liurenyong at 2013-7-29
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package demail.com.kd.socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import demail.com.kd.dmail.tool.SFTPTool;

/**
 * 
 * @author liurenyong 2013-7-29
 */
public class SFTPDMAILTool implements DMAILHelpTool{
    
    /** 存储服务器ip,端口号,用户名,密码,以及相关目录的配置 */
    private static final Properties DMAIL_CONFIG_PROPERTIES = new Properties();
    
    /** 配置文件的名字 */
    private static final String DMAIL_CONFIG_FILE_NAME = "config.properties";
    
    /** sftp端口号 */
    private static final int SFTP_PORT = 22;
    
    /** 服务器IP key */
    private static String DMAIL_SERVER_IP_KEY = "hostip";
    
    /** 服务器端口号 key */
    private static String DMAIL_SERVER_PORT_KEY = "port";
    
    /** 服务器用户名 key */
    private static String DMAIL_SREVER_USER_NAME_KEY ="username";
    
    /** 服务器密码 key */
    private static String DMAIL_SERVER_PASSWORD_KEY = "password";
    
    /** 服务器发送路径 key */
    private static String DMAIL_SERVER_SEND_PATH_KEY = "sendfilepath";
            
    /** 服务器接收路径 key */
    private static String DMAIL_SERVER_RECEIVE_PATH_KEY = "receivebox";
    
    /** 服务器主机路径 key */
    private static String DMAIL_SERVER_HOST_PATH_KEY = "hostpath";
    
    /** 服务器垃圾箱路径 key */
    private static String DMAIL_SERVER＿GARBAGE_PATH_KEY = "garbagepath";
    
    /** 服务器转换路径 key */
    private static String DMAIL_SERVER_TRANSFER_PATH_KEY = "transfer";
    
    /** 服务器用户路径 key */
    private static String DMAIL_SERVER_USER_DIST_KEY = "userdisk";
    
    /** 服务器IP value */
    private static String DMAIL_SERVER_IP_VALUE = "10.35.31.58";
    
    /** 服务器端口号 value */
    private static String DMAIL_SERVER_PORT_VALUE = "5675";
    
    /** 服务器用户名 value */
    private static String DMAIL_SREVER_USER_NAME_VALUE ="d5000";
    
    /** 服务器密码 value */
    private static String DMAIL_SERVER_PASSWORD_VALUE = "root.123";
    
    /** 服务器发送路径 value */
    private static String DMAIL_SERVER_SEND_PATH_VALUE = "/home/d5000/dmail/syssend/";
            
    /** 服务器接收路径 value */
    private static String DMAIL_SERVER_RECEIVE_PATH_VALUE = "/home/d5000/dmail/dmailsg/dpt/zdh/recv/";
    
    /** 服务器主机路径 value */
    private static String DMAIL_SERVER_HOST_PATH_VALUE = "/home/d5000/dmail/dmailsrc/";
    
    /** 服务器垃圾箱路径 value */
    private static String DMAIL_SERVER＿GARBAGE_PATH_VALUE = "/home/d5000/dmail/garbage/";
    
    /** 服务器转换路径 value */
    private static String DMAIL_SERVER_TRANSFER_PATH_VALUE = "/home/d5000/dmail/transfer/";
    
    /** 服务器用户路径 value */
    private static String DMAIL_SERVER_USER_DIST_VALUE = "/home/d5000/dmail/userdisk";
    
    static {
        try {
            //加载配置文件
            DMAIL_CONFIG_PROPERTIES.load(new FileInputStream("conf" + File.separator + DMAIL_CONFIG_FILE_NAME));
            DMAIL_SERVER_IP_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER_IP_KEY);
            DMAIL_SERVER_PORT_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER_PORT_KEY);
            DMAIL_SREVER_USER_NAME_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SREVER_USER_NAME_KEY);
            DMAIL_SERVER_PASSWORD_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER_IP_KEY);
            DMAIL_SERVER_SEND_PATH_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER_SEND_PATH_KEY);
            DMAIL_SERVER_RECEIVE_PATH_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER_RECEIVE_PATH_KEY);
            DMAIL_SERVER_HOST_PATH_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER_HOST_PATH_KEY);
            DMAIL_SERVER＿GARBAGE_PATH_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER＿GARBAGE_PATH_KEY);
            DMAIL_SERVER_TRANSFER_PATH_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER_TRANSFER_PATH_KEY);
            DMAIL_SERVER_USER_DIST_VALUE = DMAIL_CONFIG_PROPERTIES.getProperty(DMAIL_SERVER_IP_KEY);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    SFTPTool tool = new SFTPTool();

    /* (non-Javadoc)
     * @see com.kd.socket.DMAILHelpTool#sendDMAIL(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void sendDMAIL(String address, String userName, String mailFilePath, String appendixFilePath) {
        tool.uploadFile(DMAIL_SERVER_IP_VALUE, SFTP_PORT, DMAIL_SREVER_USER_NAME_VALUE, DMAIL_SERVER_PASSWORD_VALUE, DMAIL_SERVER_SEND_PATH_VALUE, mailFilePath);
        tool.uploadFile(DMAIL_SERVER_IP_VALUE, SFTP_PORT, DMAIL_SREVER_USER_NAME_VALUE, DMAIL_SERVER_PASSWORD_VALUE, DMAIL_SERVER_SEND_PATH_VALUE, appendixFilePath);
    }

    /* (non-Javadoc)
     * @see com.kd.socket.DMAILHelpTool#receiveDMAIL(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void receiveDMAIL(String address, String mailFilePath, String appendixFilePath) {
        tool.downloadFile(DMAIL_SERVER_IP_VALUE, SFTP_PORT, DMAIL_SREVER_USER_NAME_VALUE, DMAIL_SERVER_PASSWORD_VALUE, DMAIL_SERVER_RECEIVE_PATH_VALUE, mailFilePath, "temp" + File.separator + "temp.eh");
    }

}
