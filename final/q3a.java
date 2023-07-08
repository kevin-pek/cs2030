// Write the entire class for q3(a) below. Do not remove this comment.
// You will need to add another method in q4(a) to this class later.

import java.util.List;
import java.util.function.Function;

abstract class Command<T> {
    private final List<String> params;
    private final Function<T,T> func;

    Command(List<String> params, Function<T,T> f) {
        this.params = params;
        this.func = f;
    }

    T execute(T t) {
        return func.apply(t);
    }

    Command<T> andThen(Command<T> other) {
        return new Command<T>() {
            T execute(T t) {
                func.apply(t);
                return other.execute(t);
            }
        };
    }
}
