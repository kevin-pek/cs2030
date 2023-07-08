abstract class AbstractTask {
    private final int day;
    private final int start;
    private final int end;
    private final String desc;

    AbstractTask(int day, int start, int end, String desc) {
        this.day = day;
        this.start = start;
        this.end = end;
        this.desc = desc;
    }

    AbstractTask(AbstractTask task) {
        this.day = task.getDay();
        this.start = task.getStart();
        this.end = task.getEnd();
        this.desc = task.getDesc();
    }

    public int getDay() {
        return day;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isCancelled() {
        return false;
    }

    public String getHeader() {
        return String.format("Task: %d,%d,%d,%s", day, start, end, desc);
    }

    public ImList<AbstractTask> getTasks() {
        return new ImList<AbstractTask>().add(this);
    }

    @Override
    public String toString() {
        return getHeader();
    }
}
