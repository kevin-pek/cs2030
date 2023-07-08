import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.Optional;

class ImList<E> implements Iterable<E> {
    private final ArrayList<E> elems;

    ImList() {
        this.elems = new ArrayList<E>();
    }

    Stream<E> stream() {
        return this.elems.stream();
    }

    ImList(List<? extends E> list) {
        this.elems = new ArrayList<E>(list);
    }

    ImList<E> add(E elem) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.add(elem);
        return newList;
    }

    ImList<E> addAll(ImList<? extends E> list) {
        ImList<E> newList = new ImList<E>(this.elems);
        newList.elems.addAll(list.elems);
        return newList;
    }
    public ImList<E> set(int index, E elem) {
        ImList<E> newList = new ImList<E>(this.elems);
        if (index >= 0 && index < this.size()) {
            newList.elems.set(index, elem);
        }
        return newList;
    }

    E get(int index) {
        return this.elems.get(index);
    }

    boolean isEmpty() {
        return this.elems.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return this.elems.iterator();
    }

    <U> U reduce(U identity, BiFunction<? super U, ? super E, ? extends U> acc) {
        for (E t : this) {
            identity = acc.apply(identity, t);
        }
        return identity;
    }

    // Optional<E> reduce(Function<? super E, ? extends Function<? super E, ? extends E>> f) {
    //     return isEmpty() ? Optional.empty() :
    //             Optional.of(get(0))
    //                     .map(x -> remove(0).reduce(x, (a, b) -> f.apply(a).apply(b)));
    // }

    ImList<E> remove(int index) {
        ImList<E> newList = new ImList<E>(this.elems);
        if (index < this.size()) {
            newList.elems.remove(index);
        }
        return newList;
    }

    int size() {
        return this.elems.size();
    }

    @Override
    public String toString() {
        return this.elems.toString();
    }
}
