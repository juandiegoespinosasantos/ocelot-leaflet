package net.comtor.ocelot.leaflet;

/**
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Dec 02, 2019
 */
public class MapCoordinate {

    private double latitude;
    private double longitude;

    public MapCoordinate() {
    }

    public MapCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Marker{"
                + "latitude=" + latitude
                + ", longitude=" + longitude
                + '}';
    }
}
