class TakeACab extends Service {
    private static final int FEE_PER_KM = 33;
    private static final int SURCHARGE = 200;

    TakeACab() {
        super(FEE_PER_KM, SURCHARGE);
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}
