package CollectionFramework;

import java.util.*;

public class SetOverview {
    public static void main(String[] args) {
        // Set is a collection that cannot contain duplicate elements.
        // Map --> HashMap, LinkedHashMap, TreeMap, EnumMap
        // Set --> HashSet, LinkedHashSet, TreeSet, EnumSet

        Set<Integer> st = new HashSet<>(); // it is unordered
        st.add(12);
        st.add(1);
        st.add(1);
        st.add(67);
        System.out.println(st);

        Set<Integer> st2 = new LinkedHashSet<>(); // ordered
        st2.add(12);
        st2.add(1);
        st2.add(67);
        System.out.println(st2);

        Set<Integer> st3 = new TreeSet<>(); // sorted
        st3.add(12);
        st3.add(1);
        st3.add(67);
        System.out.println(st3);

        NavigableSet<Integer> st4 = new TreeSet<>(); // more methods
        st4.add(12);
        st4.add(1);
        st4.add(67);
        System.out.println(st4.ceiling(1));

        // thread safety
        // use concurrentSkipListSet

    }
}
