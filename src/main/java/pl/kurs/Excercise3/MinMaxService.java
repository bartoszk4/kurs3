package pl.kurs.Excercise3;

import java.util.*;

public class MinMaxService {

    public static <T extends Comparable<? super T>> MinMax<T> getMinAndMax(Collection<? extends T> items) {
        if (items == null) {
            throw new NullPointerException("Element nie może być null");
        }
        Iterator<? extends T> it = items.iterator();
        if (!it.hasNext()) {
            throw new IllegalArgumentException("Kolekcja nie może być pusta");
        }

        T min = it.next();
        if (min == null) {
            throw new NullPointerException("Element nie może być null");
        }
        T max = min;

        while (it.hasNext()) {
            T v = it.next();
            if (v == null) {
                throw new NullPointerException("Element nie może być null");
            }
            if (v.compareTo(min) < 0) min = v;
            if (v.compareTo(max) > 0) max = v;
        }
        return new MinMax<>(min, max);
    }

}
