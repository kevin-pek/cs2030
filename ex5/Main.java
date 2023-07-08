import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.function.UnaryOperator;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

class Main {
    static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, n)
            .noneMatch(x -> n % x == 0);
    }

    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(1, n)
            .filter(x -> isPrime(x) && (isPrime(x + 2) || isPrime(x - 2)));
    }

    static String reverse(String s) {
        return Stream.<String>of(s.split(""))
            .reduce("", (x, r) -> r + x);
    }

    static long countRepeats(List<Integer> list) {
        return IntStream.range(0, list.size() - 1)
            .filter(i -> list.get(i) == list.get(i + 1) &&
                    (i >= list.size() - 2 || list.get(i) != list.get(i + 2)))
            .count();
    }

    static UnaryOperator<List<Integer>> generateRule() {
        BiFunction<List<Integer>, Integer, Integer> f =
            new BiFunction<List<Integer>, Integer, Integer>() {
                public Integer apply(List<Integer> l, Integer i) {
                    if (l.get(i) == 1) {
                        return 0;
                    }
                    int count = 0;
                    if (i != 0) {
                        if (l.get(i - 1) == 1) {
                            count++;
                        }
                    }
                    if (i != l.size() - 1) {
                        if (l.get(i + 1) == 1) {
                            count++;
                        }
                    }
                    return count == 1 ? 1 : 0;
                }
            };
        return x -> IntStream.range(0, x.size()).map(y -> f.apply(x, y))
            .boxed().toList();
    }

    static Stream<String> gameOfLife(List<Integer> list,
            UnaryOperator<List<Integer>> rule, int n) {
        return Stream.iterate(list, rule).limit(n)
            .map(l -> l.stream()
                    .map(x -> x == 1 ? "x" : ".")
                    .reduce("", (x, s) -> x + s));
    }
}
