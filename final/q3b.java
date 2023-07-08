// Write the entire class for q3(b) below. Do not remove this comment.

import java.util.List;

class UpdateRoute extends Command<City> {
    private static final int PARAM_POSITION_START_LOCATION = 0;
    private static final int PARAM_POSITION_END_LOCATION = 1;
    private static final int PARAM_POSITION_DISTANCE = 2;
    private static final String MESG_UPDATED = 
        "Route from %s to %s with distance %skm updated";

    UpdateRoute(List<String> params) {
        super(params, x -> {
            String origin = params.get(PARAM_POSITION_START_LOCATION);
            String destination = params.get(PARAM_POSITION_END_LOCATION);
            String distance = params.get(PARAM_POSITION_DISTANCE);
            City newCity = x.updateRoute(new Route(origin, destination, distance));
            System.out.println(String.format(MESG_UPDATED, origin, destination, distance));
            return newCity;
        });
    }
}
