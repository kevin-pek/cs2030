class WaitEvent extends Event {
    private final Server server;
    private final Customer customer;

    WaitEvent(Customer customer, Server server) {
        super(customer);
        this.server = server;
        this.customer = customer;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        Server server = shop.getServer(this.server.getId())
                .queueCustomer(this.customer);
        shop = shop.updateServer(this.server.getId(), server);
        ImList<Event> events = new ImList<Event>()
                .add(new WaitDoneEvent(server.getEndTime(), customer, server));
        return new Pair<>(events, shop);
    }

    @Override
    public String toString() {
        return String.format("%s waits at %s", super.toString(), server);
    }
}
