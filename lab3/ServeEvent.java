class ServeEvent extends Event {
    private final Server server;
    private final Customer customer;
    private final double serveTime;

    ServeEvent(Customer customer, Server server) {
        this(customer.getArrivalTime(), customer, server);
    }

    ServeEvent(double time, Customer customer, Server server) {
        super(time, customer);
        this.customer = customer;
        this.server = server;
        this.serveTime = customer.getServeTime();
    }

    private double getServiceEndTime() {
        return this.serveTime + this.getTime();
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        shop = shop.assignServer(this.server.getId(), getServiceEndTime());
        ImList<Event> events = new ImList<Event>()
                .add(new DoneEvent(getServiceEndTime(), customer, server));
        return new Pair<>(events, shop.customerServed());
    }

    @Override
    public String toString() {
        return String.format("%s serves by %s", super.toString(), server);
    }
}
