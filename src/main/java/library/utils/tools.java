package library.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class tools {

    private tools() {
    }

    public static <T> Collector<T, ?, List<T>> toSortedList(Comparator<? super T> comparator) {
        return Collectors.collectingAndThen(
                Collectors.toCollection(ArrayList::new),
                list -> {
                    list.sort(comparator);
                    return List.copyOf(list);
                }
        );
    }
}