class ServeEvent extends Event {
    private final Customer customer;
    private final Server server;

    ServeEvent(double time, Customer customer, Server server) {
        super(time);
        this.customer = customer;
        this.server = server;
    }

    @Override
    public String toString() {
        return String.format("%s %s served by %s", super.toString(), customer, server);
    }
}
