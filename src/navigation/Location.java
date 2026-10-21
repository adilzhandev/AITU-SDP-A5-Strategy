
package navigation;

public record Location(
        String name,
        double latitude,
        double longitude
) {
    public Location {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Location name is required");
        }

        if (!Double.isFinite(latitude) || latitude < -90 || latitude > 90
                || !Double.isFinite(longitude) || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    public double distanceTo(Location other) {
        double earthRadius = 6371.0;

        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(other.latitude);

        double deltaLat = lat2 - lat1;
        double deltaLon = Math.toRadians(other.longitude - longitude);

        double a = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(deltaLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}
