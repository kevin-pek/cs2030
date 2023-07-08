public class WaitDoneEvent extends Event {
    private final Server server;
    private final Customer customer;

    WaitDoneEvent(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
        this.customer = customer;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        Server server = shop.getServer(this.server.getId() - 1);
        // if server has a customer in queue, serve the customer
        if (server.hasQueue() && server.getEndTime() <= getTime()) {
            ImList<Event> events = new ImList<Event>()
                    .add(new ServeEvent(getTime(), customer, server));
            shop = shop.addWaitTime(getTime() - customer.getArrivalTime());
            return new Pair<>(events, shop);
        }
        // if server still has customer in queue, schedule next wait done event
        ImList<Event> events = new ImList<Event>()
                .add(new WaitDoneEvent(server.getEndTime(), customer, server));
        return new Pair<>(events, shop);
    }

    @Override
    public String toString() {
        return "";
    }
}
