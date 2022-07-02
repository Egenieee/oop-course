public class GameLauncher {
    public static void main(String[] args){
        GuessGame game = new GuessGame();
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        p1.setName("Jinhee");
        p2.setName("Jinwoo");
        p3.setName("Seohee");

        System.out.println(p1.getName());
        game.startGame(p1, p2, p3);

    }
}
