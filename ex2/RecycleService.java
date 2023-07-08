class RecycleService extends Service {
    private static final int HOUR = 60;
    private final Loader loader;
    private final Cruise cruise;

    RecycleService(Loader loader, Cruise cruise) {
        super(loader, cruise);
        this.loader = loader;
        this.cruise = cruise;
    }

    @Override
    public int getServiceEndTime() {
        return cruise.getArrivalTime() + cruise.getServiceTime() + HOUR;
    }

    @Override
    public Service serveNewCruise(Cruise cruise) {
        return new RecycleService(loader, cruise);
    }

    @Override
    public String toString() {
        return "Recycled " + super.toString();
    }
}
