// Write the entire class for q5(a) below. Do not remove this comment.

import java.util.List;
import java.util.function.Function;

abstract class Command<T> {
    private final List<String> params;
    private final Function<T,Pair<String,T>> func;
    private final Log<Function<T,Pair<String,T>>> log;

    Command(List<String> params, Function<T,Pair<String,T>> f) {
        this.params = params;
        this.func = f;
        this.log = Log.of(f);
    }

    Pair<Log,T> execute(T t) {
        return new Pair<>(log.flatMap(x -> Log.of(func.apply(t).first())),
                func.apply(t).second());
    }

    String getLog() {
        return log.getLog();
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
