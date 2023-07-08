import java.util.Comparator;

class EventComp implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getTime() == e2.getTime()) {
            double comp = e1.getCustomerArrTime() - e2.getCustomerArrTime();
            if (comp == 0) {
                return 0;
            }
            return comp > 0 ? 1 : -1;
        }
        return e1.getTime() - e2.getTime() > 0 ? 1 : -1;
    }
}
