import java.util.Optional;

class City {
    // Answer q2(a) below. Do not remove this comment.
    private final ImList<Route> routes;

    City() {
        this.routes = new ImList<>();
    }

    // Answer q2(b) below. Do not remove this comment.
    private City(ImList<Route> routes) {
        this.routes = routes;
    }

    City updateRoute(Route route) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).equals(route)) {
                return new City(routes.set(i, route));
            }
        }
        return new City(routes.add(route));
    }

    // Answer q2(c) below. Do not remove this comment.
    Optional<String> getDistance(Route route) {
        for (int i = 0; i < routes.size(); i++) {
            if (routes.get(i).equals(route)) {
                return Optional.of(route.getDistance());
            }
        }
        return Optional.empty();
    }
} // end of class City. Do not remove this comment.
