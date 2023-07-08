// Write the entire class for q3(c) below. Do not remove this comment.

import java.util.List;

class GetDistance extends Command<City> {
    private static final int PARAM_POSITION_START_LOCATION = 0;
    private static final int PARAM_POSITION_END_LOCATION = 1;
    private static final String MESG_NO_ROUTE = "No route exists from %s to %s!";
    private static final String MESG_DISTANCE = "Distance from %s to %s is %s";

    GetDistance(List<String> params) {
        super(params, x -> {
            String origin = params.get(PARAM_POSITION_START_LOCATION);
            String destination = params.get(PARAM_POSITION_END_LOCATION);
            x.getDistance(new Route(origin, destination))
                .ifPresentOrElse(
                    y -> System.out.println(String.format(MESG_DISTANCE, origin, destination, y)),
                    () -> System.out.println(String.format(MESG_NO_ROUTE, origin, destination)));
            return x;
        });
    }
}
