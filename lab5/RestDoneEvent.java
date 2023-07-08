class RestDoneEvent extends Event {
    private final Server server;

    RestDoneEvent(double time,Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        Server server = shop.getServer(this.server.getId()).endRest();
        shop = shop.updateServer(this.server.getId(), server);
        return new Pair<>(new ImList<>(), shop);
    }
    
    @Override
    public String toString() {
        return "";
    }
}
