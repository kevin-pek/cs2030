class LeaveEvent extends Event {
    LeaveEvent(Customer customer) {
        super(customer.getArrivalTime(), customer);
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
