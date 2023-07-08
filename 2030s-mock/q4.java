import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

abstract class MyStream<T> {
    static <T> MyStream<T> generate(Supplier<T> seed) {
        return new MyStream<T>() {
            public T get() {
                return seed.get();
            }

            public void forEach(Consumer<? super T> action, int n) {
                IntStream.range(0, n).forEach(i -> action.accept(seed.get()));
            }

            public <U> MyStream<U> map(Function<? super T, ? extends U> mapper) {
                return MyStream.generate(() -> mapper.apply(seed.get()));
            }
        };
    }

    abstract T get();

    abstract void forEach(Consumer<? super T> action, int n);

    abstract <U> MyStream<U> map(Function<? super T, ? extends U> mapper);
}
