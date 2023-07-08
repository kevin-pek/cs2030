class DoneEvent extends Event {
    private final Server server;

    DoneEvent(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        return new Pair<>(new ImList<>(), shop.doneServing(server.getId()));
    }

    @Override
    public String toString() {
        return String.format("%s done serving by %s", super.toString(), server);
    }
}
