class Person {
    private static final int RISK_THRES = 8;
    private static final int DOSE_THRES = 2;
    private final String id;
    private final String vax;
    private final int risk;

    Person(String id, String vax, int risk) {
        this.id = id;
        this.vax = vax;
        this.risk = risk;
    }

    public boolean isHighRisk() {
        return risk >= RISK_THRES;
    }

    public boolean isVaccinated() {
        return vax.length() >= DOSE_THRES;
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", id, vax, isHighRisk() ? "HIGH" : "LOW");
    }
}
