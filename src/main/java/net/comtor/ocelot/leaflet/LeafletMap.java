package net.comtor.ocelot.leaflet;

import net.comtor.ocelot.html.programing.HtmlScript;
import net.comtor.ocelot.html.styles.HtmlDiv;

/**
 *
 * @author juandiego@comtor.net
 * @since 1.8
 * @version Dec 02, 2019
 */
public class LeafletMap extends HtmlDiv {

    private static final String TILE_SERVER_URL = "http://trackerco.comtor.net/osm_tiles/";
    private static final String DEFAULT_ICON_URL = "css/images/marker-icon.png";
    private static final String DEFAULT_SHADOW_URL = "css/images/marker-shadow.png";

    public static final int ZOOM_LEVEL_WORLD = 1;
    public static final int ZOOM_LEVEL_CONTINENT = 5;
    public static final int ZOOM_LEVEL_CITY = 13;
    public static final int ZOOM_LEVEL_STREETS = 15;
    public static final int ZOOM_LEVEL_BUILDINGS = 20;

    private String id;
    private double latCenter;
    private double lngCenter;
    private int zoom;

    public LeafletMap(String id, double latCenter, double lngCenter, int zoom) {
        super(id);
        addClass("leaflet-map map");

        this.id = id;
        this.latCenter = latCenter;
        this.lngCenter = lngCenter;
        this.zoom = zoom;

        init();
    }

    public LeafletMap(String id, double latCenter, double lngCenter) {
        this(id, latCenter, lngCenter, ZOOM_LEVEL_CITY);
    }

    public LeafletMap(String id) {
        this(id, 0, 0);
    }

    private void init() {
        String js = String.format("\n"
                + " var map_%1$s = L.map(\"%1$s\").setView([%2$f, %3$f], %4$d); \n"
                + ""
                + " L.tileLayer(\"%5$s{z}/{x}/{y}.png\", { \n"
                + "     attribution: \"\" \n"
                + " }).addTo(map_%1$s); \n\n",
                id, latCenter, lngCenter, zoom, TILE_SERVER_URL);

        add(new HtmlScript(js));
    }

    public void addSimpleMarker(double lat, double lng, String iconUrl, String shadowUrl) {
        String js = String.format("addSimpleMarker(map_%1$s, %2$f, %3$f, \"%4$s\", \"%5$s\")", id, lat, lng, iconUrl, shadowUrl);

        add(new HtmlScript(js));
    }

    public void addSimpleMarker(double lat, double lng) {
        LeafletMap.this.addSimpleMarker(lat, lng, DEFAULT_ICON_URL, DEFAULT_SHADOW_URL);
    }

    public void setLocation(String latInput, String lngInput) {
        String js = String.format("setLocation(map_%1$s, \"%2$s\", \"%3$s\")", id, latInput, lngInput);

        add(new HtmlScript(js));
    }

    /**
     * Agrega al mapa una lista de marcadores por AJAX
     *
     * @param endpoint URL del WS que devuelve la lista de marcadores.
     */
    public void addAjaxMarkers(String endpoint) {
        String js = String.format("addAjaxMarkers(map_%1$s, \"%2$s\");", id, endpoint);

        add(new HtmlScript(js));
    }

    public double getLatCenter() {
        return latCenter;
    }

    public void setLatCenter(double latCenter) {
        this.latCenter = latCenter;
    }

    public double getLngCenter() {
        return lngCenter;
    }

    public void setLngCenter(double lngCenter) {
        this.lngCenter = lngCenter;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

}
