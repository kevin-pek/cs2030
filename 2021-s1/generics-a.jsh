//**** Generics (a)
//**** Write your answer below

<T> void replace(List<T> src, List<T> dst,
        BiPredicate<? super T, ? super T> cond) {
    if (src.size() == dst.size()) {
        for (int i = 0; i < src.size(); i++) {
            if (cond.test(src.get(i), dst.get(i))) {
                dst.set(i, src.get(i));
            }
        }
    }
}
