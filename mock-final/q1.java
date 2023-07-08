// Write your entire solution in this file only.
import java.util.List;
import java.util.Random;

class Bot {
    private final int id;
    private final ImList<Notifiable> users;

    Bot() {
        this(List.of());
    }

    Bot(List<Notifiable> users) {
        this(new Random().nextInt(1000), new ImList<>(users));
    }

    Bot(int id, ImList<Notifiable> users) {
        this.id = id;
        this.users = users;
    }

    Bot join(Notifiable user) {
        String msg = String.format("%s says hi to %s", this, user);
        Bot newBot = new Bot(id, users.add(user));
        for (Notifiable u : newBot.users) {
            u.notify(msg);
        }
        return newBot;
    }

    @Override
    public String toString() {
        return String.format("bot@%d", id);
    }
}

class User implements Notifiable {
    private final String name;

    User(String name) {
        this.name = name;
    }

    Bot join(Bot bot) {
        return bot.join(this);
    }

    public void notify(String msg) {
        System.out.printf("%s: %s\n", toString(), msg);
    }

    @Override
    public String toString() {
        return name;
    }
}

interface Notifiable {
    void notify(String s);
}
