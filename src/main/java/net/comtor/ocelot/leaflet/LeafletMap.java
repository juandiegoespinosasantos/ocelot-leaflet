package net.comtor.ocelot.leaflet;

import java.util.List;
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

    private void init() {
        String js = String.format("\n"
                + " var map_%1$s = {}; \n"
                + " var markers_%1$s = []; \n"
                + " var coordinates_%1$s = null; \n"
                + " var polyline_%1$s = null; \n\n"
                + ""
                + " function initMap() { \n"
                + "     map = L.map(\"%1$s\", {center: [%2$f, %3$f], zoom:  %4$d, preferCanvas: true}); \n"
                + "     L.tileLayer(\"%5$s{z}/{x}/{y}.png\", { \n"
                + "         maxZoom: 18, \n"
                + "         attribution: \"\", \n"
                + "         id: \"mapbox.streets\" \n"
                + "     }).addTo(map); \n\n"
                + ""
                + "     loadingCenter(\"%1$s\"); \n"//TODO
                + ""
                + "     map.on(\"zoomstart\", function(e) { \n"
                + "         hasZoom = true; \n"
                + "     }); \n"
                + "     map.on(\"movestart\", function(e) { \n"
                + "         hasPane = true; \n"
                + "     }); \n"
                + " } \n\n"
                + ""
                + " initMap(); \n",
                id, lngCenter, latCenter, zoom, TILE_SERVER_URL);

        HtmlScript js2 = new HtmlScript();
        js2.addRawText(js);

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

    public void addSampleMarker(double latitude, double longitude) {
        String js = String.format("addSampleMarker(map%1$s, %2$s, %3$s);",
                id, latitude, longitude);

        add(new HtmlScript(js));
    }

    public void addListMarker(List<MapCoordinate> markers) {
        StringBuilder script = new StringBuilder();

        for (MapCoordinate marker : markers) {
            String func = String.format("addSampleMarker(map%1$s, %2$s, %3$s); ",
                    id, marker.getLatitude(), marker.getLongitude());

            script.append(func);
        }

        add(new HtmlScript(script.toString()));
    }

    public void addAjaxMarker(String endpoint) {
        String js = String.format("addAjaxMarker(map%1$s, '%2$s');", id, endpoint);

        add(new HtmlScript(js));
    }

    /**
     * Agrega al mapa una lista de marcadores por AJAX
     *
     * @param endpoint URL del WS que devuelve la lista de marcadores.
     * @param withPolyline Si se agrega polilínea
     */
    public void addAjaxMarkerList(String endpoint, boolean withPolyline) {
        String polyline = (withPolyline ? ("polyline" + id) : "null");

//        String js = String.format("addAjaxMarkerList(map%1$s, coordinates%1$s, markers%1$s, %2$s, '%3$s');",
//                id, polyline, endpoint);
        String js = String.format("addAjaxMarkerList('%1$s');",
                endpoint);

//        add(new HtmlScript(js));
        HtmlScript js2 = new HtmlScript();
        js2.addRawText(js);

        add(new HtmlScript(js));
    }

    /**
     * Agrega al mapa una lista de marcadores por AJAX que se actualiza cada
     * cierto tiempo.
     *
     * @param endpoint URL del WS que devuelve la lista de marcadores.
     * @param interval Tiempo en segundos del intervalo de actualización
     * @param withPolyline Si se agrega polilínea
     */
    public void addAjaxMarkerInvertal(String endpoint, int interval, boolean withPolyline) {
        int millis = interval * 1000;
        String js;

        if (withPolyline) {
            js = String.format("addAjaxMarkerInvertal(map%1$s, coordinates%1$s, markers%1$s, polyline%1$s, '%2$s', %3$s);",
                    id, endpoint, millis);
        } else {
            js = String.format("addAjaxMarkerIntervalWithoutLine(map%1$s, coordinates%1$s, markers%1$s, '%2$s', %3$s);",
                    id, endpoint, millis);
        }

        add(new HtmlScript(js));
    }

}
