abstract class Event {
    private final double time;

    Event(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%.3f", time);
    }
}
