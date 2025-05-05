package CollectionFramework;

import java.util.*;

public class VectorDemo {
    public static void main(String[] args) {

        // Arraylist is not thread-safe.
        List<Integer> list = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i=0; i<1000; i++)
                list.add(i);
        });
        Thread t2 = new Thread(() -> {
            for (int i=0; i<1000; i++)
                list.add(i);
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        // ek hi time pe dono thread access kr le rhe list ko..to do me se koi ek add ho rha

        System.out.println(list.size());

        // Vector is thread-safe
        List<Integer> vlist = new Vector<>();
        Thread t3 = new Thread(() -> {
            for (int i=0; i<1000; i++)
                vlist.add(i);
        });
        Thread t4 = new Thread(()-> {
            for (int i=0; i<1000; i++)
                vlist.add(i);
        });

        t3.start();
        t4.start();
        try {
            t3.join();
            t4.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(vlist.size());

        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        Integer x = st.pop();
        System.out.println(x);
        st.push(4);
        st.push(5);
        System.out.println(st.search(Integer.valueOf(1))); // Returns 1-based indexing from the top.

    }
}
