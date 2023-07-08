class Task extends AbstractTask implements ActiveTask {
    Task(int day, int start, int end, String desc) {
        super(day, start, end, desc);
    }

    public Task edit(int start, int end) {
        return new Task(super.getDay(), start, end, super.getDesc());
    }

    public CancelledTask cancel() {
        return new CancelledTask(this, super.getDay(), super.getStart(),
                super.getEnd(), super.getDesc());
    }
}
