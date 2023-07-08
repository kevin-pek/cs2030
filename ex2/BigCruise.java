class BigCruise extends Cruise {
    private static final int LOADER = 40;
    private static final int DURATION = 50;

    BigCruise(String id, int arrivalTime, int length, int numPassengers) {
        super(id, arrivalTime, (length + LOADER - 1) / LOADER,
                (numPassengers + DURATION - 1) / DURATION);
    }
}
