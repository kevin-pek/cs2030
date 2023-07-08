import java.util.function.Function;

abstract class Maybe<T> {
    static <T> Maybe<T> of(T thing) {
        return new Maybe<T>() {
            private final T t = thing;
            
            <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                return Maybe.<R>of(mapper.apply(this.t));
            }

            @Override
            public String toString() {
                return "Maybe[" + t + "]";
            }
        };
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>() {
            <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                return Maybe.<R>empty();
            }

            @Override
            public String toString() {
                return "Maybe.empty";
            }
        };
    }
}
