package CollectionFramework;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapDemo {
    public static void main(String[] args) {
        Map<Day, String> mp = new EnumMap<>(Day.class);
        mp.put(Day.TUESDAY, "Gym");
        System.out.println(Day.TUESDAY.ordinal());
        mp.put(Day.MONDAY, "Walk");
        System.out.println(mp);
    }
}

enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}