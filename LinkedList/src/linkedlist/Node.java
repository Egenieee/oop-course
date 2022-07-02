package linkedlist;

public class Node {
    private Node mNext = null;
    private int mKey = 0;
    private double mValue = 0;

    public void setNext(Node node) {
        mNext = node;
    }

    public void setKey(int key) {
        mKey = key;
    }

    public void setValue(double value) {
        mValue = value;
    }

    public Node getNext() {
        return mNext;
    }

    public int getKey() {
        return mKey;
    }

    public double getValue() {
        return mValue;
    }
}
