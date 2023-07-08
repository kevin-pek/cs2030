import java.util.List;

class SumList extends DnC<List<Integer>, Integer> {
    SumList(List<Integer> l) {
        super(() -> l, x -> x.size() == 1, x -> x.get(0),
                x -> Pair.of(x.subList(0, (x.size() + 1) / 2),
                x.subList((x.size() + 1) / 2, x.size())));
    }
}
