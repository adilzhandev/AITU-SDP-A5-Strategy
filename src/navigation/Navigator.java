
package navigation;

import java.util.Objects;

public class Navigator {

    private RouteStrategy strategy;

    public Navigator(RouteStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = Objects.requireNonNull(
                strategy,
                "Route strategy cannot be null"
        );
    }

    public Route buildRoute(Location start, Location destination) {
        Objects.requireNonNull(start, "Start location cannot be null");
        Objects.requireNonNull(destination, "Destination cannot be null");

        return strategy.buildRoute(start, destination);
    }
}
