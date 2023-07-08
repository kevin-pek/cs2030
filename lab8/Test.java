class Test {
    static void test(Object rec, Object exp) {
        if (!rec.toString().equals(exp.toString())) {
            System.out.printf("Expected: %s\n", exp.toString());
            System.out.printf("Received: %s\n", rec.toString());
            throw new Error("Error!");
        }
    }

    public static void main(String[] args) {
        System.out.println("-------------level 4--------------");
        Num zero = Num.zero();
        Num one = Num.one();
        test(Fraction.of(1, 2), "1 : 2");
        test(Fraction.of(2, 1), "2 : 1");
        test(Fraction.of(2, 2), "2 : 2");
        test(Fraction.of(0, 2), "0 : 2");
        test(Fraction.of(2, 0), "NaN");
        test(Fraction.of(-1, 2), "NaN");
        test(Fraction.of(1, -2), "NaN");
        System.out.println("--------------end-------------");
        System.out.println("-------------level 5--------------");
        test(Fraction.of(1, 2).add(Fraction.of(1, 4)), "6 : 8");
        test(Fraction.of(-1, 2).add(Fraction.of(1, 4)), "NaN");
        test(Fraction.of(1, 2).sub(Fraction.of(1, 4)), "2 : 8");
        test(Fraction.of(1, 4).sub(Fraction.of(1, 2)), "NaN");
        test(Fraction.of(1, 2).mul(Fraction.of(2, 1)), "2 : 2");
        test(Fraction.of(1, 2).mul(Fraction.of(2, 0)), "NaN");
        System.out.println("--------------end-------------");
    }
}
