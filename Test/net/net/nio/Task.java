package net.nio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Task {
    public void checkState() {
        Set<String> keys = SessionManager.getSessionKeys();
        if (keys.size() == 0) {
            return;
        }
        List<String> removes = new ArrayList<String>();
        Iterator<String> iterator = keys.iterator();
        String key = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            if (!SessionManager.getSession(key).isKeekAlive()) {
                removes.add(key);
            }
        }
        if (removes.size() > 0) {
            Log.i("sessions is time out,remove " + removes.size() + "session");
        }
        SessionManager.remove(removes.toArray(new String[removes.size()]));
    }

    public void sendAck() {
        Set<String> keys = SessionManager.getSessionKeys();
        if (keys.size() == 0) {
            return;
        }
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            // TODO ·¢ËÍÐÄÌø°ü
        }
    }
}