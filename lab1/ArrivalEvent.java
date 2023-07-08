class ArrivalEvent extends Event {
    private final Customer customer;

    ArrivalEvent(double time, Customer customer) {
        super(time);
        this.customer = customer;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %s arrives", customer);
    }
}
