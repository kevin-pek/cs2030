class PCR extends Test {
    private static final ImList<String> strains =
        new ImList<String>().add("alpha").add("beta").add("delta").add("omicron");
    private final String result;

    PCR(String result) {
        super("P");
        this.result = result;
    }

    public boolean isValid() {
        return true;
    }

    public boolean isPositive() {
        for (String s : strains) {
            if (s.equals(result)) {
                return true;
            }
        }
        return false;
    }
}
