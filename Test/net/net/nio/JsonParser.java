package net.nio;

import java.io.IOException;
import java.net.ServerSocket;

public class JsonParser {

//    private static JSONObject mJson;

    public synchronized static String get(String json, String key) {
//        mJson = JSON.parseObject(json);
//        return mJson.getString(key);
        return "";
    }
    
    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket();
            System.out.println(serverSocket.isClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}