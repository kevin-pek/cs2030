class DischargeMed implements Protocol {
    public Protocol next(Person p, Test t, int n) {
        return this;
    }

    @Override
    public String toString() {
        return "Discharged: seek medical attention";
    }
}
