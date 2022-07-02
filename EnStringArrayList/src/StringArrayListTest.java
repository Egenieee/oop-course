public class StringArrayListTest {
    public static void main(String arg[]) {
        EnStringArrayList arr = new EnStringArrayList();

        for (int k = 0; k < 3000000; k++) {
            arr.add(Integer.toString(k));
        }

        System.out.println(arr.size());

    }
}
