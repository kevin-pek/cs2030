class RecurringTask extends AbstractTask implements ActiveTask {
    private final AbstractTask task;
    private final int freq;
    private final int num;
    private final ImList<AbstractTask> tasks;

    RecurringTask(AbstractTask task, int freq, int num) {
        super(task);
        this.task = task;
        this.freq = freq;
        this.num = num;
        ImList<AbstractTask> tasks = new ImList<AbstractTask>();
        for (int i = 0; i < num; i++) {
            tasks = tasks.add(new Task(getDay() + freq * i, getStart(), getEnd(), getDesc()));
        }
        this.tasks = tasks;
    }

    RecurringTask(AbstractTask task, int freq, ImList<AbstractTask> tasks) {
        super(task);
        this.task = task;
        this.tasks = tasks;
        this.freq = freq;
        this.num = tasks.size();
    }

    public RecurringTask edit(int start, int end) {
        return new RecurringTask(new Task(getDay(), start, end, getDesc()), freq, num);
    }

    public CancelledTask cancel() {
        return new CancelledTask(this, getDay(), getStart(), getEnd(), getDesc());
    }

    public RecurringTask edit(int idx, int day, int start, int end) {
        ImList<AbstractTask> newTasks = new ImList<>();
        for (int i = 0; i < num; i++) {
            if (i + 1 == idx) {
                newTasks = newTasks.add(new Task(day, start, end, getDesc()));
            } else {
                newTasks = newTasks.add(tasks.get(i));
            }
        }
        return new RecurringTask(task, freq, newTasks.sort(new TaskComparator()));
    }

    public RecurringTask cancel(int idx) {
        ImList<AbstractTask> newTasks = new ImList<>();
        for (int i = 0; i < num; i++) {
            if (i + 1 == idx) {
                newTasks = newTasks.add(new CancelledTask(tasks.get(i)));
            } else {
                newTasks = newTasks.add(tasks.get(i));
            }
        }
        return new RecurringTask(task, freq, newTasks.sort(new TaskComparator()));
    }
    
    public String getHeader() {
        return String.format("Recurring %s", task.toString());
    }

    public ImList<AbstractTask> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        String s = getHeader();
        for (int i = 0; i < num; i++) {
            s = String.format("%s\n#%d:%s", s, i + 1, tasks.get(i));
        }
        return s;
    }
}
