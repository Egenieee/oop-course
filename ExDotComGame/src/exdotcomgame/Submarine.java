package exdotcomgame;

public class Submarine extends DotCom{

    private boolean underwater = false;

    public int size() {
        return 3;
    }

    public String checkYourself(String userInput) {
        String result = "miss";

        if (!underwater) {
            result = super.checkYourself(userInput);
            if (result.equals("hit")) {
                //System.out.println("Somebody hit the boat. Dolphin goes down.");
                underwater = true;
            }
        } else if (result.equals("miss")) {
            //System.out.println("Now, we are safe. Dolphin goes up. ");
            underwater = false;
        }

        return result;
    }

}
