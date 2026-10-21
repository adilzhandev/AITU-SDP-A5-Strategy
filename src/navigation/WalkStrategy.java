
package navigation;

public class WalkStrategy implements RouteStrategy {

    private static final double PATH_FACTOR = 1.15;
    private static final double WALKING_SPEED = 5.0;
    private static final int CROSSING_DELAY = 3;

    @Override
    public Route buildRoute(Location start, Location destination) {
        double distance = start.distanceTo(destination) * PATH_FACTOR;

        long minutes = Math.round(distance / WALKING_SPEED * 60)
                + CROSSING_DELAY;

        return new Route(
                "Walking",
                start,
                destination,
                distance,
                minutes,
                "Using pedestrian paths and crossings"
        );
    }
}
