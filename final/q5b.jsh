// Answer q5(b) below. Do not remove this comment.

public static void main(String[] args) {
    Invoker<City> invoker = new Invoker<City>();
    City city = new City();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNextLine()) {
        String userInput = scanner.nextLine();
        List<String> parameters = splitParameters(userInput);
        invoker = invoker.addCommand(parseCommand(parameters));
    }
    invoker.executeCommand().map(x -> x.execute(city).second()).map(x -> System.out.println(x.getLog()));
}
