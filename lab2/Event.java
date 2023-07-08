abstract class Event {
    private final double time;
    private final Customer customer;

    Event(Customer customer) {
        this.time = customer.getArrivalTime();
        this.customer = customer;
    }

    Event(double time, Customer customer) {
        this.time = time;
        this.customer = customer;
    }

    public double getTime() {
        return time;
    }

    public double getCustomerArrTime() {
        return customer.getArrivalTime();
    }

    /**
     * Returns list of generated events and the list of the servers
     * in the state after the event has been executed.
     */
    public abstract Pair<ImList<Event>, Shop> execute(Shop shop);

    @Override
    public String toString() {
        return String.format("%.3f %s", this.time, this.customer);
    }
}
