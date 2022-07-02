public class GuessGame {
        private Player p1;
        private Player p2;
        private Player p3;

        public void startGame(Player a, Player b, Player c){
            p1 = a;
            p2 = b;
            p3 = c;

            p1.pickNumber();
            p2.pickNumber();
            p3.pickNumber();


            //1.Player1이 Player2와 Player3에게 묻는다.
            //2.Player2가 Player3과 Player1에게 묻는다.
            //3.Player3이 Player1과 Player2에게 묻는다.
            //4. 1~3을 반복한다.
            //다른 플레이어의 수를 먼저 맞춘 플레이어가 나오면 그 플레이어가 승자가 되고, 게임을 끝낸다. 두 명의 플레이어가 동시에 맞출경우 둘 다 승자가 된다.

            while(true){

                System.out.println("--------------------------------------------------");

                //p1이 p2, p3에게 질문한다.
                System.out.println(p1.getName() + " : Guess my number!");
                System.out.println(p2.getName() + " guesses " + p1.getName() + "'s number. ");
                boolean p2isrightp1 = p1.askToGuess1(p2);

                System.out.println(p3.getName() + " guesses " + p1.getName() + "'s number. ");
                boolean p3isrightp1 = p1.askToGuess1(p3);

                if(p2isrightp1 && p3isrightp1) { //p2우, p3이 동시에 맞추는 경우
                    System.out.println(p2.getName() + " and " + p3.getName() + " both got it right.");
                    break;
                } else if (p2isrightp1) { //p2만 맞추는 경우
                    System.out.println(p2.getName() + " got it right.");
                    break;
                } else if (p3isrightp1) {//p3만 맞추는 경우
                    System.out.println(p3.getName() + " got it right.");
                    break;
                }

                //p2가 p1, p3에게 질문한다.
                System.out.println(p2.getName() + " : Guess my number!");
                System.out.println(p1.getName() + " guesses " + p2.getName() + "'s number. ");
                boolean p1isrightp2 = p2.askToGuess1(p1);

                System.out.println(p3.getName() + " guesses " + p2.getName() + "'s number. ");
                boolean p3isrightp2 = p2.askToGuess1(p3);

                if(p1isrightp2 && p3isrightp2) { //p1, p3이 동시에 맞는 경우
                    System.out.println(p1.getName() + " and " + p3.getName() + " both got it right.");
                    break;
                } else if (p1isrightp2) {//p1만 맞추는 경우
                    System.out.println(p1.getName() + " got it right.");
                    break;
                } else if (p3isrightp2) {//p3만 맞추는 경우
                    System.out.println(p3.getName() + " got it right.");
                    break;
                }

                //p3이 p1, p2에게 질문한다.
                System.out.println(p3.getName() + " : Guess my number!");
                System.out.println(p1.getName() + " guesses " + p3.getName() + "'s number. ");
                boolean p1isrightp3 = p3.askToGuess1(p1);

                System.out.println(p2.getName() + " guesses " + p3.getName() + "'s number. ");
                boolean p2isrightp3 = p3.askToGuess1(p2);

                if(p1isrightp3 && p2isrightp3) {//p1, p2가 동시에 맞는 경우
                    System.out.println(p1.getName() + " and " + p2.getName() + " both got it right.");
                    break;
                } else if (p1isrightp3) {//p1만 맞추는 경우
                    System.out.println(p1.getName() + " got it right.");
                    break;
                } else if (p2isrightp3) {//p2만 맞추는 경우
                    System.out.println(p2.getName() + " got it right.");
                    break;
                }

                }

            }
}
