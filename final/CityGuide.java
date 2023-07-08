import java.util.Scanner;
import java.util.List;
import java.util.Optional;

class CityGuide {
    private static final int PARAM_POSITION_START_LOCATION = 0;
    private static final int PARAM_POSITION_END_LOCATION = 1;
    private static final int PARAM_POSITION_DISTANCE = 2;
    private static final String MESG_INVALID_FORMAT = "invalid command format: %s";
    private static final String MESG_NO_ROUTE = "No route exists from %s to %s!";
    private static final String MESG_DISTANCE = "Distance from %s to %s is %s";
    private static final String MESG_UPDATED = 
        "Route from %s to %s with distance %skm updated";

    public static void main(String[] args) {
        City city = new City();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            String command = parseCommand(userInput);
            String feedback = "";
            if (command == "UPDATE_ROUTE") {
                List<String> parameters = splitParameters(userInput);
                Pair<String,City> pair = updateRoute(parameters, city);
                feedback = pair.first();
                city = pair.second();
            } else if (command == "GET_DISTANCE") {
                List<String> parameters = splitParameters(userInput);
                feedback = getDistance(parameters, city);
            } else {
                feedback = String.format(MESG_INVALID_FORMAT, userInput);
            }
            System.out.println(feedback);
        }
    }

    static String parseCommand(String userInput) {
        String commandString = getFirstWord(userInput);
        if (commandString.equalsIgnoreCase("updateroute")) {
            return "UPDATE_ROUTE";
        } else if (commandString.equalsIgnoreCase("getdistance")) {
            return "GET_DISTANCE";
        } else {
            return "INVALID";
        }
    }

    static List<String> splitParameters(String commandParametersString) {
        return List.of(removeFirstWord(commandParametersString)
            .trim().split("\\s+"));
    }

    static String getDistance(List<String> parameters, City city) {
        String newStartLocation = parameters.get(PARAM_POSITION_START_LOCATION);
        String newEndLocation = parameters.get(PARAM_POSITION_END_LOCATION);

        Route other = new Route(newStartLocation, newEndLocation);
        Optional<String> os = city.getDistance(other);

        if (os.isEmpty()) {
            return String.format(MESG_NO_ROUTE, newStartLocation,
                newEndLocation);
        } else {
            return String.format(MESG_DISTANCE, newStartLocation, newEndLocation,
                os.get());
        }
    }

    static Pair<String, City> updateRoute(List<String> parameters, City city) {
        String newStartLocation = parameters.get(PARAM_POSITION_START_LOCATION);
        String newEndLocation = parameters.get(PARAM_POSITION_END_LOCATION);
        String distance = parameters.get(PARAM_POSITION_DISTANCE);

        Route other = new Route(newStartLocation, newEndLocation, distance);

        return Pair.<String,City>of(String.format(MESG_UPDATED, newStartLocation, 
            newEndLocation, distance), city.updateRoute(other));
    }

    static String getFirstWord(String userInput) {
        return userInput.trim().split("\\s+")[0];
    }

    static String removeFirstWord(String userInput) {
        return userInput.replace(getFirstWord(userInput), "").trim();
    }
}
