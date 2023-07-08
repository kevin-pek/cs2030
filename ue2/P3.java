class P3 implements Protocol {
    private static final int MIN = 5;

    public Protocol next(Person p, Test t, int n) {
        if (t.isPositive()) {
            if (p.isHighRisk()) {
                return new P1();
            } else {
                return new P2();
            }
        }
        if (n >= MIN) {
            return new Discharge("complete");
        }
        return this;
    }

    @Override
    public String toString() {
        return "P3";
    }
}
