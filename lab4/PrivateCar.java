class PrivateCar extends Driver {
    private static final ImList<Service> SERVICES =
            new ImList<Service>().add(new JustRide()).add(new ShareARide());

    PrivateCar(String license, int waitTime) {
        super(license, waitTime, SERVICES);
    }

    @Override
    public String toString() {
        return String.format("%s PrivateCar", super.toString());
    }
}
