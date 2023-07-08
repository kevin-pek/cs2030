class NormalCab extends Driver {
    private static final ImList<Service> SERVICES =
            new ImList<Service>().add(new JustRide()).add(new TakeACab());

    NormalCab(String license, int waitTime) {
        super(license, waitTime, SERVICES);
    }

    @Override
    public String toString() {
        return String.format("%s NormalCab", super.toString());
    }
}
