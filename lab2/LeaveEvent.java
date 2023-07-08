class LeaveEvent extends Event {
    LeaveEvent(double time, Customer customer) {
        super(time, customer);
    }

    @Override
    public Pair<ImList<Event>, Shop> execute(Shop shop) {
        return new Pair<>(new ImList<>(), shop.customerLeft());
    }

    @Override
    public String toString() {
        return String.format("%s leaves", super.toString());
    }
}
