class Discharge implements Protocol {
    private final String msg;

    Discharge(String s) {
        this.msg = s;
    }

    public Protocol next(Person p, Test t, int n) {
        if (t.isPositive()) {
            return new DischargeMed();
        }
        return this;
    }

    @Override
    public String toString() {
        return "Discharged: " + msg;
    }
}
