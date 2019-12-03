package net.comtor.ocelot.leaflet;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Dec 02, 2019
 */
@XmlRootElement
public class MapMarker implements Serializable {

    private static final long serialVersionUID = 3045057358404819753L;

    private String key;
    private String name;
    private double latitude;
    private double longitude;
    private String event;
    private String iconUrl;
    private String info;

    public MapMarker() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "MapMarker{"
                + "key=" + key
                + ", name=" + name
                + ", latitude=" + latitude
                + ", longitude=" + longitude
                + ", event=" + event
                + ", iconURL=" + iconUrl
                + ", info=" + info
                + '}';
    }

    public static class Builder {

        private String name;
        private String key;
        private String event;
        private double latitude;
        private double longitude;
        private String iconUrl;
        private String info;

        public Builder() {

        }

        public Builder(double lat, double lng) {
            this.latitude = lat;
            this.longitude = lng;
        }

        public Builder name(String name) {
            this.name = name;

            return this;
        }

        public Builder key(String key) {
            this.key = key;

            return this;
        }

        public Builder event(String event) {
            this.event = event;

            return this;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;

            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;

            return this;
        }

        public Builder iconURL(String iconURL) {
            this.iconUrl = iconURL;

            return this;
        }

        public Builder info(String info) {
            this.info = info;

            return this;
        }

        public MapMarker build() {
            MapMarker marker = new MapMarker();
            marker.setName(name);
            marker.setKey(key);
            marker.setEvent(event);
            marker.setLatitude(latitude);
            marker.setLongitude(longitude);
            marker.setIconUrl(iconUrl);
            marker.setInfo(info);

            return marker;

        }
    }

}
