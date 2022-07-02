public class Player {

    private int number;
    private String name;
    private int myPick;

    public void guess() {
        number = (int)(Math.random() * 10);
        System.out.println(name + " is guessing " + number);
    }

    public int getNumber() {
        return number;
    }

    public boolean setName(String n) {
        if(n == null){
            return false;
        }
        name = n;
        return true;

    }

    public String getName() {
        return name;
    }

    public void pickNumber() {
        myPick = (int)(Math.random() * 10);
        System.out.println(name + " has picked " + myPick);
    }

    public boolean askToGuess1(Player one) {
        one.guess();
        int num = one.getNumber();
        if(num == myPick){
            return true;
        } else {
            return false;
        }
    }


}
