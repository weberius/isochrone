var cartodbAttribution = '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, &copy; <a href="http://cartodb.com/attributions">CartoDB</a>';
var url = "/isochrone/service/test?format=geojson";


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

// die Farben werden in einem zweidimensionalen Array abgelegt.
// die erste Dimension steht für die Anzahl der Farben
// farben werden mit colorbrewer bestimmt:
// http://colorbrewer2.org/#type=diverging&scheme=RdYlGn&n=11
var colors = new Array(9);
colors[2] = ['#fc8d59','#ffffbf','#91cf60'].reverse();
colors[3] = ['#d7191c','#fdae61','#a6d96a','#1a9641'].reverse();
colors[4] = ['#d7191c','#fdae61','#ffffbf','#a6d96a','#1a9641'].reverse();
colors[5] = ['#d73027','#fc8d59','#fee08b','#d9ef8b','#91cf60','#1a9850'].reverse();
colors[6] = ['#d73027','#fc8d59','#fee08b','#ffffbf','#d9ef8b','#91cf60','#1a9850'].reverse();
colors[7] = ['#d73027','#f46d43','#fdae61','#fee08b','#d9ef8b','#a6d96a','#66bd63','#1a9850'].reverse();
colors[8] = ['#d73027','#f46d43','#fdae61','#fee08b','#ffffbf','#d9ef8b','#a6d96a','#66bd63','#1a9850'].reverse();
colors[9] = ['#a50026','#d73027','#f46d43','#fdae61','#fee08b','#d9ef8b','#a6d96a','#66bd63','#1a9850','#006837'].reverse();
colors[10] = ['#a50026','#d73027','#f46d43','#fdae61','#fee08b','#ffffbf','#d9ef8b','#a6d96a','#66bd63','#1a9850','#006837'].reverse();

function polystyle(feature) {
    return {
        fillColor: colors[10][feature.properties.id],
        weight: 2,
        opacity: 1,
        color: 'white',  //Outline color
        fillOpacity: 0.5
    };
}

// user https://github.com/calvinmetcalf/leaflet-ajax
var isochronLayer = new L.GeoJSON.AJAX(url, {
	style: polystyle
}).addTo(map);

isochronLayer.on('data:loaded', function() {
	this.map.fitBounds(isochronLayer.getBounds());
}.bind(this));
