import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;

interface Maybe<T> {
    T get();

    boolean isEmpty();

    boolean isPresent();

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper);

    <R> Maybe<R> flatMap(
            Function<? super T, ? extends Maybe<? extends R>> mapper);
    
    Maybe<T> filter(Predicate<? super T> pred);
    
    void ifPresent(Consumer<? super T> action);
    
    void ifPresentOrElse(Consumer<? super T> action, Runnable runnable);
    
    T orElse(T t);
    
    T orElseGet(Supplier<? extends T> supplier);

    Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier);

    static <T> Maybe<T> empty() {
        return Maybe.<T>of(null);
    }

    static <T> Maybe<T> of(T t) {
        return new Maybe<T>() {
            private final T value = t;

            public T get() {
                return this.value;
            }

            public boolean isEmpty() {
                return this.value == null;
            }

            public boolean isPresent() {
                return !this.isEmpty();
            }

            public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
                if (this.isEmpty()) {
                    return Maybe.empty();
                } else {
                    return Maybe.of(mapper.apply(this.value));
                }
            }

            public <R> Maybe<R> flatMap(
                    Function<? super T, ? extends Maybe<? extends R>> mapper) {
                if (isEmpty()) {
                    return Maybe.empty();
                } else {
                    Maybe<? extends R> m = mapper.apply(get());
                    return Maybe.<R>of(m.get());
                }
            }

            public Maybe<T> filter(Predicate<? super T> pred) {
                if (isEmpty()) {
                    return Maybe.<T>empty();
                } else if (!pred.test(get())) {
                    return Maybe.<T>empty();
                }
                return Maybe.<T>of(get());
            }

            public void ifPresent(Consumer<? super T> action) {
                if (isPresent()) {
                    action.accept(get());
                }
            }

            public void ifPresentOrElse(Consumer<? super T> action, Runnable runnable) {
                if (isPresent()) {
                    action.accept(get());
                } else {
                    runnable.run();
                }
            }

            public T orElse(T t) {
                if (isPresent()) {
                    return get();
                }
                return t;
            }

            public T orElseGet(Supplier<? extends T> supplier) {
                if (isPresent()) {
                    return get();
                }
                return supplier.get();
            }

            public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
                if (isPresent()) {
                    return Maybe.<T>of(get());
                }
                Maybe<? extends T> m = supplier.get();
                return Maybe.<T>of(m.get());
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                } else if (obj instanceof Maybe<?> other) {
                    if (isEmpty()) {
                        return other.isEmpty();
                    }
                    return !other.isEmpty() && !other.filter(x -> x.equals(get())).isEmpty();
                }
                return false;
            }

            @Override
            public String toString() {
                if (this.value == null) {
                    return "Maybe.empty";
                } else {
                    return "Maybe[" + this.value + "]";
                }
            }
        };
    }
}
