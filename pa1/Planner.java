class Planner {
    private final ImList<AbstractTask> tasks;

    Planner() {
        this.tasks = new ImList<AbstractTask>();
    }

    Planner(ImList<AbstractTask> tasks) {
        this.tasks = tasks;
    }

    public Planner add(AbstractTask task) {
        return new Planner(tasks.add(task));
    }

    public void view(View view) {
        ImList<AbstractTask> newTasks = new ImList<AbstractTask>();
        for (int i = 0; i < tasks.size(); i++) {
            if (!tasks.get(i).isCancelled()) {
                for (AbstractTask task : tasks.get(i).getTasks()) {
                    if (!task.isCancelled()) {
                        newTasks = newTasks.add(task);
                    }
                }
            }
        }
        view.view(newTasks);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s = String.format("%s\n%s", s, tasks.get(i));
        }
        return s;
    }
}