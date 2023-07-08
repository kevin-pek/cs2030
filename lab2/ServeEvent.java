class ServeEvent extends Event {
    private final Server server;
    private final Customer customer;

    ServeEvent(Customer customer, Server server) {
        super(customer);
        this.customer = customer;
        this.server = server;
    }

    private double getServiceEndTime() {
        return this.customer.getServeTime() + this.getTime();
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        ImList<Event> events = new ImList<Event>()
                .add(new DoneEvent(getServiceEndTime(), customer, server));
        return new Pair<>(events, shop.customerServed());
    }

    @Override
    public String toString() {
        return String.format("%s serves by %s", super.toString(), server);
    }
}
