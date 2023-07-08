// (a)
public LazyList<T> reverse() {
    if (this.isEmpty())
        return LazyList.makeEmpty();
    LazyList<T> tail = this.tail();
    
}

private LazyList<T> reverse(LazyList<T> l) {
    if (this.tail().isEmpty())
        return LLmake(this.head(), LazyList.makeEmpty());
    return LLmake( , l.concat(this.head()))
}
