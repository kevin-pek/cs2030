import java.lang.Comparable;

class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;
    private final Service bestService;
    private final int bestFare;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
        ImList<Service> services = driver.getServices();
        int bestFare = request.computeFare(services.get(0));
        Service bestService = services.get(0);
        for (int i = 1; i < services.size(); i++) {
            Service service = services.get(i);
            int fare = request.computeFare(service);
            if (request.computeFare(service) < bestFare) {
                bestFare = fare;
                bestService = service;
            }
        }
        this.bestFare = bestFare;
        this.bestService = bestService;
    }

    Booking(Driver driver, Request request, Service service) {
        this.driver = driver;
        this.request = request;
        this.bestFare = request.computeFare(service);
        this.bestService = service;
    }

    public int getWaitingTime() {
        return driver.getWaitingTime();
    }

    public int getBestFare() {
        return bestFare;
    }

    @Override
    public int compareTo(Booking other) {
        if (this.bestFare == other.bestFare) {
            return this.getWaitingTime() - other.getWaitingTime();
        }
        return this.bestFare - other.bestFare;
    }

    @Override
    public String toString() {
        return String.format("$%d.%02d using %s (%s)", bestFare / 100,
                bestFare % 100, driver, bestService);
    }
}
