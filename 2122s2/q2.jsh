double pow(double a, long b) {
    return Stream.<Double>generate(() -> a).limit(b).reduce(1.0, (acc, x) -> acc * x);
}

double seriesPi(long n) {
    return Stream.<Integer>iterate(1, x -> x + 1).limit(2 * n).filter(x -> x % 2 == 1)
        .<Double>map(x -> x % 4 == 1 ? 4.0 / x : 4.0 / -x).reduce(0.0, (acc, x) -> acc + x);
}

double approxPi(long n) {
    return Stream.<Point>generate(() -> new Point(getRand(), getRand()))
            .limit(n)
            .filter(x -> unitCircle.contains(x))
            .count() * 4.0 / n
}
