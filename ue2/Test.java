abstract class Test {
    private final String type;
    
    abstract boolean isValid();

    abstract boolean isPositive();

    Test(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s%s", type, isValid() ? isPositive() ? "+" : "-" : "X");
    }
}
