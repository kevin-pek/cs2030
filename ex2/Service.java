class Service {
    private final Loader loader;
    private final Cruise cruise;

    Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }

    public int getServiceStartTime() {
        return cruise.getArrivalTime();
    }
    
    public int getServiceEndTime() {
        return cruise.getServiceTime() + cruise.getArrivalTime();
    }

    public Service serveNewCruise(Cruise cruise) {
        return new Service(loader, cruise);
    }

    @Override
    public String toString() {
        return loader.toString() + " serving " + cruise.toString();
    }
}
