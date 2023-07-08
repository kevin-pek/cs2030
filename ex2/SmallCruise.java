class SmallCruise extends Cruise {
    private static final int DURATION = 30;

    SmallCruise(String id, int arrivalTime) {
        super(id, arrivalTime, 1, DURATION);
    }
}
