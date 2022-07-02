import linkedlist.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        System.out.println("insert (5,100)");
        if (!list.insert(5, 100))
            System.out.println("fail to insert (5, 100)");
        System.out.println("insert (7,29)");
        if (!list.insert(7, 29))
            System.out.println("fail to insert (7, 29)");
        System.out.println("insert (2,60)");
        if (!list.insert(2, 60))
            System.out.println("fail to insert (2, 60)");
        System.out.println("insert (7,80)");
        if (!list.insert(7, 80))
            System.out.println("fail to insert (7, 80)");

        list.print();

        System.out.println("delete a node with key = 5");
        if (!list.delete(5))
            System.out.println("fail to delete a node with key = 5");
        System.out.println("delete a node with key = 5 again");
        if (!list.delete(5))
            System.out.println("fail to delete a node with key = 5");

        list.print();

        System.out.println("insert (10,100)");
        if (!list.insert(10, 100))
            System.out.println("fail to insert (10, 100)");
        System.out.println("insert (20,200)");
        if (!list.insert(20, 200))
            System.out.println("fail to insert (20, 200)");

        list.print();

        System.out.println("delete a node with key = 10");
        if (!list.delete(10))
            System.out.println("fail to delete a node with key = 10");
        System.out.println("delete a node with key = 2");
        if (!list.delete(2))
            System.out.println("fail to delete a node with key = 2");

        list.print();
    }
}

