abstract class TrafficLight {
    private final String color;

    TrafficLight(String color) {
        this.color = color;
    }

    abstract TrafficLight toggle();

    @Override
    public String toString() {
        return this.color;
    }
}

abstract class RedLight extends TrafficLight {
    RedLight() {
        super("red");
    }

//    @Override
//    TrafficLight toggle() {
//        return new AmberLight() {
//            TrafficLight toggle() {
//                return new GreenLight() {
//                    TrafficLight toggle() {
//                        return new AmberLight() {
//                            TrafficLight toggle() {
//                                return new RedLight();
//                            }
//                        };
//                    }
//                };
//            }
//        };
//    }
}

abstract class GreenLight extends TrafficLight {
    GreenLight() {
        super("green");
    }
}

abstract class AmberLight extends TrafficLight {
    AmberLight() {
        super("amber");
    }
}

void toggling(TrafficLight t, int n) {
    System.out.print(t);
    for (int i = 1; i < n; i++) {
        t = t.toggle();
        System.out.print(" -> " + t);
    }
    System.out.println();
}

toggling(new RedLight(), 8)
