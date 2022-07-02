package dotcomobservers;

import extdotcomgame.DotCom;

import java.util.ArrayList;

abstract public class Observable {
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer o) {
        observers.add(o);
    }

    public void dettach(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for(Observer o : observers) {
            o.update();
        }
    }
}
