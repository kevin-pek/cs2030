// Answer 2a below. Do not remove this comment.
<T> ImList<T> reverse(List<? extends T> list) {
    return list.stream()
        .map(x -> new ImList<T>().add(x))
        .reduce(new ImList<T>(), (x, y) -> y.addAll(x));
}

// Answer 2b below. Do not remove this comment.
<T> List<Pair<T,T>> pairing(List<? extends T> list) {
    return IntStream.range(0, list.size() / 2)
        .mapToObj(i -> new Pair<>(list.get(2 * i), list.get(2 * i + 1)))
        .toList();
}

// Answer 2c below. Do not remove this comment.
<T> List<T> merging(List<? extends Pair<? extends T, ? extends T>> list) {
    return list.stream()
        .flatMap(p -> Stream.of(p.first(), p.second()))
        .toList();
}
