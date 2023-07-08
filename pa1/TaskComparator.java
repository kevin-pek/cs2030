import java.util.Comparator;

class TaskComparator implements Comparator<AbstractTask> {
    @Override
    public int compare(AbstractTask t1, AbstractTask t2) {
        if (t1.getDay() == t2.getDay()) {
            return t1.getStart() - t2.getStart();
        }
        return t1.getDay() - t2.getDay();
    }
}
