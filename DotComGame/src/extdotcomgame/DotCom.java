package extdotcomgame;

import dotcomobservers.Observable;
import java.util.*;

public class DotCom extends Observable {
    protected ArrayList<String> locationCells;
    private String name;
    private int dotComSize;

    public String checkYourself(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "kill";
                System.out.println("You sunk " + name + ". ");
            } else
                result = "hit";
            this.notifyObservers();
        }

        return result;
    }

    public void setLocationCells(ArrayList<String> loc) {
        if (loc == null) return;
        locationCells = (ArrayList<String>) loc.clone();
    }

    public void setName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getState() {
        return (ArrayList<String>) locationCells.clone();
    }

    public int size() {return dotComSize; }

}
