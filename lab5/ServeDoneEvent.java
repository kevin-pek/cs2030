class ServeDoneEvent extends Event {
    private final Server server;

    ServeDoneEvent(double time, Customer customer, Server server) {
        super(time, customer);
        this.server = server;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        Server server = shop.getServer(this.server.getId()).doneServing();
        double restTime = server.getRestTime();
        if (restTime > 0.0) {
            server = server.startRest(restTime);
            return new Pair<>(new ImList<Event>()
                    .add(new RestDoneEvent(getTime() + restTime, getCustomer(), server)),
                    shop.updateServer(this.server.getId(), server));
        }
        // update server to remove customer from queue if there is no WaitDoneEvent
        if (!server.hasQueue()) {
            server = server.moveQueue();
        }
        return new Pair<>(new ImList<>(),
                shop.updateServer(this.server.getId(), server));
    }

    @Override
    public String toString() {
        return String.format("%s done serving by %s", super.toString(), server);
    }
}
