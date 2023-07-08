// (a)
ImList<T> update(Consumer<ImList<T>> action) {
    ImList<T> newList = new ImList<T>(this.list);
    action.accept(newList);
    return newList;
}

// (b)
ImList<T> add(T item) {
    return update(x -> x.list.add(item));
}

ImList<T> set(int i, T item) {
    return update(x -> x.list.set(i, item));
}
