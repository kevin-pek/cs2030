Log<Integer> sum(int n) {
    if (n == 0) {
        return Log.of(0, "hit base case!");
    }
    return sum(n - 1)
            .flatMap(x -> Log.of(n + x, String.format("adding %d", n)));
}

Log<Integer> f(int n) {
    if (n == 1) {
        return Log.of(1, "1");
    } else if(n % 2 == 0) {
        return Log.of(n, String.format("%d / 2", n)).flatMap(x -> f(n / 2));
    } else {
        return Log.of(n, String.format("3(%d) + 1", n))
                .flatMap(x -> f(3 * n + 1));
    }
}
