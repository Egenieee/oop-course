package dotcomobservers;

import extdotcomgame.DotCom;


import java.util.ArrayList;

public class SurvivingCells implements Observer{
    private DotCom subject;

    public SurvivingCells(DotCom s) {
        subject = s;
    }

    public void update() {
        ArrayList<String> state = subject.getState();

        System.out.print("Surviving cells in " + subject.getName() +": ");
        for (String cell : state) {
            System.out.print(cell + " ");
        }
        System.out.println();
    }
}
