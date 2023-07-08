class ShareARide extends Service {
    private static final int FEE_PER_KM = 50;
    private static final int SURCHARGE = 0;
    private static final int PEAK_CHARGE = 500;
    private static final int PEAK_START = 600;
    private static final int PEAK_END = 900;
    
    ShareARide() {
        super(FEE_PER_KM, SURCHARGE);
    }

    @Override
    public int computeFare(int dist, int nPax, int time) {
        int fare = super.computeFare(dist, nPax, time);
        if (time <= PEAK_END && time >= PEAK_START) {
            fare += PEAK_CHARGE;
        }
        return fare / nPax;
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}
