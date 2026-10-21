
package navigation;

public record Route(
        String transport,
        Location start,
        Location destination,
        double distanceKm,
        long estimatedMinutes,
        String description
) {
    @Override
    public String toString() {
        return String.format(
                "Transport: %s%n" +
                        "From: %s%n" +
                        "To: %s%n" +
                        "Distance: %.2f km%n" +
                        "Estimated time: %d minutes%n" +
                        "Details: %s",
                transport,
                start.name(),
                destination.name(),
                distanceKm,
                estimatedMinutes,
                description
        );
    }
}
