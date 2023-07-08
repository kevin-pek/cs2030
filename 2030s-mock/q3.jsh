// (a)
ImList<Integer> apply(ImList<Integer> list, Function<Integer, Integer> fn) {
    return new ImList<>(list.stream().map(fn).toList());
}

// (b)
ImList<Integer> convert(ImList<Integer> list,
        Function<ImList<Integer>, Stream<Integer>> fn) {
    return new ImList<>(fn.apply(list).toList());
}

System.out.println(apply(new ImList<>(List.of(1,2,3)), x-> x+1))

// (c)
System.out.println(convert(new ImList<>(List.of(1,2,3)), x ->
        IntStream.range(0, x.size() - 1)
                .boxed()
                .map(y -> x.get(y) * x.get(y + 1))
                )
)

// (e)
void conway(ImList<Integer> list, UnaryOperator<ImList<Integer>> r, int n) {
    Stream.iterate(list, r).limit(n)
            .forEach(x -> System.out.println(x.stream().map(b -> b == 1 ? "*" : " ")
                    .reduce("", (a,b) -> a + b)));
}

UnaryOperator<ImList<Integer>> r = x ->
        new ImList<>(IntStream.range(0, x.size()).map(e -> {
            if (x.get(e) == 1) {
                return 0;
            }
            int count = 0;
            if (e < x.size() - 1 && x.get(e + 1) == 1) {
                count++;
            }
            if (e > 0 && x.get(e - 1) == 1) {
                count++;
            }
            return count == 1 ? 1 : 0;
        }).boxed().toList())

conway(new ImList<>(List.of(1, 0, 0)), r, 4)
