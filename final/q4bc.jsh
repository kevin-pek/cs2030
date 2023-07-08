// Answer q4(b) below. Do not remove this comment.
static Command<T> parseCommand(String userInput) {
    String commandString = getFirstWord(userInput);
    if (commandString.equalsIgnoreCase("updateroute")) {
        return new UpdateCommand();
    } else if (commandString.equalsIgnoreCase("getdistance")) {
        return new GetDistance();
    }
    return new Command() {
        void execute() {
            System.out.printf("invalid command format: %s", userInput);
        }
    };
}

// Answer q4(c) below. Do not remove this comment.
public static void main(String[] args) {
    Invoker<City> invoker = new Invoker<City>();
    City city = new City();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
        String userInput = scanner.nextLine();
        List<String> parameters = splitParameters(userInput);
        invoker = invoker.addCommand(parseCommand(parameters));
    }
    invoker.executeCommand().map(x-> System.out.println(x.execute(city)));
}
