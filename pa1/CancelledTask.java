class CancelledTask extends AbstractTask {
    private final AbstractTask task;

    CancelledTask(AbstractTask task, int day, int start, int end, String desc) {
        super(day, start, end, desc);
        this.task = task;
    }

    CancelledTask(AbstractTask task) {
        this(task, task.getDay(), task.getStart(), task.getEnd(), task.getDesc());
    }

    @Override
    public boolean isCancelled() {
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("%s[cancelled]", task.getHeader());
    }
}
