class Test {
    static void test(Object rec, Object exp) {
        if (!rec.toString().equals(exp.toString())) {
            System.out.printf("Expected: %s\n", exp.toString());
            System.out.printf("Received: %s\n", rec.toString());
            throw new Error("Error!");
        }
    }

    public static void main(String[] args) {
        System.out.println("-------------level 1--------------");
        test(Main.twinPrimes(100).toArray(), "int[15] { 3, 5, 7, 11, 13, 17, 19, 29, 31, 41, 43, 59, 61, 71, 73 }");
        test(Main.twinPrimes(100).toArray(), "int[15] { 3, 5, 7, 11, 13, 17, 19, 29, 31, 41, 43, 59, 61, 71, 73 }");
        test(Main.twinPrimes(100).count(), "15");
        Main.twinPrimes(2).forEach(System.out::println);
        test(Main.twinPrimes(2).count(), "0");
        Main.twinPrimes(3).forEach(System.out::println);
        System.out.println("--------------end-------------");
    }
}
