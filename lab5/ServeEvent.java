class ServeEvent extends Event {
    private final Server server;
    private final double serveTime;

    ServeEvent(Customer customer, Server server) {
        this(customer.getArrivalTime(), customer, server);
    }

    ServeEvent(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
        this.serveTime = customer.getServeTime();
    }

    private double getServiceEndTime() {
        return this.serveTime + this.getTime();
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        Server server = shop.getServer(this.server.getId())
                .serveCustomer(getServiceEndTime());
        shop = shop.updateServer(this.server.getId(), server);
        ImList<Event> events = new ImList<Event>()
                .add(new ServeDoneEvent(getServiceEndTime(), getCustomer(), server));
        return new Pair<>(events, shop.customerServed());
    }

    @Override
    public String toString() {
        return String.format("%s serves by %s", super.toString(), server);
    }
}
