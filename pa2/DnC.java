import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;

class DnC<T,R> {
    private final Optional<Supplier<T>> t;
    private final Predicate<T> pred;
    private final Function<T, R> func;
    private final Function<T,Pair<Supplier<T>,Supplier<T>>> sub;

    DnC(Supplier<T> t, Predicate<T> p, Function<T, R> f, Function<T,Pair<T,T>> sub) {
        this.t = Optional.of(t);
        this.pred = p;
        this.func = f;
        this.sub = sub.andThen(x -> Pair.of(() -> x.first(), () -> x.second()));
    }

    DnC(Optional<Supplier<T>> t, Predicate<T> p, Function<T, R> f,
            Function<T,Pair<Supplier<T>,Supplier<T>>> sub) {
        this.t = t;
        this.pred = p;
        this.func = f;
        this.sub = sub;
    }

    static <T,R> DnC<T,R> of(T t, Predicate<T> p, Function<T, R> f) {
        return new DnC<T,R>(() -> t, p, f, x -> Pair.of(x, x));
    }

    static <T,R> DnC<T,R> of(T t, Predicate<T> p, Function<T, R> f, Function<T,Pair<T,T>> sub) {
        return new DnC<T,R>(() -> t, p, f, sub);
    }
    
    static <T,R> DnC<T,R> of(Supplier<T> t, Predicate<T> p, Function<T, R> f,
            Function<T,Pair<Supplier<T>,Supplier<T>>> sub) {
        return new DnC<T,R>(Optional.of(t), p, f, sub);
    }

    void peek(Consumer<T> c) {
        t.ifPresent(x -> c.accept(x.get()));
    }

    Optional<R> solve() {
        return t.map(x -> x.get()).filter(pred).map(func);
    }

    Optional<R> solve(BinaryOperator<R> op) {
        Optional<T> val = t.map(x -> x.get());
        return val.filter(pred).map(func).or(() ->
            left(val).solve(op).flatMap(x -> right(val).solve(op).map(y -> op.apply(x, y))));
    }

    private DnC<T,R> left(Optional<T> val) {
        return new DnC<T,R>(val.map(sub.andThen(x -> x.first())), pred, func, sub);
    }

    DnC<T,R> left() {
        return new DnC<T,R>(t.filter(x -> pred.test(x.get()))
                .or(() -> t.map(x -> x.get()).map(sub.andThen(x -> x.first()))),
                pred, func, sub);
    }

    private DnC<T,R> right(Optional<T> val) {
        return new DnC<T,R>(val.map(sub.andThen(x -> x.second())), pred, func, sub);
    }

    DnC<T,R> right() {
        return new DnC<T,R>(t.filter(x -> pred.test(x.get()))
                .or(() -> t.map(x -> x.get()).map(sub.andThen(x -> x.second()))),
                pred, func, sub);
    }
}
