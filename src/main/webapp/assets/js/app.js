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

// user https://github.com/calvinmetcalf/leaflet-ajax
var url = "/isochrone/service/test?format=geojson";
var isochronLayer = new L.GeoJSON.AJAX(url, {
	pointToLayer : pointToLayer,
	onEachFeature : onEachFeature
}).addTo(map);

isochronLayer.on('data:loaded', function() {
	this.map.fitBounds(isochronLayer.getBounds());
}.bind(this));
