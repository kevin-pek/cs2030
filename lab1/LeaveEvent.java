class LeaveEvent extends Event {
    private final Customer customer;

    LeaveEvent(double time, Customer customer) {
        super(time);
        this.customer = customer;
    }

    @Override
    public String toString() {
        return String.format("%s %s leaves", super.toString(), customer);
    }
}
