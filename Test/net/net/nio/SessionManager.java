package net.nio;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private static ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<String, Session>();

    public static void addSession(String key, Session session) {
        sessions.put(key, session);
    }

    public static Session getSession(String key) {
        return sessions.get(key);
    }

    public static Set<String> getSessionKeys() {
        return sessions.keySet();
    }

    public static int getSessionCount() {
        return sessions.size();
    }

    public static void remove(String[] keys) {
        for (String key : keys) {
            if (sessions.containsKey(key)) {
                sessions.get(key).distroy();
                sessions.remove(key);
            }
        }
    }

    public static void remove(String key) {
        if (sessions.containsKey(key)) {
            sessions.get(key).distroy();
            sessions.remove(key);
        }
    }
}