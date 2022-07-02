package practice08;

public class ArrayListExcTest {
    public static void main (String [] args) {
        ArrayListExc arrlist = new ArrayListExc();

        arrlist.add("Sunday");
        arrlist.add("Monday");
        arrlist.add("Tuesday");
        arrlist.add("Wednesday");
        arrlist.add("Thursday");
        arrlist.add("Friday");
        arrlist.add("Saturday");

        try {
            System.out.println("test 1");
            arrlist.getExc(-1);
        } catch (ArrayListOutOfBoundException ex) {
            System.out.println("" + ex.getIndex() + " is out of bound.");
        }

        try {
            System.out.println("test 2");
            arrlist.getExc(7);
        } catch (ArrayListOutOfBoundException ex) {
            System.out.println("" + ex.getIndex() + " is out of bound.");
        }

        try {
            System.out.println("test 3");
            arrlist.setExc(1, "월요일");
        } catch (ArrayListOutOfBoundException ex) {
            System.out.println("" + ex.getIndex() + " is out of bound.");
        }

        try {
            System.out.println("test 4");
            arrlist.setExc(7, "토요일");
        } catch (ArrayListOutOfBoundException ex) {
            System.out.println("" + ex.getIndex() + " is out of bound.");
        }

        try {
            System.out.println("test 5");
            int idx = arrlist.indexOfExc("아주대학교");
        } catch (ArrayListStringNotFoundException ex) {
            System.out.println("" + ex.getMessage() + " is not found.");
        }

        try {
            System.out.println("test 6");
            arrlist.removeExc(7);
        } catch (ArrayListOutOfBoundException ex) {
            System.out.println("" + ex.getIndex() + " is out of bound.");
        }


        try {
            System.out.println("test 7");
            arrlist.removeExc("Monday");
        } catch (ArrayListStringNotFoundException ex) {
            System.out.println("" + ex.getMessage() + " is not found in the list");
            System.out.println(arrlist);
        }
    }
}

