class P2 implements Protocol {
    private static final int MIN = 3;
    private static final int MAX_LOW = 7;
    private static final int MAX_HIGH = 14;

    public Protocol next(Person p, Test t, int n) {
        if (n < MIN) {
            return this;
        } else if ((p.isVaccinated() && n >= MAX_LOW) || (!p.isVaccinated() && n >= MAX_HIGH)
                || !t.isPositive()) {
            return new Discharge("complete");
        }
        return this;
    }

    @Override
    public String toString() {
        return "P2";
    }
}
