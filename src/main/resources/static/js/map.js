var hasLoaded = false;
var markers = [];

function addSimpleMarker(myMap, lat, lng, iconUrl, shadowUrl) {
    var greenIcon = L.icon({
        iconUrl: iconUrl,
        shadowUrl: shadowUrl
    });

    var marker = L.marker([lat, lng], {icon: greenIcon});
    marker.addTo(myMap);

    markers.push(marker);
}

function addAjaxMarkers(myMap, endpoint) {
    var markerGroup = L.layerGroup().addTo(myMap);
    markerGroup.clearLayers();

    $.ajax({
        method: "GET",
        url: endpoint
    }).done(function (data) {
        for (var i = 0; i < data.length; i++) {
            var marker = addMarker(markerGroup, data[i]);
            marker.on("dbclick", function (e) {
                myMap.closePopup();
            });

            if (!hasLoaded) {
                hasLoaded = true;
                map.panTo(marker.getLatLng());
            }
        }
    });
}

function addMarker(markerGroup, element) {
    var greenIcon = L.icon({
        iconUrl: element.iconUrl,
        iconSize: [48, 48]
    });

    var marker = new L.marker([element.latitude, element.longitude], {icon: greenIcon})
            .addTo(markerGroup)
            .bindPopup(element.info);
    marker.bindTooltip(element, {direction: "bottom"});

    return marker;
}

function setLocation(myMap, latInput, lngInput) {
    myMap.on("click", function (e) {
        for (var i = 0; i < markers.length; i++) {
            myMap.removeLayer(markers[i]);
        }

        var newLocation = new L.marker(e.latlng);
        newLocation.addTo(myMap);

        myMap.setView(e.latlng, 13);
        myMap.panTo(newLocation.getLatLng());

        $("#" + latInput).val(e.latlng.lat);
        $("#" + lngInput).val(e.latlng.lng);
    });
}