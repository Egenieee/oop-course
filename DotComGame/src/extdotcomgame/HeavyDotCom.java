package extdotcomgame;

import java.util.*;

public class HeavyDotCom extends DotCom {
    public int size() {
        return 3;
    }

    public void setLocationCells(ArrayList<String> locs) {
        if (locs == null) return;
        locationCells = (ArrayList<String>) locs.clone();
        locationCells.addAll(locs);
    }

    public ArrayList<String> getState() {//중복일 경우 제외하고 리턴하는 함수
        ArrayList<String> originCells = new ArrayList<String>();
        for (int i = 0; i < locationCells.size(); i++) {
            boolean check = true;
            for (int j = 0; j < originCells.size(); j++) {
                if (originCells.get(j).equals(locationCells.get(i))) {//중복일 경우
                    check = false;
                    break;
                }
            }
            if (check == true) {// 중복 아닐 경우
                originCells.add(locationCells.get(i));
            }
        }
        return (ArrayList<String>) originCells.clone();
    }
}
