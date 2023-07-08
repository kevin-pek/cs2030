class ArrivalEvent extends Event {
    private final Customer customer;

    ArrivalEvent(Customer customer) {
        super(customer);
        this.customer = customer;
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        Pair<Integer, Shop> serveResult = shop.assignServer(this.customer);
        Shop newShop = serveResult.second();
        ImList<Event> events = new ImList<>();
        if (serveResult.first() == -1) {
            events = events.add(new LeaveEvent(customer.getArrivalTime(), customer));
        } else {
            Server server = newShop.getServer(serveResult.first());
            events = new ImList<Event>()
                .add(new ServeEvent(customer, server));
        }
        return new Pair<>(events, newShop);
    }

    @Override
    public String toString() {
        return String.format("%s arrives", super.toString());
    }
}
