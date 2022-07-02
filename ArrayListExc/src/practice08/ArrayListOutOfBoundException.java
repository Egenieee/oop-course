package practice08;

public class ArrayListOutOfBoundException extends Exception{
    private int idx;

    public ArrayListOutOfBoundException(int idx) { this.idx = idx;}

    public int getIndex() {
        return this.idx;
    }
}
