import java.util.function.Supplier;

class Customer {
    private final double arrivalTime;
    private final Supplier<Double> serveTime;
    private final int id;

    Customer(int id, double arrivalTime, Supplier<Double> serveTime) {
        this.arrivalTime = arrivalTime;
        this.serveTime = serveTime;
        this.id = id;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    // should only be called once per customer
    public double getServeTime() {
        return this.serveTime.get();
    }

    @Override
    public String toString() {
        return String.format("%d", id);
    }
}
