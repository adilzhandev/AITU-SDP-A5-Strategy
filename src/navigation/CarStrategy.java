
package navigation;

public class CarStrategy implements RouteStrategy {

    private static final double ROAD_FACTOR = 1.3;
    private static final double AVERAGE_SPEED = 40.0;
    private static final int TRAFFIC_DELAY = 8;

    @Override
    public Route buildRoute(Location start, Location destination) {
        double distance = start.distanceTo(destination) * ROAD_FACTOR;

        long minutes = Math.round(distance / AVERAGE_SPEED * 60)
                + TRAFFIC_DELAY;

        return new Route(
                "Car",
                start,
                destination,
                distance,
                minutes,
                "Driving on roads with estimated traffic delays"
        );
    }
}

