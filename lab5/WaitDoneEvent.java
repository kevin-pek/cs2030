public class WaitDoneEvent extends Event {
    private final Server server;

    WaitDoneEvent(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        Server server = shop.getServer(this.server.getId());
        ImList<Event> events = new ImList<>();
        if (server.getEndTime() <= getTime()) {
            // if server has a customer in queue, serve the customer
            server = server.moveQueue();
            shop = shop.updateServer(server.getId(), server);
            events = events.add(new ServeEvent(getTime(), getCustomer(), server));
            shop = shop.addWaitTime(getTime() - getCustomerArrTime());
        } else {
            // if server still has customer in queue, schedule next wait done event
            events = events.add(new WaitDoneEvent(server.getEndTime(), getCustomer(), server));
        }
        return new Pair<>(events, shop);
    }

    @Override
    public String toString() {
        return "";
    }
}
