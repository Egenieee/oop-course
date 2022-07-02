package exdotcomgame;

import java.util.ArrayList;

public class HeavyDotCom extends DotCom {
    public int size() {
        return 3;
    }

    public void setLocationCells(ArrayList<String> locs) {
        if (locs == null) return;
        locationCells = (ArrayList<String>) locs.clone();
        locationCells.addAll(locs);
    }
}
