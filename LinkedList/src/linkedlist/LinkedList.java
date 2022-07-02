package linkedlist;

public class LinkedList {
    private Node mData = null;
    private int mSize = 0;


    public boolean insert(int key, double value) {
        Node newnode = new Node();
        newnode.setKey(key);
        newnode.setValue(value);

        if (mData != null) {
            Node lastnode = mData;

            //마지막 노드 찾기
            // 1 2 3 4 5 null
            for (; lastnode.getNext() != null; lastnode = lastnode.getNext()) {
                if (lastnode.getKey() == key)
                    return false;
            }
            if (lastnode.getKey() == key)
                return false;

            //마지막 노드에 새 노드 추가
            lastnode.setNext(newnode);
            mSize++;

        } else {
            mData = newnode;
            mSize++;
        }

        return true;
    }

    // mData -> 1 ->  2 -> 3 -> 4 -> NULL
    public boolean delete(int key) {
        if (mData != null) {
            if (mData.getKey() == key) {
                mData = mData.getNext();
                mSize--;
                return true;
            }
        } else {
            return false;
        }

        Node curnode = mData;
        for (; curnode.getNext() != null; curnode = curnode.getNext()) {
            Node nextnode = curnode.getNext();
            if (nextnode.getKey() == key) {
                curnode.setNext(nextnode.getNext());
                mSize--;
                return true;
            }

        }

        return false;
    }

    public void print() {
        System.out.println("List: " + mSize + " elements");
        for (Node node = mData; node != null; node = node.getNext()) {
            System.out.println("  (" + node.getKey() + " , " + node.getValue() + ")  ");
        }
    }
}

