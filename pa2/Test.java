import java.util.function.*;
import java.util.List;
class Test {
    static void p(Object o) {
        System.out.println(o.toString());
    }

    static Pair<Integer,Integer> pairOf(int a, int b) {
        Pair<Integer,Integer> pair = Pair.of(a, b);
        System.out.println(pair + " evaluated");
        return pair;
    }

    public static void main(String[] args) {
        Pair<Integer,Integer> p = Pair.of(5, 7);
        p(p);
        Predicate<Pair<Integer, Integer>> pred = pair -> pair.first() == pair.second();
        Function<Pair<Integer, Integer>, Integer> f = pair -> pair.first() * pair.first();
        DnC<Pair<Integer,Integer>, Integer> dnc = DnC.of(p, pred, f,
                 pair -> {
                    int a = pair.first();
                    int b = pair.second();
                    int mid = (a + b) / 2;
                    return Pair.of(Pair.of(a, mid), Pair.of(mid + 1, b));
                });
        p(dnc);
        dnc.peek(x -> System.out.println(x));
        dnc.left().peek(x -> System.out.println(x));
        dnc.left().left().peek(x -> System.out.println(x));
        dnc.left().left().left().peek(x -> System.out.println(x));
        dnc.peek(x -> System.out.println(x));
        dnc.right().peek(x -> System.out.println(x));
        dnc.right().right().peek(x -> System.out.println(x));
        p(dnc.left() instanceof DnC);
        p(dnc.right() instanceof DnC);
        p(dnc.solve());
        p(dnc.left().solve());
        p(dnc.left().left().solve());
        p(dnc.right().solve());
        p(dnc.right().right().solve());
        System.out.println("---------level 5---------");
        BinaryOperator<Integer> bop = (x,y) -> x + y;
        dnc = DnC.of(pairOf(5, 7), pred, f, pair -> {
                    int a = pair.first();
                    int b = pair.second();
                    int mid = (a + b) / 2;
                    return Pair.of(Pair.of(a, mid), Pair.of(mid + 1, b));
                });
        p(dnc);
        p(dnc.solve(bop));
        dnc = DnC.<Pair<Integer,Integer>,Integer>of(() -> pairOf(5, 7), pred, f, pair -> {
                    int a = pair.first();
                    int b = pair.second();
                    int mid = (a + b) / 2;
                    return Pair.of(() -> pairOf(a, mid), () -> pairOf(mid + 1, b));
                });
        p(dnc);
        p(dnc.solve(bop));
        SumList sumlist = new SumList(List.of(1, 2, 3, 4, 5));
        p(sumlist);
        p(sumlist instanceof DnC);
        //  sumlist.left().left().left();
        // sumlist.right().right().right();
        p(sumlist.solve((x,y) -> x + y));
        p(sumlist.left().solve((x,y) -> x + y));
        p(sumlist.left().left().solve((x,y) -> x + y));
        p(sumlist.left().left().left().solve((x,y) -> x + y));
        p(sumlist.right().solve((x,y) -> x + y));
        p(sumlist.right().right().solve((x,y) -> x + y));
        p(sumlist.right().right().right().solve((x,y) -> x + y));
    }
}
