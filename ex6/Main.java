import java.time.Instant;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: cs2030 (orig. Ooi Wei Tsang)
 */
class Main {

    static CompletableFuture<BusRoutes> processQuery(String query) {
        Scanner sc = new Scanner(query);
        BusStop srcBusStop = new BusStop(Integer.valueOf(sc.next()).toString());
        String searchString = sc.next();
        sc.close();
        return BusSg.findBusServicesBetween(srcBusStop, searchString);
    }

    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n")
            .tokens()
            .map(s -> processQuery(s))
            .toList()
            .stream()
            .map(routes -> routes.thenCompose(x -> x.description()))
            .forEach(x -> System.out.println(x.join()));
        sc.close();
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}
