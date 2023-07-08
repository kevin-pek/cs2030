import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

// Answer 3a below. Do not remove this comment.
interface Level {
    int getMarks();
}

class PA {
    private final List<Level> levels;

    PA(List<Level> levels) {
        this.levels = levels;
    }

    Stream<Integer> getMarks() {
        return levels.stream()
                .map(x -> x.getMarks());
    }
}

// Answer 3b below. Do not remove this comment.
class Student {
    private final List<Optional<PA>> listPA;

    Student(List<Optional<PA>> listPA) {
        this.listPA = listPA;
    }

    Stream<Optional<Integer>> getMarks() {
        return listPA.stream()
                .map(x -> // Optional<PA>
                x.map(y -> y.getMarks()
                        .reduce(0, (a, b) -> a + b)));
    }
}

// Answer 3c below. Do not remove this comment.
class Main {
    int getTotalMarks(List<Student> list) {
        return list.stream()
                .flatMap(x -> x.getMarks()) // Stream<Optional<Integer>>
                .mapToInt(x -> x.orElse(0)) // IntStream
                // .map(x -> x.orElse(0)) // Stream<Integer>
                .reduce(0, (a, b) -> a + b);
    }
}