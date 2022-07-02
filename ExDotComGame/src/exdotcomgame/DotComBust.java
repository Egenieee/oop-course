package exdotcomgame;

import java.util.ArrayList;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;


    public void setUpGame() {

        ShortDotCom one = new ShortDotCom();
        one.setName("Pets.com");
        LongDotCom two = new LongDotCom();
        two.setName("eToys.com");
        HeavyDotCom three = new HeavyDotCom();
        three.setName("Go2.com");
        Submarine four = new Submarine();
        four.setName("Dolphin.com");

        dotComsList.add(one); //DotCom 타입으로 추가됨.
        dotComsList.add(two);
        dotComsList.add(three);
        dotComsList.add(four);

        ArrayList<String> newLocation;
        newLocation = helper.placeDotCom(one.size());
        one.setLocationCells(newLocation);
        newLocation = helper.placeDotCom(two.size());
        two.setLocationCells(newLocation);
        newLocation = helper.placeDotCom(three.size());
        three.setLocationCells(newLocation);
        newLocation = helper.placeDotCom(four.size());
        four.setLocationCells(newLocation);
    }

    public void startPlaying() {
        while (!dotComsList.isEmpty()) {
            String userGuess = helper.getUserInputRandom("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "";
        for (int x = 0; x < dotComsList.size(); x++) {
            result = dotComsList.get(x).checkYourself(userGuess);
            if (result.equals("hit")) {
                result += " " + dotComsList.get(x).getName();
                break;
            } else if (result.equals("kill")) {
                result += " " + dotComsList.get(x).getName();
                dotComsList.remove(x);
                break;
            }
        }
        if (!result.equals("miss")) {
            System.out.println(result);
        }
    }


    private void finishGame() {
        System.out.println("All Dot Coms are dead!");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
        }
    }
}
