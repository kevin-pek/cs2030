import java.util.Scanner;
import java.util.function.Supplier;
import java.util.Random;
import java.util.stream.Stream;

class Main {
    private static final Random RNG_REST = new Random(3L);
    private static final Random RNG_REST_PERIOD = new Random(4L);
    private static final double SERVER_REST_RATE = 0.1;

    static int i = 0;
    static double genServiceTime() {
        i++;
        switch(i) {
            case 1:
                return 5.0;
            case 4:
                return 2.0;
            case 9:
                return 3.0;
            default:
                return 10.0;
        }
    }
    static double genRestPeriod() {
        return -Math.log(RNG_REST_PERIOD.nextDouble()) / SERVER_REST_RATE;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ImList<Double> arrivalTimes = new ImList<Double>();
        Supplier<Double> serviceTimes = () -> genServiceTime();
        int numOfServers = sc.nextInt();
        int numOfSelfChecks = sc.nextInt();
        int qmax = sc.nextInt();
        double probRest = sc.nextDouble();
        Supplier<Double> restTimes = () ->
            RNG_REST.nextDouble() < probRest ? genRestPeriod() : 0.0;

        while (sc.hasNextDouble()) {
            arrivalTimes = arrivalTimes.add(sc.nextDouble());
        }

        Simulator sim = new Simulator(numOfServers, numOfSelfChecks, qmax, arrivalTimes, serviceTimes, restTimes);
        System.out.println(sim.simulate());
        sc.close();
    }
}
