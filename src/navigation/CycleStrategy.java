
package navigation;

public class CycleStrategy implements RouteStrategy {

    private static final double BIKE_PATH_FACTOR = 1.2;
    private static final double CYCLING_SPEED = 16.0;
    private static final int TRAFFIC_LIGHT_DELAY = 2;

    @Override
    public Route buildRoute(Location start, Location destination) {
        double distance = start.distanceTo(destination) * BIKE_PATH_FACTOR;

        long minutes = Math.round(distance / CYCLING_SPEED * 60)
                + TRAFFIC_LIGHT_DELAY;

        return new Route(
                "Bicycle",
                start,
                destination,
                distance,
                minutes,
                "Using bicycle-friendly roads and cycle lanes"
        );
    }
}
