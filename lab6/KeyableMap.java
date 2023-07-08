import java.util.Map;
import java.util.Optional;

abstract class KeyableMap<V extends Keyable> implements Keyable {
    private final ImmutableMap<String, V> map;
    private final String key;

    KeyableMap(String key) {
        this.key = key;
        this.map = new ImmutableMap<>();
    }

    KeyableMap(String key, ImmutableMap<String, V> map) {
        this.key = key;
        this.map = map;
    }

    public String getKey() {
        return this.key;
    }

    public Optional<V> get(String key) {
        return this.map.get(key);
    }

    protected ImmutableMap<String, V> putItem(V item) {
        return this.map.put(item.getKey(), item);
    }

    public abstract KeyableMap<V> put(V item);

    @Override
    public String toString() {
        String printString = "";
        int i = 0;
        for (Map.Entry<String, V> e : map) {
            if (i == 0) {
                printString = e.getValue().toString();
            } else {
                printString = String.format(
                        "%s, %s",
                        printString,
                        e.getValue());
            }
            i++;
        }
        return String.format(
                "%s: {%s}",
                this.getKey(),
                printString);
    }
}
