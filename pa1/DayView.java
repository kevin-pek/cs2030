class DayView implements View {
    private final int day;

    DayView(int day) {
        this.day = day;
    }

    public void view(ImList<AbstractTask> tasks) {
        tasks = tasks.sort(new TaskComparator());
        for (int i = 0; i < tasks.size(); i++) {
            AbstractTask task = tasks.get(i);
            if (task.getDay() == day) {
                String s = String.format("Task: %d,%d,%d,%s", task.getDay(), task.getStart(),
                        task.getEnd(), task.getDesc());
                System.out.println(s);
            }
        }
    }
}