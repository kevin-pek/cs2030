import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.Optional;

// Answer 4a below. Do not remove this comment.
Optional<E> reduce(Function<? super E, ? extends Function<? super E, ? extends E>> f) {
        return isEmpty() ? Optional.empty() :
                Optional.of(get(0))
                        .map(x -> remove(0).reduce(x, (a, b) -> f.apply(a).apply(b)));
}

// Answer 4b below. Do not remove this comment.
interface ImList<T> extends Iterable<T> {
    T get(int i);
    
    boolean isEmpty();

    ImList<T> remove(int i);

    int size();

    <U> U reduce(U id, BiFunction<? super U, ? super T, ? extends U> f);

    Optional<T> reduce(Function<? super T, ? extends Function<? super T, ? extends T>> f);

    static <E> ImList<E> of() {
        return ImList.of(List.of());
    }

    static <E> ImList<E> of(List<? extends E> list) {
        return new ImList<E>() {
            private final ArrayList<E> elems = new ArrayList<E>(list);

            public E get(int index) {
                return this.elems.get(index);
            }

            public boolean isEmpty() {
                return this.elems.isEmpty();
            }
            
            public int size() {
                return this.elems.size();
            }

            @Override
            public Iterator<E> iterator() {
                return this.elems.iterator();
            }
            
            public ImList<E> remove(int index) {
                List<E> newList = new ArrayList<E>(this.elems);
                if (index < this.size()) {
                    newList.remove(index);
                }
                return ImList.of(newList);
            }

            public <U> U reduce(U identity, BiFunction<? super U, ? super E, ? extends U> acc) {
                for (E t : this) {
                    identity = acc.apply(identity, t);
                }
                return identity;
            }

            public Optional<E> reduce(Function<? super E, ? extends Function<? super E, ?
                    extends E>> f) {
                return isEmpty() ? Optional.empty() :
                        Optional.of(get(0))
                        .map(x -> remove(0).reduce(x, (a, b) -> f.apply(a).apply(b)));
            }
        };
    }
}
