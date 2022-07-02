package dotcomobservers;

import extdotcomgame.*;

import java.util.ArrayList;

public class DamageRate implements Observer {
    private DotCom subject;
    private double dotComSize;
    private double damageRate = 0;

    public DamageRate(DotCom s) {
        subject = s;
        dotComSize = s.size();
    }

    public void update() {
        ArrayList<String> state = subject.getState();

        damageRate = 100 * ((dotComSize - state.size())/ dotComSize);

        System.out.print("Damage rate of " + subject.getName() + " = ");
        System.out.printf("%.2f", damageRate);
        System.out.print("%");

        System.out.println();

    }
}
