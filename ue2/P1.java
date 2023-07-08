class P1 implements Protocol {
    public Protocol next(Person p, Test t, int n) {
        if (t.isPositive()) {
            if (p.isHighRisk()) {
                return this;
            } else {
                return new P2();
            }
        }
        return new Discharge("follow up with doctor");
    }

    @Override
    public String toString() {
        return "P1";
    }
}
