var cartodbAttribution = '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, &copy; <a href="http://cartodb.com/attributions">CartoDB</a>';

var cartoLight = L.tileLayer(
		'http://{s}.basemaps.cartocdn.com/light_nolabels/{z}/{x}/{y}.png', {
			attribution : cartodbAttribution
		});

var map = L.map("map", {
	zoom : 14,
	minZoom : 12,
	center : [ 50.94135, 6.95819 ],
	layers : [ cartoLight ],
	zoomControl : true,
	attributionControl : true
});

function pointToLayer(feature, latlng) {
	// empty by now
}

function onEachFeature(feature, latlng) {
	// empty by now
}

var colors = ['#a50026','#d73027','#f46d43','#fdae61','#fee08b','#ffffbf','#d9ef8b','#a6d96a','#66bd63','#1a9850','#006837'].reverse();

function polystyle(feature) {
    return {
        fillColor: colors[feature.properties.id],
        weight: 2,
        opacity: 1,
        color: 'white',  //Outline color
        fillOpacity: 0.5
    };
}

// user https://github.com/calvinmetcalf/leaflet-ajax
var url = "/isochrone/service/test?format=geojson";
var isochronLayer = new L.GeoJSON.AJAX(url, {

	style: polystyle
}).addTo(map);

isochronLayer.on('data:loaded', function() {
	this.map.fitBounds(isochronLayer.getBounds());
}.bind(this));
