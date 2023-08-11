package tree;

import java.util.Objects;

public class Comparables {
    private Comparables() {
    }

    public static <T extends Comparable<T>> boolean lessThan(T comparable1, T comparable2) {
        return compare(comparable1, comparable2) < 0;
    }

    public static <T extends Comparable<T>> boolean greaterThan(T comparable1, T comparable2) {
        return compare(comparable1, comparable2) > 0;
    }

    private static <T extends Comparable<T>> int compare(T comparable1, T comparable2) {
        return Objects.compare(comparable1, comparable2, Comparable::compareTo);
    }
}
