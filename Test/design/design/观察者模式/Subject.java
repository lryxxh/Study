/**
 * Subject.java
 * Created by liurenyong at 2013-12-20
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */
package design.观察者模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author liurenyong 2013-12-20
 */
public abstract class Subject {
    
    private List<Observer> observers = null;
    
    public Subject() {
        observers = new ArrayList<Observer>();
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
