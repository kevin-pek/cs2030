interface ActiveTask {
    ActiveTask edit(int start, int end);
    AbstractTask cancel();
}
