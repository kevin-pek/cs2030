class JustRide extends Service {
    private static final int FEE_PER_KM = 22;
    private static final int SURCHARGE = 0;
    private static final int PEAK_CHARGE = 500;
    private static final int PEAK_START = 600;
    private static final int PEAK_END = 900;

    JustRide() {
        super(FEE_PER_KM, SURCHARGE);
    }

    public int computeFare(int dist, int nPax, int time) {
        int fare = super.computeFare(dist, nPax, time);
        if (time <= PEAK_END && time >= PEAK_START) {
            fare += PEAK_CHARGE;
        }
        return fare;
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}
