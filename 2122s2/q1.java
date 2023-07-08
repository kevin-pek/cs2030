import java.util.List;

abstract class Student {
    private final String id;

    Student(String id) {
        this.id = id;
    }

    double getCAP() {
        return 5.0;
    }

    abstract double getLoan();

    String getId() {
        return id;
    }
}

class PG extends Student {
    PG(String id) {
        super(id);
    }

    double getLoan() {
        return 111.11;
    }
}

class UG extends Student {
    UG(String id) {
        super(id);
    }

    double getLoan() {
        return 99.99;
    }
}

interface Processable {
    void process(List<Student> students);
}

class Admin implements Processable {
    public void process(List<Student> students) {
        for (Student stu : students) {
            System.out.printf("%s : %f\n", stu.getId(), stu.getCAP());
        }
    }
}

class Bursar implements Processable {
    public void process(List<Student> students) {
        for (Student stu : students) {
            System.out.printf("%s : %f\n", stu.getId(), stu.getLoan());
        }
    }
}
