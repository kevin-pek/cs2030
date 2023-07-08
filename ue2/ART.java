class ART extends Test {
    private static final String POS = "CT";
    private static final String INV = "T";
    private final String result;

    ART(String r) {
        super("A");
        this.result = r;
    }

    public boolean isValid() {
        return !result.equals(INV);
    }

    public boolean isPositive() {
        return result.equals(POS);
    }
}
