import java.util.function.Predicate;
import java.util.function.Function;

class IfElse<T,U> {
    private final Predicate<T> pred;
    private final Function<T,U> left;
    private final Function<T,U> right;

    IfElse(Predicate<T> pred, Function<T,U> left, Function<T,U> right) {
        this.pred = pred;
        this.left = left;
        this.right = right;
    }

    static <T,U> IfElse<T,U> of(Predicate<T> p, Function<T,U> left, Function<T,U> right) {
        return new IfElse<T,U>(p, left, right);
    }

    U apply(T t) {
        return pred.test(t) ? left.apply(t) : right.apply(t);
    }

    IfElse<T,U> mapIf(IfElse<T,U> other) {
        return new IfElse<T,U>(pred.and(other.pred), other.left,
                x -> new IfElse<T,U>(pred, other.right, right).apply(x));
    }

    IfElse<T,U> mapElse(IfElse<T,U> other) {
        return new IfElse<T,U>(pred.or(other.pred),
                x -> new IfElse<T,U>(pred, left, other.left).apply(x),
                other.right);
    }

    <R> IfElse<T,R> map(Function<? super U, ? extends R> mapper) {
        return new IfElse<T,R>(pred, left.andThen(mapper), right.andThen(mapper));
    }

    <R> IfElse<T,R> flatMap(Function<? super U, ? extends IfElse<U, ? extends R>> mapper) {
        // return new IfElse<T,R>(pred,
        //         x -> mapper.apply(left.apply(x)).apply(left.apply(x)),
        //         x -> mapper.apply(right.apply(x)).apply(right.apply(x)));
        Function<T,R> f = x -> mapper.apply(apply(x)).apply(apply(x));
        return new IfElse<T,R>(pred, f, f);
    }
}
