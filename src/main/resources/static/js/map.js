var hasLoaded = false;
var markers = [];
var isMaxim = false;
var sizeMap = {
    w: 0,
    h: 0
};

function addAjaxMarkerList(endpoint) {
    console.log("CONECTÁNDOSE A " + endpoint);

    for (var i = 0; i < markers.length; i++) {
        map.removeLayer(markers[i]);
    }

    $.ajax({
        method: "GET",
        url: endpoint
    }).done(function (data) {
        for (var i = 0; i < markers.length; i++) {
            markers[i].closeTooltip();
        }

        for (var i = 0; i < data.length; i++) {
            var marker = createMarker(data[i]);

            if (!hasLoaded) {
                hasLoaded = true;
                map.panTo(marker.getLatLng());
            }
        }
    });

//    setTimeout(addAjaxMarkerList, 1000);
}

function loadingCenter(id) {
//    var div = document.createElement("div");
//    div.id = 'loading2';
//    div.className = 'red';
//    div.style.zindex = 25000;
//
//    setTimeout(document.getElementById(id).appendChild(div), 400);
}

function createMarker(element) {
    var myIcon = L.icon({
        iconUrl: element.iconUrl,
        iconSize: [48, 48], // size of the icon
        iconAnchor: [48, 48], // point of the icon which will correspond to marker's location
        popupAnchor: [0, 0] // point from which the popup should open relative to the iconAnchor
    });

    var marker = new L.marker([element.latitude, element.longitude], {icon: myIcon})
            .addTo(map)
            .bindPopup(element.info);
    marker.textoToolTip = element.name;
    marker.imei = element.key;
    marker.bindTooltip(element, {direction: "bottom"});
    marker.on("dblclick", function (e) {
        map.closePopup();
    });
    
    return marker;
}

function maxiMiWindow() {
    var elem = document.getElementById("activityResponse");

    if (!isMaxim) {
        $("#buttonMaximise").html("&#128469;");
        elem.style.width = screen.availWidth + "px";
        elem.style.height = screen.availHeight + "px";
        isMaxim = true;

        if (elem.requestFullscreen) {
            elem.requestFullscreen();
        } else if (elem.mozRequestFullScreen) { /* Firefox */
            elem.mozRequestFullScreen();
        } else if (elem.webkitRequestFullscreen) { /* Chrome, Safari & Opera */
            elem.webkitRequestFullscreen();
        } else if (elem.msRequestFullscreen) { /* IE/Edge */
            elem.msRequestFullscreen();
        }
    } else {
        $("#buttonMaximise").html("&#128470;");

        isMaxim = false;

        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.mozCancelFullScreen) { /* Firefox */
            document.mozCancelFullScreen();
        } else if (document.webkitExitFullscreen) { /* Chrome, Safari and Opera */
            document.webkitExitFullscreen();
        } else if (document.msExitFullscreen) { /* IE/Edge */
            document.msExitFullscreen();
        }

        elem.style.width = sizeMap.w + "px";
        elem.style.height = sizeMap.h + "px";
    }
}

$(document).ready(function () {
//    $(document).on('webkitfullscreenchange mozfullscreenchange fullscreenchange', function (e) {
//        if (!window.screenTop && !window.screenY) {
//            isMaxim = false;
//        } else {
//            isMaxim = true;
//            maxiMiWindow();
//        }
//    });
});