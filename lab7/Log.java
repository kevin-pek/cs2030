import java.util.function.Function;
import java.util.Optional;

class Log<T> {
    private final T t;
    private final String log;
    
    Log(T t, String log) {
        this.t = t;
        this.log = log;
    }

    static <T> Log<T> of(T t) {
        return Log.of(t, "");
    }

    static <T> Log<T> of(T t, String log) {
        return Optional
                .ofNullable(t)
                .filter(x -> !(t instanceof Log))
                .map(x -> new Log<T>(t, log))
                .filter(x -> !Optional.ofNullable(x.getString()).equals(Optional.empty()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }

    T get() {
        return t;
    }

    String getString() {
        return log;
    }

    <U> Log<U> map(Function<? super T, ? extends U> f) {
        return Log.of(f.apply(t), log);
    }

    <U> Log<U> flatMap(Function<? super T, ? extends Log<? extends U>> f) {
        Log<? extends U> l = f.apply(get());
        String log = getString();
        if (!l.getString().equals("") && !log.equals("")) {
            log = String.format("%s\n%s", log, l.getString());
        } else if (log.equals("")) {
            log = l.getString();
        }
        return Log.of(l.get(), log);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Log<?> other) {
            return getString().equals(other.getString()) && get().equals(other.get());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Log[%s]%s", t.toString(),
                log.equals("") ? "" : "\n" + log);
    }

}
