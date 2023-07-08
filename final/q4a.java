// Write the entire class for q4(a) below. Do not remove this comment.
// The additional method in Command class should be written in q3a.java

import java.util.Optional;

class Invoker<T> {
    private final ImList<Command<T>> commands;

    Invoker(ImList<Command<T>> cmd) {
        this.commands = cmd;
    }

    Invoker<T> addCommand(Command<T> cmd) {
        return new Invoker<T>(commands.add(cmd));
    }

    Optional<Command<T>> executeCommand() {
        return Optional.ofNullable(commands.stream()
                .reduce(commands.get(0), (acc, x) -> acc.andThen(x)));
    }
}
