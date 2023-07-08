abstract class Driver {
    private final String licensePlate;
    private final int waitingTime;
    private final ImList<Service> services;

    Driver(String license, int waitingTime, ImList<Service> services) {
        this.licensePlate = license;
        this.waitingTime = waitingTime;
        this.services = services;
    }

    public int getWaitingTime() {
        return this.waitingTime;
    }

    public ImList<Service> getServices() {
        return this.services;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away)", licensePlate, waitingTime);
    }
}
