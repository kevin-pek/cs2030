class ArrivalEvent extends Event {
    private final Customer customer;

    ArrivalEvent(Customer customer) {
        super(customer);
        this.customer = customer;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        ImList<Event> events = new ImList<>();
        int i = shop.getIdleServer();
        if (i != -1) {
            events = events.add(new ServeEvent(customer, shop.getServer(i)));
            return new Pair<>(events, shop);
        }
        i = shop.getAvailServer();
        if (i == -1) {
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
