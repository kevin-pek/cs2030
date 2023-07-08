class ArrivalEvent extends Event {
    private final Customer customer;

    ArrivalEvent(Customer customer) {
        super(customer);
        this.customer = customer;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        ImList<Event> events = new ImList<>();
        // get the first idle server
        int i = shop.getIdleServerId();
        if (i != -1) {
            events = events.add(new ServeEvent(customer, shop.getServer(i)));
            return new Pair<>(events, shop);
        }
        // if no idle server, check if there is a server with available queue space
        i = shop.getAvailServerId();
        if (i == -1) {
            // leave if no queue space available
            events = events.add(new LeaveEvent(customer));
        } else {
            events = events.add(new WaitEvent(customer, shop.getServer(i)));
        }
        return new Pair<>(events, shop);
    }

    @Override
    public String toString() {
        return String.format("%s arrives", super.toString());
    }
}
